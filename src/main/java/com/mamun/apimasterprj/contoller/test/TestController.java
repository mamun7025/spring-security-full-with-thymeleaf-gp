package com.mamun.apimasterprj.contoller.test;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class TestController {


    @GetMapping(path = "/getJSON", produces = "application/json")
    public Map<String, Object> getJSON() {

        Map<String, Object> returnJSON = new HashMap<>();
        returnJSON.put("result", "success");
        return returnJSON;

    }


}
