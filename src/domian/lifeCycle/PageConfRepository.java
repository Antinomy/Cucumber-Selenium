package domian.lifeCycle;

import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.util.Map;

/**
 * Created by Antinomy on 15/6/16.
 */
public class PageConfRepository {
    Map map;

    public PageConfRepository(String yamlFilePath) throws Exception {
        YamlReader reader = new YamlReader(new FileReader(yamlFilePath));

        Object object = reader.read();
        System.out.println(object);

        map = (Map) object;
    }

    public String getConf(String page, String element) {
        Map targetPage = (Map) map.get(page);
        return targetPage.get(element).toString();
    }
}
