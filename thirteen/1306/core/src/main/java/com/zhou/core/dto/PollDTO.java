package com.zhou.core.dto;

import lombok.Data;

@Data
public class PollDTO {
    //topic名称
    private String topic;
    //消费者名称
    private String consumer;
    //消费数量
    private Integer n;
    //是否自动确认
    private Boolean autoAck;
}
