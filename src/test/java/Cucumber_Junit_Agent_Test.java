/**
 * Created by Antinomy on 15/7/15.
 */
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"junit:reports/junit/junit.xml", "html:reports/html"})
public class Cucumber_Junit_Agent_Test {
}