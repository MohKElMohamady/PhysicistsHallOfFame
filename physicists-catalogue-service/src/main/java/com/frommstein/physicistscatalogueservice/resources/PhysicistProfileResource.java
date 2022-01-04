package com.frommstein.physicistscatalogueservice.resources;

import com.frommstein.physicistscatalogueservice.models.PhysicistProfile;
import com.frommstein.physicistscatalogueservice.models.Physicist;
import com.frommstein.physicistscatalogueservice.models.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/*import org.springframework.web.reactive.function.client.WebClient;*/

import java.util.*;

@RestController
@RequestMapping("/physicists/")
public class PhysicistProfileResource {

    @Autowired
    private RestTemplate restTemplate;

    /*@Autowired
    private WebClient.Builder webClientBuilder;*/

    @RequestMapping("/{physicistId}")
    public PhysicistProfile getCatalogue(@PathVariable String physicistId){


        /*return Collections.singletonList(new CatalogueItem("Max Born", 1882, "Universität Göttingen",
                List.of("Nobel Prize", "Max Planck Medal", "Fellow of the Royal Society")));*/


        Physicist foundPhysicist = restTemplate.getForObject(
                "http://physicists-data-service/physicists/" + physicistId,
                Physicist.class);

        for(String prize: foundPhysicist.getListOfWonAwards()){
            System.out.println(prize);
        }

        /*Physicist foundPhysicist = webClientBuilder.build()
                .get()
                .uri("http://localhost:9999/physicists/levlandau")
                .retrieve()
                .bodyToMono(Physicist.class)
                .block();*/
        /*
         * What should the PhysicistCatalogueResource do?
         * 1) Call the Physicist Data Service to receive the physicist that corresponds to the physicist id.
         */

        // Old implementation, hardcoding the prizes received instead of calling the prize-info-service microservice

        /*List<Prize> prizes = Arrays.asList(
                new Prize("NPiPhy",
                        "Nobel Prize in Physics",
                        1901,
                        1135384,
                        "The dream of all physicists",
                        "https://en.wikipedia.org/wiki/Nobel_Prize_in_Physics"),
                new Prize("MPM",
                        "Max Planck institute",
                        1929,
                        1135384,
                        "The Max Planck medal is the highest award of the German Physical Society",
                        "https://en.wikipedia.org/wiki/Max_Planck_Medal")

        );*/

        /*
         * We are preparing a list that contains all prize objects (prize infomration) that will be added to the
         * instance of PhysicistProfile that we will have.
         */

        List<Prize> prizeListForFoundPhysicist = new ArrayList<>();

        /*
         * We are looping through all the prizes that the physicist has won to retrieve their ids and do a rest call
         * for the prize-detail-service microservice to receive an object of type prize and then add it to the list
         * of prizes that contain all the information about the prizes.
         */

        for(String prizeId : foundPhysicist.getListOfWonAwards()){

            System.out.println(prizeId);

            prizeListForFoundPhysicist.add(restTemplate.getForObject(
                    "http://prize-info-service/prizes/" + prizeId,
                    Prize.class));
        }

         /*
         * 2) For each prize ID, call the Prize Info Service and get the detail
          */

        PhysicistProfile profileOfResult = new PhysicistProfile(
                foundPhysicist,
                prizeListForFoundPhysicist
        );

         /*
          * 3) Put them all together
          */

        return profileOfResult;

    }

}
