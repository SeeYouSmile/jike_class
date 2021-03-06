package com.zhou.b.controller;

import com.zhou.common.dto.TransferDTO;
import com.zhou.common.service.TransferB2AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private TransferB2AService transferB2AService;
    @PostMapping("/b2a")
    public String b2a(@RequestBody TransferDTO transferDTO){
        transferB2AService.transaction(transferDTO);
        return "交易完成";
    }
}
