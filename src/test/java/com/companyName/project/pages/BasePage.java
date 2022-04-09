package com.companyName.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;

public class BasePage {

    @Autowired
    public WebDriver webDriver;

    @PostConstruct
    public void initDriver() {
        PageFactory.initElements(webDriver, this);
    }

    public void Navigate(String url) {
        this.webDriver.navigate().to(url);
    }

}
