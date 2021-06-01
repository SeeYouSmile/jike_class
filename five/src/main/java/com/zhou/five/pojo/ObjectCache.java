package com.zhou.five.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectCache {
    private Object value;
    private int cacheTime;
    private long updateTime;
}
