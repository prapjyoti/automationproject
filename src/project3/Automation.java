package project3;
/*
Open url : http://automationpractice.com/index.php
Click on Sign In
Enter correct Email in Email field
Enter wrong Password in Password field
Click on Sign In button.
Expected Result:
Error Message “There is 1 error”

 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Automation {
    WebDriver driver;//global variable

    @Before
    public void setup() {//launch a web browser//open Url
        String baseUrl = "http://automationpractice.com/index.php";
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }
    // find the web element and do the action /Click on Sign In
    //Enter correct Email in Email field
    //Enter wrong Password in Password field
    //Click on Sign In button.

    @Test
    public void verifyErrorMessageWhenInvalidPasswordEntered() {

        WebElement signinlink = driver.findElement(By.linkText("Sign in"));
        signinlink.click();
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("xyz@yahoo.com");
        WebElement passwordField = driver.findElement(By.name("passwd"));
        passwordField.sendKeys("abc123");
        WebElement signinbtn = driver.findElement(By.xpath("//button[@id='SubmitLogin']"));
        signinbtn.click();
       // Expected Result:
        //Error Message “There is 1 error” using  Assert.assertEquals method

        String expectedErrorMessage = "There is 1 error";
        WebElement errorMessage = driver.findElement(By.xpath("//p[text()='There is 1 error']"));
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals("error message not displayed", expectedErrorMessage, actualErrorMessage);

    }

    @After
    public void teardown() {
        driver.quit();
    }

}
