package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SearchOptionsPage;


import java.util.Arrays;
import java.util.List;

public class countCars extends testPrepare {

        private static final Logger logger = LoggerFactory.getLogger(countCars.class);

    @DataProvider(name="brandAndModel")
    public Object[][] brandAndModel(){
        return new Object[][]{
                {"VW","Golf"}};
    }

    @Test(dataProvider = "brandAndModel")
    public void findTotalResults(String brand, String model) throws InterruptedException {
        WebDriver driver = getDriver();
        SearchOptionsPage searchOptionsPage = new SearchOptionsPage(driver);
        searchOptionsPage.navigateTo(webDriver, SearchOptionsPage.PAGE_URL);
        searchOptionsPage.isUrlLoaded(webDriver, SearchOptionsPage.PAGE_URL);
        webDriver.findElement(By.id("cookiescript_accept")).click();
        searchOptionsPage.setSearchingCriteria(brand, model);
        String maxPage = webDriver.findElement(By.cssSelector("div.saveSlink.gray")).getText();
        int pageNumber = Integer.parseInt(maxPage);

        int totalNumberOfCars = 0;
        int totalNumberOfVIPCars = 0;
        int totalNumberOfTopCars = 0;

        for(int i = 1; i<=pageNumber; i++) {

            List<WebElement> cars = driver.findElements(By.cssSelector(".ads2023 .item"));

            for (WebElement car : cars) {

                String classAttr = car.getAttribute("class");
                List<String> classList = Arrays.asList(classAttr.trim().split("\\s+"));

                if (classList.contains("VIP")) {
                    totalNumberOfVIPCars++;
                    totalNumberOfCars++;
                } else if (classList.contains("TOP")) {
                    totalNumberOfTopCars++;
                    totalNumberOfCars++;
                } else if (classList.contains("fakti")) {

                } else{
                    totalNumberOfCars++;
                }
            }

            List<WebElement> pages = webDriver.findElements(By.xpath("//a[@class='saveSlink ']"));
            for (WebElement page:pages){
                String pageNumberText = page.getText();
                int pageInt = Integer.parseInt(pageNumberText);
                if(pageInt == i+1){
                    page.click();
                   break;
                }
            }
        }
        logger.info("Total number of cars: {}", totalNumberOfCars);
        logger.info("Total number of TOP cars: {}", totalNumberOfTopCars);
        logger.info("Total number of VIP cars: {}", totalNumberOfVIPCars);
    }
}
