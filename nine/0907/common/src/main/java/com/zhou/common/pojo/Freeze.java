package com.zhou.common.pojo;

import lombok.Data;

@Data
public class Freeze {
    private Integer id;
    private String user_id;
    private String transfer_id;
    private Float money;
    private String type;
    private Long create_time;
}
