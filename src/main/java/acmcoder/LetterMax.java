package acmcoder;

import java.util.Scanner;

/**
 * Created by gang.qin on 2015/9/20.
 * 考点：有序序列相邻最大间隔
 */
public class LetterMax {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = scan.nextInt();
        }
        System.out.println(fun(n, num));
    }

    public static int fun(int n, int[] num) {
        int[] max = new int[n - 2];
        for (int i = 1; i < n-1; i++) {
            int[] temp = getTemp(i, num);
            max[i - 1] = getMax(temp);
        }
        int letteMax = max[0];
        for (int i = 1; i < n - 2; i++) {
            if (max[i] < letteMax) {
                letteMax = max[i];
            }
        }
        return letteMax;
    }

    //求有序序列的相邻最大间隔
    public static int getMax(int[] temp) {
        int d[] = new int[temp.length - 1];
        for (int i = 1; i < temp.length; i++) {
            d[i - 1] = temp[i] - temp[i - 1];
        }

        int max = d[0];
        for (int i = 1; i < d.length; i++) {
            if (d[i] > max) {
                max = d[i];
            }
        }

        return max;
    }

    //去掉a2...an-1中任意一个之后的数组
    public static int[] getTemp(int i, int[] num) {
        int[] temp = new int[num.length - 1];
        for (int j = 0; j < num.length; j++) {
            if (j < i) {
                temp[j] = num[j];
            } else if (j > i) {
                temp[j - 1] = num[j];
            }
        }
        return temp;
    }

}
