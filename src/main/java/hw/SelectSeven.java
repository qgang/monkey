package hw;

/**
 * Created by gang.qin on 2015/9/18.
 * 挑7
 */

import java.util.Scanner;

public class SelectSeven {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int calcLength = selectSeven(N);
        System.out.println(calcLength);
    }

    private static int selectSeven(int n) {
        int count = 0;
        for (Integer i = 7; i <= n; i++) {
            if (i.toString().contains("7")) {
                count++;
            } else if (i % 7 == 0){
                count++;
            }
        }
        return count;
    }
}

