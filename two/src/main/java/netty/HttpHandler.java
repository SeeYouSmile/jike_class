package netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;

public class HttpHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullHttpRequest= (FullHttpRequest) msg;
            String uri = fullHttpRequest.uri();
            if(uri.contains("/test")){
                handler(fullHttpRequest,ctx,"test");
            }else{
                handler(fullHttpRequest,ctx,"other");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void handler(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String value) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes()));
        response.headers().set("Content-Type","application/json");
        response.headers().setInt("Content-Length",response.content().readableBytes());
        if(fullHttpRequest!=null){
            if(!HttpUtil.isKeepAlive(fullHttpRequest)){
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            }else{
                response.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderNames.KEEP_ALIVE);
                ctx.write(response);
            }
        }
    }
}
