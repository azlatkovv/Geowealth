package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    @FindBy(xpath = "//div[@id=\"akSearchModeli\"]")
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

    public void setSearchingCriteria(String brand, String model)  {
        Brand.sendKeys(brand);
        brandSearchResult.click();
        Actions actions = new Actions(webDriver);
       // actions.moveToElement(dropdown).click().perform();
        Model.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOf(modelSearchResult));
       // Actions actions = new Actions(webDriver);
       // actions.moveToElement(dropdown).click().perform();

       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("data-value= Golf")));
      //  Model.findElement(By.name(model)).click();

      // FourWheelDrive.click();
        //SearchButton.click();
    }

}
