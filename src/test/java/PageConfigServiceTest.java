import domian.lifeCycle.NoConfigException;
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

        String url = page.getConf("baiduPage", "url");
        assertThat(url, is("https://www.baidu.com"));

        assertThat(page.getConf("baiduPage", "input_id"), is("kw"));
        assertThat(page.getConf("baiduPage", "searchBtn_id"), is("su"));

        assertThat(page.getId("baiduPage", "input_id"), is(By.id("kw")));
        assertThat(page.getId("baiduPage", "input_xpath"),
                is(By.id("/html/body/div[3]/div[1]/div/div[1]/div/form/span[1]/input")));
    }

    @Test(expected = NoConfigException.class)
    public void testNull() throws Exception {

        String yamlFilePath = "baiduExample.yaml";

        PageConfRepository page = new PageConfRepository(yamlFilePath);

        String url = page.getConf("baiduPage", "url1");
    }

    @Test
    public void testReplaceSystemPath() {
        String path = "E:\\A\\B/C/D";

        String changedPath = path.replaceAll("\\\\", "/");

        assertThat(changedPath, is("E:/A/B/C/D"));

        path = "E:\\Test\\AcceptanceTest/res/image/image1.png";

        changedPath = path.replaceAll("\\\\", "/");
        assertThat(changedPath, is("E:/Test/AcceptanceTest/res/image/image1.png"));
    }
}
