package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SearchOptionsPage;

import java.util.Arrays;
import java.util.List;

public class countCars extends testPrepare {

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
        List<WebElement> cars = driver.findElements(By.cssSelector(".ads2023 .item"));

        for (WebElement car : cars) {
            String classAttr = car.getAttribute("class");
            List<String> classList = Arrays.asList(classAttr.trim().split("\\s+"));
            totalNumberOfCars++;
            if (classList.contains("VIP")) {
                totalNumberOfVIPCars++;
            } else if (classList.contains("TOP")) {
                totalNumberOfTopCars++;
            }

        }
        System.out.println(totalNumberOfCars);
        System.out.println(totalNumberOfTopCars);
        System.out.println(totalNumberOfVIPCars);
    }

}
