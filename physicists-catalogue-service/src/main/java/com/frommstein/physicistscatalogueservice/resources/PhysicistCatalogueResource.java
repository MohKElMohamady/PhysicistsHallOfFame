package com.frommstein.physicistscatalogueservice.resources;

import com.frommstein.physicistscatalogueservice.models.CatalogueItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/physicists/")
public class PhysicistCatalogueResource {


    @RequestMapping("/{physicistId}")
    public List<CatalogueItem> getCatalogue(@PathVariable String physicistId){

        /*return Collections.singletonList(new CatalogueItem("Max Born", 1882, "Universität Göttingen",
                List.of("Nobel Prize", "Max Planck Medal", "Fellow of the Royal Society")));*/

        /*
         * What should the PhysicistCatalogueResource do?
         * 1) Call the Physicist Data Service to receive all the physicists
         * 2) For each prize ID, call the Prize Info Service and get the detail
         * 3) Put them all together
         */

        return null;

    }

}
