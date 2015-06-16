package test;

import domian.lifeCycle.PageConfRepository;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Antinomy on 15/6/16.
 */
public class PageConfigServiceTest {

    @Test
    public void test() throws Exception {
        File directory = new File("");//设定为当前文件夹
        String yamlFilePath = directory.getAbsolutePath()+"/src/feature/baiduExample.yaml";

        PageConfRepository ps = new PageConfRepository(yamlFilePath);

        String url = ps.getConf("baiduPage","url");
        assertThat(url,is("https://www.baidu.com"));

        assertThat(ps.getConf("baiduPage","input_id"),is("kw"));
        assertThat(ps.getConf("baiduPage","searchBtn_id"),is("su"));
    }
}
