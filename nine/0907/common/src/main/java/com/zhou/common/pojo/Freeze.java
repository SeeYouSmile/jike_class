package com.zhou.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Freeze implements Serializable {
    private Integer id;
    private String user_id;
    private String transfer_id;
    private Float money;
    private String type;
    private Long create_time;
}
