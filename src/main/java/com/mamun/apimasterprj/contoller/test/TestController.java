package com.mamun.apimasterprj.contoller.test;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


//@CrossOrigin(origins="*")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class TestController {


    @GetMapping(path = "/getJSON", produces = "application/json")
    public Map<String, Object> getJSON() {

        Map<String, Object> returnJSON = new HashMap<>();
        returnJSON.put("result", "success: Welcome to Home Page");
        return returnJSON;

    }

    @GetMapping(path = "/getAboutPageData", produces = "application/json")
    public Map<String, Object> getAboutPageData() {

        Map<String, Object> returnJSON = new HashMap<>();
        returnJSON.put("result", "success: Welcome to About Page");
        return returnJSON;

    }


}
