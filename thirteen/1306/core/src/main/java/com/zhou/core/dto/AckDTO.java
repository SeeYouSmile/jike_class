package com.zhou.core.dto;

import lombok.Data;

@Data
public class AckDTO {
    private String topic;
    private String consumer;
}
