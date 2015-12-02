package FightGame.util;

import java.util.Random;

/**
 * Created by gang.qin on 2015/12/1.
 */
public class RandomUtil {
    public static int getRandom() {
        Random random = new Random();
        return random.nextInt(10);
    }
}
