package acmcoder;

/**
 * Created by gang.qin on 2015/9/23.
 Problem Description
 A number sequence is defined as follows:

 f(1) = 1, f(2) = 1, f(n) = (A * f(n - 1) + B * f(n - 2)) mod 7.

 Given A, B, and n, you are to calculate the value of f(n).

 Input
 The input consists of multiple test cases. Each test case contains 3 integers A, B and n on a single line (1 <= A, B <= 1000, 1 <= n <= 100,000,000).
 Three zeros signal the end of input and this test case is not to be processed.

 Output
 For each test case, print the value of f(n) on a single line.

 Sample Input
 1 1 3
 1 2 10
 0 0 0

 Sample Output
 2
 5
 */
import java.util.Scanner;

public class NumberSequence {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int A = 0;
        int B = 0;
        int n = 0;
        while (true) {
            A = scan.nextInt();
            B = scan.nextInt();
            n = scan.nextInt();

            if (A == 0 && B == 0 && n == 0) {
                break;
            }
            System.out.println(func(A, B, n));
        }
    }

    //fn为周期函数，先计算出周期，然后再求解. f(n)为0...6，七中情况，那么f(n) = (A * f(n - 1) + B * f(n - 2)) mod 7的周期不会超过49，鸽巢定理
    private static int func(int a, int b, int n) {
        int[] fn = new int[55];
        fn[1] = 1;
        fn[2] = 1;

        int i = 0;
        for (i = 3; i < 55; i++) {
            fn[i] = ((a*fn[i-1] + b*fn[i-2]) % 7);
            if (fn[i] == 1 && fn[i-1] == 1) {
                break;
            }
        }

        i -= 2;
        n %= i;
        n = n == 0 ? i:n;
        return fn[n];
    }

    //用数组缓存fn, time limit exceeded
    private static int fun(int a, int b, int n) {
        int[] fn = new int[n];
        fn[0] = 1;
        fn[1] = 1;
        int i = 0;
        for (i = 2; i < n; i++) {
            fn[i] = (a*fn[i-1] + b*fn[i-2]) % 7;
        }
        if (n == 0) {
            return fn[i-2];
        } else {
            return fn[n-1];
        }
    }

    //递归方式求解，提交acmcoder，memory limit exceeded
    private static int f(int a, int b, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        return (a * f(a, b, n-1) + b* f(a, b, n-2)) % 7;
    }
}