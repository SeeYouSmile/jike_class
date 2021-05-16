package io.zhou.router;

import java.util.List;

public interface HttpEndpointRouter {
    
    String route(List<String> endpoints,Router router);
    
    // Load Balance
    // Random
    // RoundRibbon 
    // Weight
    // - server01,20
    // - server02,30
    // - server03,50

    void setWeightMemberMap(List<String> urls,List<Integer> weights);
    
}
