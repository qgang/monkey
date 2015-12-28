package hw;

/**
 * Created by gang.qin on 2015/9/18.
 * 蛇形矩阵
 */

import java.util.Scanner;

public class SJunZheng {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in) ;
        int N = scan.nextInt() ;
        println(getResult (N));
    }

    private static void println(int [][] result) {
        String out = "" ;
        int n = result. length;
        for( int i = 0 ; i < n; i++) {
            out = "";
            for (int j = 0; j < n - i; j++) {
                out += result[i][j] + " ";
            }
            System.out.println(out.trim()) ;
        }
    }

    private static int [][] getResult( int n) {
        if (n < 0) {
            return null;
        }

        int [][] result = new int[n][n] ;
        if (n == 1) {
            result[0][ 0] = 1 ;
            return result ;
        }

        int dep = 0;
        result[ 0][0 ] = 1;
        for ( int i = 1 ; i < n; i++) {
            result[0][i]  = result[ 0][i-1 ] + i + 1;
        }
        for (int i = 1; i < n ; i++) {
            dep = i + 2;
            result[i][ 0] = result[i-1 ][0] + i ;
            for (int j = 1; j < n - i; j++) {
                result[i][j] = result[i][j- 1] + dep;
                dep++;
            }
        }
        return result;
    }
}
