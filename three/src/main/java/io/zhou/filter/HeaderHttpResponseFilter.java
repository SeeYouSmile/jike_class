package io.zhou.filter;

import io.netty.handler.codec.http.FullHttpResponse;
import io.zhou.utils.SHAUtils;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        //如果返回头部存在Key的值，使用sha256加密后返回
        String key = response.headers().get("Key");
        if(key!=null){
            String sha256 = SHAUtils.sha256(key.getBytes());
            response.headers().set("Key",sha256);
        }
    }


}
