package domian.lifeCycle;

/**
 * Created by Antinomy on 15/7/16.
 */
public class NoConfigException extends Exception {

    public NoConfigException(Exception ex) {
         super("读取配置出错！！ 格式不对、或值写错",ex);
    }
}
