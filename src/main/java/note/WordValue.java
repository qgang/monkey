package note;

/**
 * Created by gang.qin on 2015/9/18.
 * 名字漂亮度
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

    private static int getCount(String name) {

        return 0;
    }
}





