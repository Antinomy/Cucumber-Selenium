package test;

/**
 * Created by Antinomy on 15/6/10.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.*;
import cucumber.api.junit.Cucumber;
import domian.driver.Brower;
import domian.lifeCycle.PageConfRepository;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import java.io.File;
import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"})
public class BaiduExample_Test {

    public Brower brower;
    private PageConfRepository page;

    private static Logger logger = Logger.getLogger(Brower.class);

    public BaiduExample_Test() throws Exception {

        page = new PageConfRepository("baiduExample.yaml");

        brower = Brower.getInstance();
        brower.open();
    }

    @Given("打开浏览器到baidu.com")
    public void go_to_baidu_com() throws MalformedURLException {
        logger.debug("打开浏览器到baidu.com");

        brower.go(page.getConf("baiduPage", "url"));
    }

    @Given("^输入 \"(.*?)\"$")
    public void input_in_text(String keyWord) throws Throwable {
        logger.debug("输入 " + keyWord);

        brower.inputText(page.getId("baiduPage", "input_id"), keyWord);

    }


    @When("^点击百度一下$")
    public void he_click_the_search_buttom() throws Throwable {
        logger.debug("点击百度一下");

        brower.click(page.getId("baiduPage", "searchBtn_id"));
    }


    @Then("^见到搜索结果$")
    public void he_should_be_see_search_result() throws Throwable {
        logger.debug("见到搜索结果");

        assertThat(brower.getTitle(), is("google_百度搜索"));
        brower.close();
    }

}
