package tool.WebForge.classes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSetup {
    private static final Map<String, Supplier<WebDriver>> drivers = new HashMap<>();
    private static String selectedBrowser = "";

    private static void driverKeeper() {
        drivers.putIfAbsent("Chrome", () -> initializeChromeDriver());
        drivers.putIfAbsent("Firefox", () -> initializeFirefoxDriver());
        drivers.putIfAbsent("Edge", () -> initializeEdgeDriver());
        drivers.putIfAbsent("Internet Explorer", () -> initializeInternetExplorerDriver());
    }
    
    private static WebDriver initializer(WebDriverManager driverManager, WebDriver driver) {
		driverManager.setup();
		driver.manage().window().maximize();
	    return driver; 
    }
    
    private static WebDriver initializeChromeDriver() {
    	return initializer(WebDriverManager.chromedriver(), new ChromeDriver());
    }
    
    private static WebDriver initializeFirefoxDriver() {
    	return initializer(WebDriverManager.firefoxdriver(), new FirefoxDriver());
    }
    
    private static WebDriver initializeEdgeDriver() {
    	return initializer(WebDriverManager.edgedriver(), new EdgeDriver());
    }
    
    private static WebDriver initializeInternetExplorerDriver() {
    	return initializer(WebDriverManager.iedriver(), new InternetExplorerDriver());
    }

    public static WebDriver getDriver(){
        Supplier<WebDriver> driverSupplier = drivers.get(selectedBrowser);
        if (driverSupplier != null) {
            return driverSupplier.get();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + selectedBrowser);
        }
    }

    public static WebDriver initializeDriver(){ 
        try {
            driverKeeper();
            selectedBrowser = promptForBrowser();
            WebDriver currentDriver = getDriver();
            return currentDriver;
        } catch (IllegalArgumentException e) {
            throw e; 
        } catch (WebDriverException | IllegalStateException e) {
            throw new IllegalStateException("Browser does not exist, try another one:" + selectedBrowser, e);
        }
    }

    private static String promptForBrowser() {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice = 0;
            while (true) {
                System.out.println("Choose browser: ");
                int count = 1;
                Iterator<String> iterator = drivers.keySet().iterator();
                while (iterator.hasNext()) {
                    String browserName = iterator.next();
                    System.out.println(count + ". " + browserName);
                    count++;
                }
                System.out.print("Enter the number of your choice: ");
                
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice <= 0 || choice > drivers.size()) {
                        throw new IllegalArgumentException();
                    }
                    break; 
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + drivers.size() + ".");
                }
            }
            int count = 1;
            for (String browserName : drivers.keySet()) {
                if (count == choice) {
                    return browserName;
                }
                count++;
            }
            return null;
        }
    }

    public static void tearDown(WebDriver driver) {
        if (driver!= null) {
            driver.quit();
        }
    }
}

// get all links
// get all buttons 
// get all images 
// get all forms
// get all input fields

