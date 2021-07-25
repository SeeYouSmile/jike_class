package com.zhou.core.dto;

import lombok.Data;

@Data
public class SendDTO {
    //topic名称
    private String topic;
    //发送内容
    private Object body;
}
