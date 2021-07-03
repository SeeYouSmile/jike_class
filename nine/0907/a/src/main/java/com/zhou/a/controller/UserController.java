package com.zhou.a.controller;

import com.zhou.a.service.TransferService;
import com.zhou.common.dto.TransferDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private TransferService transferService;
    @PostMapping("/a2b")
    public String a2b(@RequestBody TransferDTO transferDTO){
        return transferService.a2b(transferDTO);
    }
}
