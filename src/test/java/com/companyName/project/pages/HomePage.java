package com.companyName.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends BasePage {

    @FindBy(how = How.LINK_TEXT, using = "User")
    public WebElement lnkUserList;

    @FindBy(how = How.LINK_TEXT, using = "Role")
    public WebElement lnkRoleList;

    @FindBy(how = How.LINK_TEXT, using = "Security User-Role")
    public WebElement lnkSecurityUserRoleList;


    public LoginPage clickUserListLink() {
        lnkUserList.click();
        System.out.println("Click Login");
//        return new LoginPage();
        return null;
    }

    public void clickRoleList() {
        lnkRoleList.click();
    }

    public void clickSecurityUserRoleList() {
        lnkSecurityUserRoleList.click();
    }

}
