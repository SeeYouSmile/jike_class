package com.zhou.consumer.controller;

import com.zhou.consumer.service.ConsumerService;
import com.zhou.core.dto.AckDTO;
import com.zhou.core.dto.MymqResponseDTO;
import com.zhou.core.dto.PollDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource
    private ConsumerService mymqConsumer;

    /**
     * 消息队列消费接口
     * @param pollDTO（内有注释）
     * @return
     */
    @PostMapping("/poll")
    public MymqResponseDTO poll(@RequestBody PollDTO pollDTO){
        List<Object> data = mymqConsumer.poll(pollDTO.getTopic(), pollDTO.getConsumer(),pollDTO.getN(),pollDTO.getAutoAck());
        MymqResponseDTO mymqResponseDTO = new MymqResponseDTO();
        if(data!=null){
            mymqResponseDTO.setStatus(0);//成功获取到队列消息
            mymqResponseDTO.setBodies(data);//队列消息内容
        }else{
            mymqResponseDTO.setStatus(1);//失败
        }
        return mymqResponseDTO;
    }

    /**
     * 消息消费确认接口
     * @param ackDTO（内有注释）
     * @return
     */
    @PostMapping("/ack")
    public MymqResponseDTO ack(@RequestBody AckDTO ackDTO){
        boolean ack = mymqConsumer.ack(ackDTO.getTopic(), ackDTO.getConsumer());
        MymqResponseDTO mymqResponseDTO = new MymqResponseDTO();
        if(ack){
            mymqResponseDTO.setStatus(0);//确认请求发送成功
        }else{
            mymqResponseDTO.setStatus(1);//确认请求发送失败
        }
        return mymqResponseDTO;
    }
}
