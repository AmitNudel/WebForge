package tool.WebForge.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ElementsHandler {
    private WebDriver driver = null;
    
    public ElementsHandler(WebDriver driver) {
        this.driver = driver;
    }

    //continue 
    public List<WebElement> getAllLinks() {
        return driver.findElements(By.tagName("a"));
    }

    public List<By> getAllButtonLocators() {
        List<By> buttonLocators = new ArrayList<>();
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        for (WebElement button : buttons) {
            buttonLocators.add(By.id(button.getAttribute("id")));
        }
        return buttonLocators;
    }

    //continue 
    public List<WebElement> getAllImages() {
        return driver.findElements(By.tagName("img"));
    }

    //continue 
    public List<WebElement> getAllForms() {
        return driver.findElements(By.tagName("form"));
    }

    //continue 
    public List<WebElement> getAllInputFields() {
        return driver.findElements(By.tagName("input"));
    }
}