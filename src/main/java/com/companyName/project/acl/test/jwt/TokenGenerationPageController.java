package com.companyName.project.acl.test.jwt;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class TokenGenerationPageController {


    @RequestMapping("/generate-token")
    public String userPassEntryForm(){
        return "acl/test/jwt/token-generation-page";
    }


}
