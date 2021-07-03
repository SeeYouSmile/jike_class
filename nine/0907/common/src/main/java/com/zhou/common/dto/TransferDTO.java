package com.zhou.common.dto;

import lombok.Data;

@Data
public class TransferDTO {
    private String user_id_from;
    private String user_id_to;
    private Float money;
    private String type;

    public class Transfer{
        public static final String CNY="cny";
        public static final String USD="usd";
    }
}
