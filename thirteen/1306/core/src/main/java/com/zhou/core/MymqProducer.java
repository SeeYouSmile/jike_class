package com.zhou.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhou.core.dto.MyHttpResult;
import com.zhou.core.dto.MymqResponseDTO;
import com.zhou.core.dto.SendDTO;
import com.zhou.core.utils.MyHttpUtils;
import org.springframework.beans.factory.annotation.Value;

public class MymqProducer {

    @Value("${mymq.server-url}")
    private String serverUrl;
    @Value("${mymq.port}")
    private int port;

    private final String SEND="/mymq/send";

    public boolean send(String topic,Object body){
        SendDTO sendDTO = new SendDTO();
        sendDTO.setTopic(topic);
        sendDTO.setBody(body);
        MyHttpResult myHttpResult = MyHttpUtils.postJson(serverUrl + ":" + port + SEND, null, JSONObject.toJSONString(sendDTO));
        if(myHttpResult.getCode()==200){
            String content = myHttpResult.getContent();
            MymqResponseDTO mymqResponseDTO = JSON.parseObject(content, MymqResponseDTO.class);
            return mymqResponseDTO.getStatus()==0;
        }
        return false;
    }
}
