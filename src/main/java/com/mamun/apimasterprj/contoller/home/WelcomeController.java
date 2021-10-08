package com.mamun.apimasterprj.contoller.home;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {


    @PreAuthorize("permitAll()")
    @RequestMapping({ "/", "/welcome" })
    @ResponseBody
    public String printHello(){
        return ("<h1>Welcome</h1>");
    }

    // its not correct
    @GetMapping("/welcome-2")
    public String printHello2(){
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/welcome-3")
    public String printHello3(){
        return "welcome";
    }



}
