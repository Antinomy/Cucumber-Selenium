package domian.driver;


import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Antinomy on 15/6/11.
 */
public class Brower {

    public static Brower instance;

    private WebDriver driver;
    private WebDriverWait wait;

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
    public void open() throws Exception {

        String ruuningEnv = "local_any_firefox";
        open(ruuningEnv);
    }

    public void open(String ruuningEnv) throws Exception {
        logger.debug("Running on :" + ruuningEnv);

        driver = DriverFactory.getRunningEnv(ruuningEnv);
        driver.manage().deleteAllCookies();

        wait = new WebDriverWait(driver, 10);
    }

    public void go(String url) {
        driver.get(url);
    }


    public void inputText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public void inputUEditorText(String text) {
        String script = "editor.setContent('<p>" + text + "</p>');";
        ((JavascriptExecutor) driver).executeScript(script);
    }

    public String readText(By by) {
        return driver.findElement(by).getText();
    }


    public void click(By by) {
        driver.findElement(by).click();
    }

    /**
     * Embed a screenshot in test report if test is marked as failed
     */
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

    public void quit() {
        driver.quit();
    }

    public void wait(int second) {
        driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
    }

    public void absolutelyWait(int second) throws InterruptedException {
        Thread.sleep(second * 1000);
    }

    public void waitFor(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void selectText(By target, String text) {
        Select select = new Select(driver.findElement(target));
        for (WebElement ops : select.getOptions()) {
            if (ops.getText().equals(text)) {
                ops.click();
                return;
            }
        }
    }

    public void logAllHyperLinkText() {

        List<WebElement> links = driver.findElements(By.tagName("a"));

        for (WebElement ops : links) {
            logger.info(ops.getText());
        }

    }

    public void clickLinkText(String text) {
        By target = By.linkText(text);
        driver.findElement(target).click();
    }

    public void switchFirstWindow() {
        for (String winHandle : driver.getWindowHandles()) {

            switchToWindow(winHandle);
            return;
        }
    }

    private void switchToWindow(String winHandle) {
        logger.info("switched to " + winHandle);
        driver.switchTo().window(winHandle);
        logger.info(" on tittle : " + getTitle());
    }

    public void switchLastWindow() {
        int size = driver.getWindowHandles().size();
        if (size <= 1)
            return;

        for (String winHandle : driver.getWindowHandles()) {
            switchToWindow(winHandle);
        }
    }

    public void switchFrame(String frameName) {
        driver.switchTo().frame(frameName);
    }

    public void switchFrame(By frameXpath) {
        WebElement ifame = driver.findElement(frameXpath);
        driver.switchTo().frame(ifame);
    }

    public void switchBack() {
        driver.switchTo().defaultContent();
    }

    public void uploadFile(By uploadBtn, String uploadFilePath) throws InterruptedException {
        WebElement upload = driver.findElement(uploadBtn);

        upload.sendKeys(uploadFilePath);
        absolutelyWait(1);
    }

    public void viewPageSource() {
        logger.info(driver.getPageSource());
    }

}
