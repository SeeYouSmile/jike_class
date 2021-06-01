package com.zhou.five.controller;

import com.zhou.five.jms.SendService;
import com.zhou.five.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private SendService sendService;

    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }

    @PostMapping("/send")
    public String send(@RequestBody Student student){
        sendService.send(student);
        return "发送成功";
    }
}
