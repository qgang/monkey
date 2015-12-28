package acmcoder;

/**
 * Created by gang.qin on 2015/9/25.
 Problem Description
 The highest building in our city has only one elevator. A request list is made up with N positive numbers.
 The numbers denote at which floors the elevator will stop, in specified order.
 It costs 6 seconds to move the elevator up one floor, and 4 seconds to move down one floor.
 The elevator will stay for 5 seconds at each stop.

 For a given request list, you are to compute the total time spent to fulfill the requests on the list.
 The elevator is on the 0th floor at the beginning and does not have to return to the ground floor when the requests are fulfilled.

 Input
 There are multiple test cases. Each case contains a positive integer N, followed by N positive numbers.
 All the numbers in the input are less than 100. A test case with N = 0 denotes the end of input. This test case is not to be processed.

 Output
 Print the total time on a single line for each test case.

 Sample Input
 1 2
 3 2 3 1
 0
 Sample Output
 17
 41
 */
import java.util.Scanner;

public class Elevator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = 0;
        while(scan.hasNext()) {
            n = scan.nextInt();
            if (n == 0) {
                break;
            }
            int[] request = new int[n+1];
            for (int i = 1; i <= n; i++) {
                request[i] = scan.nextInt();
            }

            System.out.println(elevator(n, request));
        }
    }

    //注意diff==0时，也要停留5s
    private static int elevator(int n, int[] request) {
        int diff = 0;
        int time = 0;
        for (int i = 1; i <= n; i++) {
            diff = request[i] - request[i-1];
            if (diff > 0) {
                time += diff * 6 + 5;
            } else {
                time += diff * (-4) + 5;
            }
        }
        return time;
    }
}
