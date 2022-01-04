package com.frommstein.physicistscatalogueservice.services;

import com.frommstein.physicistscatalogueservice.models.Physicist;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class PhysicistInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getTheMostFamousPhysicist")
    public Physicist getPhysicist(String physicistId) {
        Physicist foundPhysicist = restTemplate.getForObject(
                "http://physicists-data-service/physicists/" + physicistId,
                Physicist.class);
        return foundPhysicist;
    }

    /*
     * We are returning Albert Einstein as the most prominent physicist in the history of mankind as a fallback physicist
     */

    public Physicist getTheMostFamousPhysicist(String physicistId){
        return new Physicist(
                "alberteinstein",
                "Albert Einstein",
                1879,
                "ETH Zurich",
                new ArrayList<>()
        );
    }
}
