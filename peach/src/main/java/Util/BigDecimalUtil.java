package Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by gang.qin on 2016/3/17.
 */
public class BigDecimalUtil {
    /**
     * 保留两位小数，四舍五入之后，若是整数则不显示小数部分
     * 例如1.00 显示 1， 1.10显示 1.10， 1.001显示1
     * @param bigDecimal
     * @return
     */
    public static String bigDecimal2String(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "0";
        }
        Double value = bigDecimal.setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        if (Math.round(value) == value) {
            return String.valueOf(value.intValue());
        } else {
            return String.format("%.2f", value);
        }
    }
}
