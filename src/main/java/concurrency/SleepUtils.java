package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by gang.qin on 2015/10/16.
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
