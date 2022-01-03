package com.frommstein.prizeinfoservice.resources;

import com.frommstein.prizeinfoservice.models.Prize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prizes")
public class PrizeResource {

    private List<Prize> prizeList = Arrays.asList(
            new Prize("NPiPhy",
                    "Nobel Prize in Physics",
                    1901,
                    1135384,
                    "Every physicist's dream",
                    "https://en.wikipedia.org/wiki/Nobel_Prize_in_Physics",
                    new HashMap<>()),
            new Prize("SP",
                    "Stalin Prize",
                    1941,
                    670500,
                    "the Soviet Union's state honor",
                    "https://en.wikipedia.org/wiki/USSR_State_Prize",
                    new HashMap<>()),
            new Prize("MPM",
                    "Max Planck Medal",
                    1929,
                    0,
                    "The Max Planck medal is the highest award of the German Physical Society",
                    "https://en.wikipedia.org/wiki/Max_Planck_Medal",
                    new HashMap<>()),
            new Prize("PMF",
                    "Presidential Medal of Freedom",
                    1963,
                    0,
                    "The Presidential Medal of Freedom is an award bestowed " +
                            "by the president of the United States to recognize people who have made an " +
                            "especially meritorious contribution to the security or national interests of the United " +
                            "States, world peace, cultural or other significant public or private endeavors.",
                    "https://en.wikipedia.org/wiki/Presidential_Medal_of_Freedom",
                    new HashMap<>())

    );

    @RequestMapping("/{prizeId}")
    public Prize getPrizeInfo(@PathVariable String prizeId){

        /*
         * What we are doing here, is we are retrieving the prize from the "saved" list of all prizes present
         * by providing a predicate that compares the received prize id from the api call to the prize ids present
         * in the slides.
         */

        Optional<Prize> foundPrize = prizeList.stream()
                .filter(p -> p.getPrizeId().equals(prizeId))
                .findFirst();


        return foundPrize.get();
    }

}

