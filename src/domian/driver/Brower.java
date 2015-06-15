package domian.driver;


import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Antinomy on 15/6/11.
 */
public class Brower {

    public static Brower instance;

    private WebDriver driver;

    private static Logger logger = Logger.getLogger(Brower.class);


    public static Brower getInstance() throws MalformedURLException {
        if (instance == null) {
            instance = new Brower();
        }
        return instance;
    }

    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    @Before
    public void open() throws MalformedURLException {
        logger.debug("Called open");
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
    }

    public void go(String url) {
        driver.get(url);
    }


    public void inputText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }


    public void click(By by) {
        driver.findElement(by).click();
    }

    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        driver.quit();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void back() {
        driver.navigate().back();
    }

    public void forward() {
        driver.navigate().forward();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void close() {
        driver.close();
    }
}
