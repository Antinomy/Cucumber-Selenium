
import domian.driver.DriverFactory;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Created by Antinomy on 15/7/8.
 */
public class DriverFactoryTest {

    @Test
    public void test() throws Exception {

        WebDriver driver = null;

//        driver = DriverFactory.getRunningEnv("local_any_firefox");
//        assertThat(driver, notNullValue());
//        driver.close();
//
//        driver = DriverFactory.getRunningEnv("local_mac_firefox");
//        assertThat(driver, notNullValue());
//        driver.close();
//
//        driver = DriverFactory.getRunningEnv("local_mac_chrome");
//        assertThat(driver, notNullValue());
//        driver.close();

        driver = DriverFactory.getRunningEnv("remote_any_firefox");
        assertThat(driver, notNullValue());
        driver.close();

    }
}
