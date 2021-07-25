package com.zhou.core;

import com.alibaba.fastjson.JSON;
import com.zhou.core.dto.AckDTO;
import com.zhou.core.dto.MyHttpResult;
import com.zhou.core.dto.MymqResponseDTO;
import com.zhou.core.dto.PollDTO;
import com.zhou.core.utils.MyHttpUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class MymqConsumer {

    @Value("${mymq.server-url}")
    private String serverUrl;
    @Value("${mymq.port}")
    private int port;

    private final String POLL="/mymq/poll";
    private final String ACK="/mymq/ack";

    public List<Object> poll(String topic, String consumer, int n, boolean autoAck){
        PollDTO pollDTO = new PollDTO();
        pollDTO.setTopic(topic);
        pollDTO.setConsumer(consumer);
        pollDTO.setN(n);
        pollDTO.setAutoAck(autoAck);
        MyHttpResult myHttpResult = MyHttpUtils.postJson(serverUrl + ":" + port + POLL, null, JSON.toJSONString(pollDTO));
        if(myHttpResult.getCode()==200){
            String content = myHttpResult.getContent();
            MymqResponseDTO mymqResponseDTO = JSON.parseObject(content, MymqResponseDTO.class);
            if(mymqResponseDTO.getStatus()==0){
                return mymqResponseDTO.getBodies();
            }
        }
        return null;
    }

    public boolean ack(String topic,String consumer){
        AckDTO ackDTO = new AckDTO();
        ackDTO.setTopic(topic);
        ackDTO.setConsumer(consumer);
        MyHttpResult myHttpResult = MyHttpUtils.postJson(serverUrl + ":" + port + ACK, null, JSON.toJSONString(ackDTO));
        if(myHttpResult.getCode()==200){
            return true;
        }
        return false;
    }
}
