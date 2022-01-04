package com.frommstein.physicistscatalogueservice.services;

import com.frommstein.physicistscatalogueservice.models.Prize;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PrizeInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fetchNobelPrize")
    public void fetchPrizeAndAddToPrizeListOfPhysicist(List<Prize> prizeListForFoundPhysicist, String prizeId) {
        prizeListForFoundPhysicist.add(restTemplate.getForObject(
                "http://prize-info-service/prizes/" + prizeId,
                Prize.class));
    }

    public void fetchNobelPrize(List<Prize> prizeListForFoundPhysicist, String prizeId){
        prizeListForFoundPhysicist.add(
                new Prize("NPiPhy",
                        "Nobel Prize in Physics",
                        1902,
                        1135384,
                        "The dream of every physicist",
                        "https://en.wikipedia.org/wiki/Nobel_Prize_in_Physics")
        );
    }
}
