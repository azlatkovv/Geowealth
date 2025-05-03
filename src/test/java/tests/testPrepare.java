package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class testPrepare {
    protected WebDriver webDriver;

    @BeforeSuite
    protected void setupTestSuite()  {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    protected void setUpTest(){
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().window().maximize();
        this.webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
    }

    @AfterMethod
    protected void tearDownTest(ITestResult testResult){
        quitDriver();
    }

    @AfterSuite
    protected WebDriver getDriver(){
        return this.webDriver;
    }

    private void quitDriver(){
        if (this.webDriver != null) {
            this.webDriver.quit();
        }
    }
}
