package io.zhou.filter;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        //为请求参数添加token过滤，token不为123456被拦截，并返回无效token
        String token = fullRequest.headers().get("token");
        if(token==null||"".equals(token)||!token.equals("123456")){
            String result="无效token";
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(result.getBytes()));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", result.getBytes().length);
            ctx.write(response);
        }
    }
}
