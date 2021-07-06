package com.zhou.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Transfer implements Serializable {
    private Integer id;
    private String transfer_id;
    private String user_id_from;
    private String user_id_to;
    private Float money_from;
    private Float money_to;
    private String type_from;
    private String type_to;
    private String status;
    private Long create_time;
    public class TransferStatus{//交易状态
        public static final String PROCESSING="processing";//交易中
        public static final String SUCCESS="success";//成功
        public static final String FAIL="fail";//失败
    }
}
