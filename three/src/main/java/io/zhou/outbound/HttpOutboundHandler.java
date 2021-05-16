package io.zhou.outbound;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.zhou.filter.HeaderHttpResponseFilter;
import io.zhou.filter.HttpRequestFilter;
import io.zhou.filter.HttpResponseFilter;
import io.zhou.pojo.MyHttpResult;
import io.zhou.router.HttpEndpointRouter;
import io.zhou.router.MyHttpEndpointRouter;
import io.zhou.router.Router;
import io.zhou.utils.MyHttpUtils;
import org.apache.http.impl.nio.reactor.IOReactorConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpOutboundHandler {
    
    private ExecutorService proxyService;
    private List<String> backendUrls;

    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new MyHttpEndpointRouter();

    public HttpOutboundHandler(List<String> backends) {

        this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
        
        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();
        
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }
    
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        List<Integer> weights=new ArrayList<>();
        String proxyWeight = System.getProperty("proxyWeight", "1,4");
        String[] proxyWeigths = proxyWeight.split(",");
        for (String weigth : proxyWeigths) {
            weights.add(Integer.valueOf(weigth));
        }
        router.setWeightMemberMap(backendUrls,weights);
        String backendUrl = router.route(this.backendUrls, Router.WEIGHT);//权重模式，自定义路由，可选路由方式，使用权重方式可以用setWeightMemberMap修改权重
//        String backendUrl = router.route(this.backendUrls, Router.ROUNDRIBBON);//轮询模式
//        String backendUrl = router.route(this.backendUrls, Router.RANDOM);//随机模式
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        proxyService.submit(()->fetchGet(fullRequest, ctx, url));
    }
    
    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        MyHttpResult myHttpResult = MyHttpUtils.get(url);
        try {
            handleResponse(inbound,ctx, myHttpResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final MyHttpResult myHttpResult) throws Exception {
        FullHttpResponse response = null;
        try {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(myHttpResult.getContent().getBytes()));

            for (Map.Entry<String, String> entry : myHttpResult.getHeader().entrySet()) {
                response.headers().set(entry.getKey(),entry.getValue());
            }

            filter.filter(response);

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
        
    }
    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    
}
