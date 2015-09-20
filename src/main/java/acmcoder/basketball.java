package acmcoder;
import java.util.Scanner;

/**
 * Created by gang.qin on 2015/9/20.
 * 考点：hash冲突
 */
public class Basketball {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int p = scan.nextInt();
        int n = scan.nextInt();
        int[] xi = new int[n];
        for (int i = 0; i < n; i++) {
            xi[i] = scan.nextInt();
        }
        System.out.println(fun(p, n, xi));
    }

    public static int fun(int p, int n, int[] xi) {
        if(p < 1 || n < 1) {
            return 0;
        }
        char[] pn = new char[p];
        int index = 0;
        int i = 0;
        for (; i < n; i++) {
            index = xi[i] % p;
            if (pn[index] != '1') {
                pn[index] = '1';
            } else {
                break;
            }
        }

        if (i == n) {
            return -1;
        }
        return i+1;
    }
}
