package com.zhou.common.constant;

import java.util.HashMap;
import java.util.Map;

public class Money {
    public static final Map<String,Float> moneyMap=new HashMap<>();
    public static final String CNY="CNY";
    public static final String USD="USD";
    static {
        moneyMap.put(CNY,1.0f);
        moneyMap.put(USD,7.0f);
    }
}
