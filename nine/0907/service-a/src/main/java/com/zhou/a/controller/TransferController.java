package com.zhou.a.controller;

import com.zhou.common.dto.TransferDTO;
import com.zhou.common.service.TransferA2BService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Resource
    private TransferA2BService transferA2BService;
    @PostMapping("/a2b")
    public String a2b(@RequestBody TransferDTO transferDTO){
        transferA2BService.transfer(transferDTO);
        return "交易完成";
    }
}
