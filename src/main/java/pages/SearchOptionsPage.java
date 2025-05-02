package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;



public class SearchOptionsPage{
    private final WebDriver webDriver;
    public static final String PAGE_URL = "https://www.mobile.bg/search/avtomobili-dzhipove";


    @FindBy(xpath = "//input[@class='akCustomSelectInput' and @name='marka']")
    private WebElement Brand;
    @FindBy(xpath = "//input[@class = 'akCustomSelectInput' and @name= 'model_show']")
    private WebElement Model;
    @FindBy(id = "eimg88")
    private WebElement FourWheelDrive;
    @FindBy(xpath = "//a[@class = 'SEARCH_btn MT6']")
    private WebElement SearchButton;
    @FindBy(xpath = "//div[@id=\"akSearchMarki\"]")
    private WebElement brandSearchResult;
    @FindBy(id = "akSearchModeliArrow")
    private WebElement modelSearchResult;



    public SearchOptionsPage(WebDriver webDriver) {
            this.webDriver = webDriver;
            PageFactory.initElements(webDriver, this);
    }

    public boolean isUrlLoaded(WebDriver webDriver, String PAGE_URL){
        WebDriverWait explicitWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        try{
            explicitWait.until(ExpectedConditions.urlToBe(PAGE_URL));
        }catch(TimeoutException ex) {
            return false;
        }
        return true;
    }

    public void navigateTo(WebDriver webDriver, String PAGE_URL){
        webDriver.get(PAGE_URL);
    }

    public void setSearchingCriteria(String brand, String model) throws InterruptedException {

        Brand.sendKeys(brand);
        brandSearchResult.click();
        Thread.sleep(1000); //
        Model.click();
        WebElement golfOption = webDriver.findElement(By.xpath("//input[@type='checkbox' and @data-value='Golf']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", golfOption);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(golfOption));
        golfOption.click();
        Actions actions = new Actions(webDriver);
        actions.moveToElement(FourWheelDrive).click().perform();
        SearchButton.click();
    }

}
