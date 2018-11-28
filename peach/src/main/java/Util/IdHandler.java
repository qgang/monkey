package Util;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gang.qin
 * @date 2018-11-28.
 */
public class IdHandler {

    private static String str1 = "";
    private static String str2 = "";

    public static void main(String[] args) {
        String[] strList1 = str1.split(",");
        String[] strList2 = str2.split(",");

        Set<String> set = new HashSet(strList2.length * 2);
        for (String s : strList2) {
            set.add(s);
        }

        // str2中去除str1中的元素
        for (String s : strList1) {
                set.remove(s);
        }

        System.out.println(JSON.toJSONString(set));
    }
}
