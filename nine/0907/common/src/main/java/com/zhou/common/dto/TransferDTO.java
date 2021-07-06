package com.zhou.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransferDTO implements Serializable {
    private String user_id_from;
    private String user_id_to;
    private Float money;
    private String type_from;
    private String type_to;
}
