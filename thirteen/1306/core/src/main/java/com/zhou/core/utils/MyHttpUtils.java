package com.zhou.core.utils;

import com.zhou.core.dto.MyHttpResult;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MyHttpUtils {
    public static MyHttpResult get(String website){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(website);
        MyHttpResult myHttpResult = new MyHttpResult();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Map<String,String> header=new HashMap<>();
            HeaderIterator headerIterator = httpResponse.headerIterator();
            while(headerIterator.hasNext()){
                Header nextHeader = headerIterator.nextHeader();
                header.put(nextHeader.getName(),nextHeader.getValue());
            }
            String content = EntityUtils.toString(entity,"utf-8");
            myHttpResult.setCode(statusCode);
            myHttpResult.setHeader(header);
            myHttpResult.setContent(content);
            return myHttpResult;
        } catch (IOException e) {
            e.printStackTrace();
            myHttpResult.setCode(-1);
            myHttpResult.setContent(e.getMessage());
            return myHttpResult;
        }
    }
    public static MyHttpResult post(String website, Map<String,String> head , Map<String,String> params){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost=initParams(website,head,params);
        MyHttpResult myHttpResult = new MyHttpResult();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Map<String,String> header=new HashMap<>();
            HeaderIterator headerIterator = httpResponse.headerIterator();
            while(headerIterator.hasNext()){
                Header nextHeader = headerIterator.nextHeader();
                header.put(nextHeader.getName(),nextHeader.getValue());
            }
            String content = EntityUtils.toString(entity,"utf-8");
            myHttpResult.setCode(statusCode);
            myHttpResult.setHeader(header);
            myHttpResult.setContent(content);
            return myHttpResult;
        } catch (IOException e) {
            e.printStackTrace();
            myHttpResult.setCode(-1);
            myHttpResult.setContent(e.getMessage());
            return myHttpResult;
        }
    }
    public static MyHttpResult postJson(String website, Map<String,String> head , String jsonString){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost=initParamsJson(website,head,jsonString);
        MyHttpResult myHttpResult = new MyHttpResult();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Map<String,String> header=new HashMap<>();
            HeaderIterator headerIterator = httpResponse.headerIterator();
            while(headerIterator.hasNext()){
                Header nextHeader = headerIterator.nextHeader();
                header.put(nextHeader.getName(),nextHeader.getValue());
            }
            String content = EntityUtils.toString(entity,"utf-8");
            myHttpResult.setCode(statusCode);
            myHttpResult.setHeader(header);
            myHttpResult.setContent(content);
            return myHttpResult;
        } catch (IOException e) {
            e.printStackTrace();
            myHttpResult.setCode(-1);
            myHttpResult.setContent(e.getMessage());
            return myHttpResult;
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
