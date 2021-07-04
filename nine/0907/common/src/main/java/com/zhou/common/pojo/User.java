package com.zhou.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private String user_id;
    private Float cny;
    private Float usd;
    private Float freeze_cny;
    private Float freeze_usd;
}
