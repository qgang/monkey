package hw;

/**
 * Created by gang.qin on 2015/9/18.
 描述
 给出一个名字，该名字有26个字符串组成，定义这个字符串的“漂亮度”是其所有字母“漂亮度”的总和。
 每个字母都有一个“漂亮度”，范围在1到26之间。没有任何两个字母拥有相同的“漂亮度”。字母忽略大小写。
 给出多个名字，计算每个名字最大可能的“漂亮度”。
 知识点	字符串
 运行时间限制	0M
 内存限制	0
 输入
 整数N，后续N个名字
 N个字符串，每个表示一个名字

 输出
 每个名称可能的最大漂亮程度
 样例输入	2 zhangsan lisi
 样例输出	192 101
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordValue{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //获取N
        int N = scan.nextInt();

        //获取名字列表
        String[] names = new String[N];
        for (int i = 0; i < N; i++) {
            names[i] = scan.next().toLowerCase();
        }

        for (int i = 0; i < N; i++) {
            System.out.println(getNameValues(names[i]));
        }


    }

    private static int getNameValues(String name) {
        //计算每个字母的漂亮度
        Map charValue = getCharValue(name);

        //获取每个名字的漂亮度
        int nameValue = getValues(charValue, name);

        return nameValue;
    }

    private static Map getCharValue(String name) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int[] vaules = new int[26];
        int length = 0;
        int value = 26;
        int index = 0;
        if (name != null) {
            char[] chars = name.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                index = chars[i]-'a';
                if (vaules[index] == 0) {
                    length++;
                }
                vaules[index]++;
            }
            for(int i = 0; i < length; i++) {
                int j = getMax(vaules);
                char c = (char)(j + 'a');
                map.put(c, value);
                value--;
            }
        }

        return map;
    }

    private static int getMax(int[] vaules) {
        int max = 0;
        int j = 0;
        for (int i = 0; i < 26; i++) {
            if (vaules[i] > max) {
                max = vaules[i];
                j = i;
            }
        }
        vaules[j] = 0;
        return j;
    }

    private static int getValues(Map charValues, String name) {
        int nameVule = 0;
        char[] names = name.toCharArray();
        for(int j = 0; j < names.length; j++) {
            nameVule += (Integer)charValues.get(names[j]);
        }
        return nameVule;
    }
}





