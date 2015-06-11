package domian.driver;


import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Antinomy on 15/6/11.
 */
public class SeleniumDriver {

    public static WebDriver driver;


    public static WebDriver getDriver() throws MalformedURLException {
        if(driver == null)
            openBrowser();

        return driver;
    }

    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    @Before
    public static void openBrowser() throws MalformedURLException {
        System.out.println("Called openBrowser");
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
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
}
