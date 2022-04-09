package com.companyName.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    @FindBy(how = How.NAME, using = "username")
    public WebElement txtUserName;

    @FindBy(how = How.NAME, using = "password")
    public WebElement txtPassword;

    @FindBy(how = How.CSS, using = ".btn-block")
    public WebElement btnLogin;

    public void Login(String userName, String password) {
        txtUserName.sendKeys(userName);
        txtPassword.sendKeys(password);
    }

    public HomePage ClickLogin() {
        btnLogin.submit();
        System.out.println("click login");
        return new HomePage();
    }

}
