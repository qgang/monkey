package hw;

/**
 * Created by gang.qin on 2015/9/18.
 * 字符串加解密
 */
import java.util.Scanner;

public class CharEncrypt {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String enStr = scan.next();
        String unEnStr = scan.next();
        System.out.println(encrpty(enStr));
        System.out.println(unEncrypt(unEnStr));
    }

    private static String unEncrypt(String unEnStr) {
        if (unEnStr == null || unEnStr.length() == 0) {
            return "";
        }

        int def = 'A' - 'a';
        char[] array = unEnStr.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 'a' && array[i] <= 'z') {
                array[i] = (char)(((array[i] - 1)) + def);
            } else if (array[i] == 'a') {
                array[i] = 'Z';
            } else if (array[i] > 'A' && array[i] <= 'Z') {
                array[i] = (char)(((array[i] - 1)) - def);
            } else if (array[i] == 'A') {
                array[i] = 'z';
            } else if (array[i] > '0' && array[i] <= '9') {
                array[i] = (char)((array[i] - 1));
            } else if (array[i] == '0') {
                array[i] = '9';
            }
        }
        return new String(array);
    }

    private static String encrpty(String enStr) {
        if (enStr == null || enStr.length() == 0) {
            return "";
        }

        int def = 'A' - 'a';
        char[] array = enStr.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 'a' && array[i] < 'z') {
                array[i] = (char)(((array[i] + 1)) + def);
            } else if (array[i] == 'z') {
                array[i] = 'A';
            } else if (array[i] >= 'A' && array[i] < 'Z') {
                array[i] = (char)(((array[i] + 1)) - def);
            } else if (array[i] == 'Z') {
                array[i] = 'a';
            } else if (array[i] >= '0' && array[i] < '9') {
                array[i] = (char)((array[i] + 1));
            } else if (array[i] == '9') {
                array[i] = '0';
            }
        }
        return new String(array);
    }
}


