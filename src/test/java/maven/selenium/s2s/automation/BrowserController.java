package maven.selenium.s2s.automation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.safari.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.edge.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
public class BrowserController {
	WebDriver driver;
	Properties prop;
	Map<String, String> testData;
	
	public BrowserController() throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream("src/test/resources/frameworkConfiguration.properties"));
		
		String browserName = prop.getProperty("browser");
		
		int timeout = Integer.parseInt(prop.getProperty("timeout"));
		
		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}
	public WebDriver getDriver() {
		return driver;
	}
	
	public void openUrl(String url) {
		driver.get(url);
	}
	public void quitBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebElement findById(String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement findByName(String name) {
        return driver.findElement(By.name(name));
    }
    
    public WebElement findByClassName(String className) {
    	return driver.findElement(By.className(className));
    }

    public WebElement findByXPath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }
    public List<WebElement> findByXpathList(String xpath){
    	return driver.findElements(By.xpath(xpath));
    }
    public WebElement findByCss(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }
    public boolean isElementPresent(String xpath, int timeoutMillis) {
    	try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeoutMillis));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void click(WebElement element) {
        element.click();
    }
    
    public void type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
    
    // Set implicit wait
    public void setImplicitWait(int millis) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(millis));
    }

    // Maximize browser window
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    // Unmaximize (restore window)
    public void unmaximizeWindow() {
        driver.manage().window().setSize(new Dimension(1024, 768)); // Default window size or adjustable
    }
    public void waitFor(long millis){
    	try {
    		new WebDriverWait(driver, Duration.ofMillis(millis)).until(driver -> false);
    	} catch(Exception e) {
    		return ; 
    	}
        // dummy condition that always returns true after timeout
    }
    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }
	public void takeScreenShot(String screenshotName) throws IOException {
		String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_ss").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE); // taking a screenshot
		FileUtils.copyFile(file, new File("src/test/resources/screenshots" + screenshotName + "_" + timestamp + ".png")); // copy to destination
		System.out.println("Screenshot captured:" + screenshotName);
	}
}
