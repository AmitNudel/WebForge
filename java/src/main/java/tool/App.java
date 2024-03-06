package tool;

import tool.WebForge.classes.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class App {
    private ElementsHandler elementsHandler = null;
    private WebDriver driver = null;
    private BasePageFunctionalities basicPage = null;
    
    @BeforeClass
    public void setup() {
		try {
            driver = WebDriverSetup.initializeDriver();
            elementsHandler = new ElementsHandler(driver);
            basicPage = new BasePageFunctionalities(driver);
            basicPage.getPage("https://www.google.com/");
            basicPage.loadPage();
        } catch (Exception e) {
            System.out.println("Error occurred during setup: " + e.getMessage());
        }
    }
    @Test(dataProvider = "baseData")
	public void testRun(){
        List<By> buttonLocators = elementsHandler.getAllButtonLocators();
        for (By locator : buttonLocators) {
            basicPage.clickOnButton(locator);
        }
    }
    @AfterClass
    public void tearDown(){
        WebDriverSetup.tearDown(driver);
    }
}
