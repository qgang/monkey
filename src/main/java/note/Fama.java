package note;

/**
 * Created by gang.qin on 2015/9/18.
 *
 */
import java.util.Scanner;

public class Fama {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in) ;
        int n = scan.nextInt() ;
        int[] weight = new int[n];
        int[] nums = new int[n];
        for ( int i = 0 ; i < n; i++) {
            weight[i] = scan.nextInt();
        }
        for (int i = 0; i < n ; i++) {
            nums[i] = scan.nextInt();
        }

        System.out.println( fama(n , weight, nums));
    }

    public static int fama(int n, int[] weight , int[] nums) {
        int sumWeight = getSumWeight(n, weight , nums);
        int[] avalibleWeight =  new int[sumWeight + 1 ];
        avalibleWeight[ 0] = 1 ;
        for ( int i = 0 ; i < n; i++) {
            for (int j = 0; j < nums[i] ; j++) {
                for ( int w = sumWeight; w >= weight[i]; w--) {
                    if (avalibleWeight[w - weight[i]] == 1) {
                        avalibleWeight[w] = 1;
                    }
                }
            }
        }

        int count = 0;
        for ( int i = 0 ; i <= sumWeight; i++) {
            if (avalibleWeight[i] == 1) {
                count++;
            }
        }
        return count;
    }

    // 获取所有砝码的总重量
    public static int getSumWeight(int n, int[] weight , int[] nums) {
        int sum = 0;
        for ( int i = 0 ; i < n; i++) {
            sum += weight[i] * nums[i];
        }
        return sum;
    }
}
