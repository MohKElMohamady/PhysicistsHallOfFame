package com.frommstein.physicistsdataservice.resources;

import com.frommstein.physicistsdataservice.models.Physicist;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/physicists")
public class PhysicistResource {

    @RequestMapping("/{physicistId}")
    public Physicist getPhysicist(@PathVariable String physicistId){
        return new Physicist(physicistId, "Max Born", 1885, "Unversity of Göttigen",
                List.of("999", "444", "555"));
    }
}
