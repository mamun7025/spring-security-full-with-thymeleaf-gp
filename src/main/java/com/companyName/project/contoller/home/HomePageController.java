package com.companyName.project.contoller.home;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {


    @PreAuthorize("permitAll()")
    @RequestMapping({ "/", "/home" })
    public String index(){
        return "home";
    }


}
