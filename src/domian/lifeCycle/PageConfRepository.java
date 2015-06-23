package domian.lifeCycle;

import com.esotericsoftware.yamlbeans.YamlReader;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

/**
 * Created by Antinomy on 15/6/16.
 */
public class PageConfRepository {
    Map map;

    public PageConfRepository(String yamlFilePath) throws Exception {
        File directory = new File("");//设定为当前文件夹
        yamlFilePath = directory.getAbsolutePath() + "/src/feature/" + yamlFilePath;

        YamlReader reader = new YamlReader(new FileReader(yamlFilePath));

        Object object = reader.read();
        System.out.println(object);

        map = (Map) object;
    }

    public String getConf(String page, String element) {
        Map targetPage = (Map) map.get(page);
        return targetPage.get(element).toString();
    }

    public By getId(String page, String id) {
        String conf = getConf(page, id);
        return By.id(conf);
    }

    public By getXPath(String page, String xPath) {
        String conf = getConf(page, xPath);
        return By.xpath(conf);
    }

    public By getCSS(String page, String css) {
        String conf = getConf(page, css);
        return By.cssSelector(conf);
    }

    public By getClassName(String page, String className) {
        String conf = getConf(page, className);
        return By.className(conf);
    }
}
