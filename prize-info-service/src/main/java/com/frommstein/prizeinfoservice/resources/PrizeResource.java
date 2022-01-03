package com.frommstein.prizeinfoservice.resources;

import com.frommstein.prizeinfoservice.models.Prize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/prizes")
public class PrizeResource {


    @RequestMapping("/{prizeId}")
    public Prize getPrizeInfo(@PathVariable String prizeId){
        return new Prize(prizeId,
                "Nobel Prize in Physics",
                1901,
                1135384,
                "Every physicist's dream",
                "https://en.wikipedia.org/wiki/Nobel_Prize_in_Physics",
                new HashMap<>());
    }

}

