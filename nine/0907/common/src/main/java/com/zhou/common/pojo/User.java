package com.zhou.common.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String user_id;
    private Float cny;
    private Float usd;
    private Float freeze_cny;
    private Float freeze_usd;
}
