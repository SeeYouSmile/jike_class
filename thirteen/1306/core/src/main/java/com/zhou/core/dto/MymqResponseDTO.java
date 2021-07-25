package com.zhou.core.dto;

import lombok.Data;

import java.util.List;

@Data
public class MymqResponseDTO {
    private Integer status;
    private List<Object> bodies;
}
