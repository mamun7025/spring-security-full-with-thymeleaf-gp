package com.companyName.project.contoller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


@Controller
public class WelcomeController {


    @Autowired
    private Environment env;
    @Autowired
    private ServletContext context;

    @Value("${server.servlet.context-path}")
    private String contextRoot;


    @PreAuthorize("permitAll()")
    @RequestMapping({"/welcome" })
    @ResponseBody
    public String printHello( HttpServletRequest request ){

        String contextPath = request.getContextPath();
        String contextPath2 = env.getProperty("server.servlet.context-path");
        String contextPath3 = context.getContextPath();

        System.out.println("@Welcome Controller");
        System.out.println(contextPath);
        System.out.println(contextPath2);
        System.out.println(contextPath3);
        System.out.println(contextRoot);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println(securityContext.getAuthentication().getName());
        System.out.println(securityContext.getAuthentication().getAuthorities());
        System.out.println(securityContext.getAuthentication().getPrincipal());
        System.out.println(securityContext.getAuthentication().getDetails());

        String responseStr;
        responseStr = "" +
                "<h1>Welcome</h1>" +
                "<a href='"+contextPath+"/logout'>Logout</a>";

        return (responseStr);

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
