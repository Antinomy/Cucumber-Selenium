import domian.service.TimeService;
import org.hamcrest.core.StringStartsWith;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by Antinomy on 15/7/2.
 */
public class TimeServiceTest {
    @Test
    public void testNow()
    {
        String now =  TimeService.now();

        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());

        assertThat(now, startsWith(today));
        assertThat(now.length(), is(14));
    }
}
