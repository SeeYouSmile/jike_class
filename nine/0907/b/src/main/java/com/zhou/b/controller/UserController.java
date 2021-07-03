package com.zhou.b.controller;

import com.zhou.b.service.TransferService;
import com.zhou.common.dto.TransferDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private TransferService transferService;
    @PostMapping("/b2a")
    public String b2a(TransferDTO transferDTO){
        return transferService.b2a(transferDTO);
    }
}
