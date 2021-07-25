package com.zhou.server.controller;

import com.zhou.core.dto.AckDTO;
import com.zhou.core.dto.MymqResponseDTO;
import com.zhou.core.dto.PollDTO;
import com.zhou.core.dto.SendDTO;
import com.zhou.server.service.MymqServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/mymq")
public class MymqController {

    @Resource
    private MymqServer mymqServer;

    /**
     * 消息发送接口
     * @param sendDTO（内有注释）
     * @return
     */
    @PostMapping("/send")
    public MymqResponseDTO send(@RequestBody SendDTO sendDTO){
        boolean result = mymqServer.send(sendDTO.getTopic(), sendDTO.getBody());
        MymqResponseDTO mymqResponseDTO = new MymqResponseDTO();
        if(result){
            mymqResponseDTO.setStatus(0);
        }else{
            mymqResponseDTO.setStatus(1);
        }
        return mymqResponseDTO;
    }

    /**
     * 消息获取接口
     * 如果自动确认设置为false的话，多次消费，确认的时候以最后一次为准
     * @param pollDTO（内有注释）
     * @return
     */
    @PostMapping("/poll")
    public MymqResponseDTO poll(@RequestBody PollDTO pollDTO){
        List<Object> bodies = mymqServer.poll(pollDTO.getTopic(), pollDTO.getConsumer(),pollDTO.getN(),pollDTO.getAutoAck());
        MymqResponseDTO mymqResponseDTO = new MymqResponseDTO();
        if(bodies!=null){
            mymqResponseDTO.setStatus(0);
        }else{
            mymqResponseDTO.setStatus(1);
        }
        mymqResponseDTO.setBodies(bodies);
        return mymqResponseDTO;
    }

    /**
     * 消息消费确认接口
     * 确认最后一次非自动确认的消息获取
     * @param ackDTO（内有注释）
     * @return
     */
    @PostMapping("/ack")
    public MymqResponseDTO ack(@RequestBody AckDTO ackDTO){
        mymqServer.ack(ackDTO.getTopic(),ackDTO.getConsumer());
        MymqResponseDTO mymqResponseDTO = new MymqResponseDTO();
        mymqResponseDTO.setStatus(0);
        return mymqResponseDTO;
    }
}
