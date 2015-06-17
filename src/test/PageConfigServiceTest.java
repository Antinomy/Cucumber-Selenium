package test;

import domian.lifeCycle.PageConfRepository;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Antinomy on 15/6/16.
 */
public class PageConfigServiceTest {

    @Test
    public void test() throws Exception {

        String yamlFilePath = "baiduExample.yaml";

        PageConfRepository page = new PageConfRepository(yamlFilePath);

        String url = page.getConf("baiduPage","url");
        assertThat(url,is("https://www.baidu.com"));

        assertThat(page.getConf("baiduPage","input_id"),is("kw"));
        assertThat(page.getConf("baiduPage","searchBtn_id"),is("su"));

        assertThat(page.getId("baiduPage", "input_id"),is(By.id("kw")));
    }
}
