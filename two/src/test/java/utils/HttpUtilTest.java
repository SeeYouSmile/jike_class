package utils;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pojo.HttpResult;

public class HttpUtilTest {
    @Test
    public void testGet(){
        HttpResult httpResult = HttpUtil.get("http://wthrcdn.etouch.cn/weather_mini?citykey=101210101");
        String content = httpResult.getContent();
        JSONObject jsonObject = JSONObject.parseObject(content);
        System.out.println(jsonObject.toJSONString());
    }
    @Test
    public void test8801(){
        HttpResult httpResult = HttpUtil.get("http://localhost:8801");
        String content = httpResult.getContent();
        System.out.println(content);
    }
}
