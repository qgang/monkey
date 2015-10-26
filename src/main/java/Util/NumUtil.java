package Util;

import java.util.regex.Pattern;

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

    /**
     * 判断一个String是否为整数
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断一个String是否为16进制数
     * @param str
     * @return
     */
    public static boolean isHex(String str) {
        Pattern pattern = Pattern.compile("^[\\da-fA-F]+$");
        return pattern.matcher(str).matches();
    }
}
