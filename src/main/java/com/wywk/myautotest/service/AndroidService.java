package com.wywk.myautotest.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * @ClassName: AndroidService
 * @Author: zsj
 * @Since:  2020/4/24 16:12
 * @Description:
 */

public class AndroidService {

    public String appiumTest() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.itest.info");

        String title = driver.getTitle();
        System.out.println(title);

        driver.close();
        return "success";
    }
}
