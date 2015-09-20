package note;

/**
 * Created by gang.qin on 2015/9/19.
 */
import java.util.Scanner;

public class MonkeyApple {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println(fun(n));
    }

    public static int fun(int n) {
        int i = 1;
        int a = fun(1, n);
        while (a == 0) {
            a = fun(++i, n);
        }

        return a;
    }

    public static int fun(int a0, int n) {
        double an = a0;
        for (int i = 0; i < n; i++) {
            an = n*an/(n-1);
            if (an - (int)an != 0) {
                return 0;
            }
            an += 1;
        }
        return (int)an;
    }
}