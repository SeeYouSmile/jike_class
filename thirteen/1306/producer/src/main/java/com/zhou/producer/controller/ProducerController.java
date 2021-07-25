package com.zhou.producer.controller;

import com.zhou.core.dto.MymqResponseDTO;
import com.zhou.core.dto.SendDTO;
import com.zhou.producer.service.ProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Resource
    private ProducerService mymqProducer;

    /**
     * 消息发送到队列接口
     * @param sendDTO（内有注释）
     * @return
     */
    @PostMapping("/send")
    public MymqResponseDTO send(@RequestBody SendDTO sendDTO){
        boolean result = mymqProducer.send(sendDTO.getTopic(), sendDTO.getBody());
        MymqResponseDTO mymqResponseDTO = new MymqResponseDTO();
        if(result){
            mymqResponseDTO.setStatus(0);//发送成功
        }else{
            mymqResponseDTO.setStatus(1);//发送失败
        }
        return mymqResponseDTO;
    }
}
