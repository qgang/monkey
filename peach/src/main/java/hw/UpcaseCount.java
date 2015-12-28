package hw;

/**
 * Created by gang.qin on 2015/9/18.
 * 统计大写字母个数
 */

import java.util.Scanner;

public class UpcaseCount {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine().trim();
        int calcLength = getCalcCapital(str);
        System.out.println(calcLength);
    }

    private static int getCalcCapital(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int calcLenght = 0;
        char[] strs = str.toCharArray();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] >= 'A' && strs[i] <= 'Z') {
                calcLenght++;
            }
        }
        return calcLenght;
    }
}



