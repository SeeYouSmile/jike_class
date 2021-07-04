package com.zhou.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Transfer implements Serializable {
    private Integer id;
    private String transferId;
    private String userIdFrom;
    private String userIdTo;
    private Float money;
    private String type;
    private String status;
    private Long createTime;
    public class CurrentType{//币种
        public static final String CNY="cny";//人民币
        public static final String USD="usd";//美元
    }
    public class TransferStatus{//交易状态
        public static final String PROCESSING="processing";//交易中
        public static final String SUCCESS="success";//成功
        public static final String FAIL="fail";//失败
    }
}
