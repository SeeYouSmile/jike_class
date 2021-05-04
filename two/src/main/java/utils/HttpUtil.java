package utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import pojo.HttpResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class HttpUtil {
    public static HttpResult get(String website){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(website);
        HttpResult httpResult = new HttpResult();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String content = EntityUtils.toString(entity,"utf-8");
            httpResult.setCode(statusCode);
            httpResult.setContent(content);
            return httpResult;
        } catch (IOException e) {
            e.printStackTrace();
            httpResult.setCode(-1);
            httpResult.setContent(e.getMessage());
            return httpResult;
        }
    }
    public static HttpResult post(String website, Map<String,String> head ,Map<String,String> params){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost=initParams(website,head,params);
        HttpResult httpResult = new HttpResult();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String content = EntityUtils.toString(entity,"utf-8");
            httpResult.setCode(statusCode);
            httpResult.setContent(content);
            return httpResult;
        } catch (IOException e) {
            e.printStackTrace();
            httpResult.setCode(-1);
            httpResult.setContent(e.getMessage());
            return httpResult;
        }
    }
    public static HttpResult postJson(String website, Map<String,String> head ,String jsonString){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost=initParamsJson(website,head,jsonString);
        HttpResult httpResult = new HttpResult();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String content = EntityUtils.toString(entity,"utf-8");
            httpResult.setCode(statusCode);
            httpResult.setContent(content);
            return httpResult;
        } catch (IOException e) {
            e.printStackTrace();
            httpResult.setCode(-1);
            httpResult.setContent(e.getMessage());
            return httpResult;
        }
    }

    private static HttpPost initParams(String website,Map<String,String> head,Map<String,String> params){
        HttpPost httpPost = new HttpPost(website);
        if(head!=null){
            for(Map.Entry<String,String> entry:head.entrySet()){
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        StringBuffer sb=new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        if(sb.length()>0){
            sb.substring(0,sb.length()-1);
        }
        try {
            StringEntity stringEntity = new StringEntity(sb.toString());
            httpPost.setEntity(stringEntity);
            return httpPost;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static HttpPost initParamsJson(String website,Map<String,String> head,String jsonString){
        HttpPost httpPost = new HttpPost(website);
        if(head!=null){
            for(Map.Entry<String,String> entry:head.entrySet()){
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        httpPost.setHeader("Content-Type","application/json");
        try {
            StringEntity stringEntity = new StringEntity(jsonString);
            httpPost.setEntity(stringEntity);
            return httpPost;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
