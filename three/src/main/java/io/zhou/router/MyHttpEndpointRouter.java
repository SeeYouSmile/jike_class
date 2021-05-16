package io.zhou.router;

import java.util.*;

public class MyHttpEndpointRouter implements HttpEndpointRouter {

    private Map<String, Integer> roundRibbonMemberMap=new HashMap<>();
    private Map<String, List<Integer>> weightMemberMap=new HashMap<>();

    @Override
    public String route(List<String> urls,Router router) {
        switch (router){
            case ROUNDRIBBON:
                return roundRibbon(urls);
            case WEIGHT:
                return weight(urls);
            default:
                return random(urls);
        }
    }

    private String random(List<String> urls){
        int size = urls.size();
        Random random = new Random();
        return urls.get(random.nextInt(size));
    }

    private String roundRibbon(List<String> urls){
        String identity=urls.toString()+urls.hashCode();
        int size=urls.size();
        Integer index = roundRibbonMemberMap.get(identity);
        if(index==null){
            index=0;
            roundRibbonMemberMap.put(identity,index);
        }else{
            index=(index+1)%size;
            roundRibbonMemberMap.put(identity,index);
        }
        return urls.get(index);
    }

    private String weight(List<String> urls){
        String identity=urls.toString()+urls.hashCode();
        List<Integer> weights = weightMemberMap.get(identity);
        if(weights==null){
            weights=initWeight(urls);
        }

        int all=0;
        for (Integer weight : weights) {
            all+=weight;
        }
        Random random = new Random();
        int nextInt = random.nextInt(all)+1;
        for (int i = 0; i < weights.size(); i++) {
            nextInt-=weights.get(i);
            if(nextInt<=0){
                return urls.get(i);
            }
        }
        return urls.get(0);
    }

    /**
     * 设置权重路由的权重参数（长度不同使用默认方式，相当普通随机了）
     * @param urls 请求地址
     * @param weights 权重大小
     */
    @Override
    public void setWeightMemberMap(List<String> urls,List<Integer> weights){
        String identity=urls.toString()+urls.hashCode();
        if(urls.size()!=weights.size()){
            weights=initWeight(urls);
        }
        weightMemberMap.put(identity,weights);
    }

    private List<Integer> initWeight(List<String> urls){
        List<Integer> weights=new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            weights.add(1);
        }
        return weights;
    }
}
