package domian.driver;

import domian.lifeCycle.NoConfigException;
import domian.lifeCycle.PageConfRepository;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;

/**
 * Created by Antinomy on 15/7/8.
 */
public class DriverFactory {

    private static Logger logger = Logger.getLogger(DriverFactory.class);

    public static WebDriver getRunningEnv(String ruuningEnv) throws Exception {

        WebDriver result = null;

        PageConfRepository env = new PageConfRepository("env/runningEnv.yaml");

        DesiredCapabilities aDesiredcap = getDesiredCapabilities(ruuningEnv, env);

        logger.info("read ruuningEnv: ["+ruuningEnv+"]!!");
        String runAt = env.getConf(ruuningEnv, "RunAt");
        logger.info("RunAt: ["+runAt+"]!!");

        if (runAt.equalsIgnoreCase("local")) {
            result = newLocalDriver(aDesiredcap);
            logger.info("new Local Driver Start !!");
            return result;
        }

        result = new RemoteWebDriver(new URL(runAt), aDesiredcap);
        logger.info("new Remote WebDriver Start !!!");
        return result;
    }

    private static DesiredCapabilities getDesiredCapabilities(String ruuningEnv, PageConfRepository env) throws NoConfigException {
        DesiredCapabilities aDesiredcap = new DesiredCapabilities();

        String browerName = env.getConf(ruuningEnv, "BrowserName");
        aDesiredcap.setBrowserName(browerName);

        String browerVersion = env.getConf(ruuningEnv, "Version");
        if (browerVersion != null && !browerVersion.equalsIgnoreCase("any"))
            aDesiredcap.setVersion(browerVersion);

        String platform = env.getConf(ruuningEnv, "Platform");
        aDesiredcap.setPlatform(getPlatform(platform));

        initDriverPath(env, browerName, platform);

        return aDesiredcap;
    }

    private static void initDriverPath(PageConfRepository env, String browerName, String platform) {

        if (isChrome(browerName) && isMac(platform)) {
            String chromeDriverPath = env.getDriverPath("ChromeDriver", "chromedriver");
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            logger.info("InitDriverPath : " + chromeDriverPath);
        }

    }

    private static WebDriver newLocalDriver(DesiredCapabilities aDesiredcap) {
        WebDriver result = null;
        String browserName = aDesiredcap.getBrowserName();

        if (isFirefox(browserName)) {
            ProfilesIni allProfiles = new ProfilesIni();
            FirefoxProfile profile = allProfiles.getProfile("default");
            profile.setEnableNativeEvents(true);
            profile.setPreference("IsRelative",0);

            result = new FirefoxDriver(profile);
            return result;
        }

        if (isSafari(browserName)) {
            result = new SafariDriver(aDesiredcap);
            return result;
        }

        if (isChrome(browserName)) {
            result = new ChromeDriver(aDesiredcap);
            return result;
        }

        return result;
    }

    private static boolean isChrome(String browserName) {
        return browserName.equalsIgnoreCase("chrome");
    }

    private static boolean isSafari(String browserName) {
        return browserName.equalsIgnoreCase("safari");
    }

    private static boolean isFirefox(String browserName) {
        return browserName.equalsIgnoreCase("firefox");
    }

    private static Platform getPlatform(String platform) {

        if (isMac(platform))
            return Platform.MAC;

        if (isLinux(platform))
            return Platform.LINUX;

        if (isWindows(platform))
            return Platform.WINDOWS;

        return Platform.ANY;
    }

    private static boolean isWindows(String platform) {
        return platform.equalsIgnoreCase("WINDOWS");
    }

    private static boolean isLinux(String platform) {
        return platform.equalsIgnoreCase("LINUX");
    }

    private static boolean isMac(String platform) {
        return platform.equalsIgnoreCase("MAC");
    }

}
