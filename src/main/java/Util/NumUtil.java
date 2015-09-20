package Util;

/**
 * Created by gang.qin on 2015/9/20.
 */
public class NumUtil {

    /**
     * 求x，y中较大的值ֵ
     * @param x
     * @param y
     * @return
     */
    public static int getMax(int x, int y) {
        return x > y ? x : y;
    }

    /**
     * 求x，y中较小的值
     * @param x
     * @param y
     * @return
     */
    public static int getMin(int x, int y) {
        return x > y ? y : x;
    }
}
