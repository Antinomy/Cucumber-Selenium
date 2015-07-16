package domian.lifeCycle;

import com.esotericsoftware.yamlbeans.YamlReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

/**
 * Created by Antinomy on 15/6/16.
 */
public class PageConfRepository {
    private Map map;
    private String absolutePath = null;
    private static Logger logger = Logger.getLogger(PageConfRepository.class);

    public PageConfRepository(String yamlFilePath) throws Exception {
        File directory = new File("");//设定为当前文件夹
        absolutePath = directory.getAbsolutePath();

        yamlFilePath = absolutePath + "/src/test/resources/feature/" + yamlFilePath;

        YamlReader reader = new YamlReader(new FileReader(yamlFilePath));

        Object object = reader.read();
        logger.debug(object);

        map = (Map) object;
    }

    public String getConf(String page, String element) throws NoConfigException {
        Map targetPage = (Map) map.get(page);
        try {
            return targetPage.get(element).toString();
        }catch (Exception ex)
        {
            NoConfigException noConfigException = new NoConfigException(ex);
            logger.error(noConfigException);
            throw noConfigException;
        }
    }

    public By getId(String page, String id) throws NoConfigException {
        String conf = getConf(page, id);
        return By.id(conf);
    }

    public By getXPath(String page, String xPath) throws NoConfigException{
        String conf = getConf(page, xPath);
        return By.xpath(conf);
    }

    public By getCSS(String page, String css) throws NoConfigException{
        String conf = getConf(page, css);
        return By.cssSelector(conf);
    }

    public By getClassName(String page, String className) throws NoConfigException{
        String conf = getConf(page, className);
        return By.className(conf);
    }

    public String getFilePath(String page, String fileName) throws NoConfigException{
        String confPath = getConf(page, fileName);
        confPath = confPath.replaceAll("/", File.separator);

        String filePath = absolutePath + confPath;
        return filePath;
    }

    public String getImageFilePath(String fileName) {

        StringBuffer confPath = new StringBuffer();

        // ../AcceptanceTest/src/main/resources/image/image1.png
        addSubFolder(confPath, "src");
        addSubFolder(confPath, "main");
        addSubFolder(confPath, "resources");
        addSubFolder(confPath, "image");
        addSubFolder(confPath, fileName);

        String filePath = absolutePath + confPath.toString();
        return filePath;
    }

    private void addSubFolder(StringBuffer confPath, String fileName) {
        confPath.append( File.separator + fileName);
    }

    public String getDriverPath(String driverType,String driverName) {

        String confPath = File.separator + "drivers"
                + File.separator + driverType
                + File.separator + driverName;

        String filePath = absolutePath + confPath;
        return filePath;
    }
}
