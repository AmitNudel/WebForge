package tool.WebForge.classes;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePageFunctionalities {
    private WebDriver driver = null;

    private WebDriver getDriver(){
        return driver;
    }

    private void setDriver(WebDriver driver){
        this.driver = driver;
    }  

    public BasePageFunctionalities(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        setDriver(driver);
    }

    public void clickOnButton(By locator) {
        try {
            WebElement element = findElement(locator);
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void getPage(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebElement findElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadPage(){
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }
}
