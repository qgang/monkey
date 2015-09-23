package acmcoder;

/**
 * Created by Administrator on 2015/9/23.
 Problem Description
 Contest time again! How excited it is to see balloons floating around.
 But to tell you a secret, the judges' favorite time is guessing the most popular problem.
 When the contest is over, they will count the balloons of each color and find the result.
 This year, they decide to leave this lovely job to you.

 Input
 Input contains multiple test cases. Each test case starts with a number N (0 < N <= 1000) -- the total number of balloons distributed.
 The next N lines contain one color each. The color of a balloon is a string of up to 15 lower-case letters.
 A test case with N = 0 terminates the input and this test case is not to be processed.

 Output
 For each case, print the color of balloon for the most popular problem on a single line.
 It is guaranteed that there is a unique solution for each test case.

 Sample Input
 5
 green
 red
 blue
 red
 red
 3
 pink
 orange
 pink
 0

 Sample Output
 red
 pink
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LetTheBalloonRise {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        while ( N != 0) {
            String[] colors = new String[N];
            for (int i = 0; i < N; i++){
                colors[i] = scan.next();
            }
            System.out.println(mostPopular(colors));

            N = scan.nextInt();
        }
    }

    private static String mostPopular(String[] colors) {
        Map<String, Integer> colorCount = new HashMap<String, Integer>();
        int max = 0;
        Integer count = null;
        String result = null;

        for (String color : colors) {
            count = colorCount.get(color);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            colorCount.put(color, count);
            if (count > max) {
                max = count;
                result = color;
            }
        }
        return result;
    }
}
