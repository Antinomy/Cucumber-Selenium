package domian.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Antinomy on 15/7/2.
 */
public class TimeService {
    public static String now() {
        String format = "yyyyMMddhhmmss";

        return now(format);
    }

    private static String now(String format) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(now);
    }

}
