package world; /**
 * Created by Antinomy on 15/7/9.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.junit.Cucumber;
import domian.driver.Brower;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
public class World {

    private Brower brower;
    private static Logger logger = Logger.getLogger(World.class);

    public World() throws Throwable {
        brower = Brower.getInstance();
    }

    @Given("^在本地环境$")
    public void 在本地环境() throws Throwable {
        logger.info("在本地环境");

        brower.open();
    }

    @Given("^在目标环境 \"(.*?)\"$")
    public void 在目标环境(String runningEnv) throws Throwable {
        logger.info("在目标环境: " + runningEnv);

        brower.open(runningEnv);
    }

    @Given("^关闭浏览器$")
    public void 关闭浏览器() throws Throwable {
        logger.info("退出浏览器");

        brower.close();
    }

    @Given("^退出浏览器")
    public void 退出浏览器() throws Throwable {
        logger.info("退出浏览器");

        brower.quit();
    }


}
