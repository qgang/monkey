package acmcoder;

/**
 Created by gang.qin on 2015/9/22.
 Problem Description
 Given a sequence a[1],a[2],a[3]......a[n], your job is to calculate the max sum of a sub-sequence.
 For example, given (6,-1,5,4,-7), the max sum in this sequence is 6 + (-1) + 5 + 4 = 14.
 Input
 The first line of the input contains an integer T(1<=T<=20) which means the number of test cases.
 Then T lines follow, each line starts with a number N(1<=N<=100000), then N integers followed(all the integers are between -1000 and 1000).
 Output
 For each test case, you should output two lines. The first line is "Case #:", # means the number of the test case.
 The second line contains three integers, the Max Sum in the sequence, the start position of the sub-sequence, the end position of the sub-sequence.
 If there are more than one result, output the first one. Output a blank line between two cases.
 Sample Input
 4
 5 6 -1 5 4 -7
 7 0 6 -1 1 -6 7 -5
 9 -3 4 9 2 -10 -7 11 3 -8
 13 -1 2 6 -3 5 -7 14 -5 -15 1 8 -4 9
 Sample Output
 Case 1:
 14 1 4

 Case 2:
 7 1 6

 Case 3:
 15 2 4

 Case 4:
 17 2 7
 */
import java.util.Scanner;

public class MaxSumOfSubArray {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int i = 0; i < T; i++){
            int n = scan.nextInt();
            int[] seq = new int[n];
            for (int j = 0; j < n; j++) {
                seq[j] = scan.nextInt();
            }
            System.out.println("Case " + (i + 1) + ":");
            System.out.println(maxSumSeq(n, seq));
            if (i < T-1) {
                System.out.println();
            }
        }
    }

    // 简单暴力法,穷举序列的子序列，O(n^2)
    public static String maxSumSequnence(int n, int[] seq) {
        int max = 0;
        int sum = 0;
        int begin = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n; j++) {
                sum = seq[j] + sum;
                if (sum > max) {
                    max = sum;
                    begin = i;
                    end = j;
                }
            }
        }
        return max + " " + (begin+1) + " " + (end+1);
    }

    /**动态规划求解, O(n), 设sum[i] 为前i个元素中，包含第i个元素且和最大的连续子数组,
     * 对于第i+1个元素有两种选择：作为新子数组的第一个元素，放入前面找到的子数组。
     * sum[i+1] = max(a[i+1], sum[i] + a[a+1])
     * max = max(max, sum[i])
     */
    public static String maxSumSeq(int n, int[] seq) {
        int sum = 0;
        int max = seq[0];
        int begin = 0;
        int temp = 0;
        int end = 0;

        for (int i = 0; i < n; i++) {
            if (sum >= 0) {
                sum += seq[i];
            } else {
                sum = seq[i];
                temp = i;
            }

            if (sum > max) {
                max = sum;
                begin = temp;
                end = i;
            }
        }

        return max + " " + (begin+1) + " " + (end+1);
    }
}
