package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SearchOptionsPage;

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
        searchOptionsPage.navigateTo(webDriver,SearchOptionsPage.PAGE_URL);
        searchOptionsPage.isUrlLoaded(webDriver,SearchOptionsPage.PAGE_URL);
        webDriver.findElement(By.id("cookiescript_accept")).click();
        searchOptionsPage.setSearchingCriteria(brand,model);
        Thread.sleep(5000);
        WebElement resultsInfo = webDriver.findElement(By.xpath("//div[contains(text(), 'от общо')]"));
        String text = resultsInfo.getText();
        String[] tokens = text.trim().split("\\s+");
        String totalStr = tokens[tokens.length - 1]; // Последният елемент -> "143"
        int total = Integer.parseInt(totalStr);
        System.out.println(total);
    }

}
