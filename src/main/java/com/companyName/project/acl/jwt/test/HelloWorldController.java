package com.companyName.project.acl.jwt.test;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/api")
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello() {

        System.out.println("it works...@/api/hello");
        return "Hello World";

    }


}
