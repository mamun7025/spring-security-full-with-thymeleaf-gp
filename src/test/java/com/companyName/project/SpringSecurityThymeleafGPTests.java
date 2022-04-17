package com.companyName.project;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import com.companyName.project.pages.HomePage;
import com.companyName.project.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class SpringSecurityThymeleafGPTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringSecurityThymeleafGPTests.class);

    @Value("${app.url}")
    private String appUrl;

    @Value("chrome,firefox,edge")
    private List<String> browsers;

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private HomePage homePage;

    @Autowired
    private WebDriver webDriver;

    ATUTestRecorder recorder;


    @BeforeEach
    public void beforeEachTest() throws ATUTestRecorderException {
        logger.error("@....beforeEachTest");

//        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
//        Date date = new Date();
//        //Created object of ATUTestRecorder
//        //Provide path to store videos and file name format.
//        recorder = new ATUTestRecorder("D:\\ScriptVideos\\","TestVideo-"+dateFormat.format(date),false);
//        //To start video recording.
//        recorder.start();
//        logger.error("@....setup");
    }


    @Test
    void contextLoads() throws Exception {

//        ScreenRecorderUtil.startRecord("contextLoads");
        System.out.println(appUrl);

		loginPage.Navigate(appUrl);
        Thread.sleep(2000);
		loginPage.Login("admin", "1234567");
        Thread.sleep(2000);
		loginPage.ClickLogin();

//        homePage.ClickLogin();
//        ScreenRecorderUtil.stopRecord();

    }


    @AfterEach
    public void afterEachTest() throws ATUTestRecorderException {
        logger.error("@....afterEachTest");
//        recorder.stop();
    }


}
