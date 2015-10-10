package test;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int K = scan.nextInt();
        int[][]  floor= new int[N][M];
        int x = 0;
        int y = 0;
        for (int i = 0; i < K; i++) {
            x = scan.nextInt();
            y = scan.nextInt();
            floor[x-1][y-1] += 1;
        }
        System.out.println(mushroom(N, M, floor) + mushroom(N, M, floor));
    }
    //求清理的蘑菇数
    private static int mushroom(int N, int M, int[][] floor) {
        int max = 0;
        int x = 0;
        int y = 0;
        int i = 0;
        int j = 0;
        int count = 0;
        if (N <= 3 || M <=3) {
            if (N <= 3 && M <=3) {
                for (i = 0; i < N; i++) {
                    for (j = 0; j < M; j++) {
                        count += floor[i][j];
                        floor[i][j] = 0;
                    }
                }
                return count;
            }
            if (N <=3 && M > 3) {
                for(j  = 0; j + 2 < M; j++) {
                    i = 0;
                    while (i++ < N) {
                        count += floor[i][j] + floor[i][j+1] + floor[i][j+2];
                    }
                    if (count > max) {
                        y = j;
                    }
                    i = 0;
                    j = y;
                    while (i++ < N) {
                        floor[i][j] = floor[i][j+1] = floor[i][j+2] = 0;
                    }
                    return max;
                }
            }
            if (N > 3 && M >=3) {
                for(i  = 0; i + 2 < N; i++) {
                    j = 0;
                    while (j++ < M) {
                        count += floor[i][j] + floor[i+1][j] + floor[i+2][j];
                    }
                    if (count > max) {
                        x = i;
                    }
                    i = x;
                    j = 0;
                    while (j++ < M) {
                        floor[i][j] = floor[i+1][j] = floor[i+2][j] = 0;
                    }
                    return max;
                }
            }
        }
        for (i = 0; i + 3< N; i++) {
            for (j = 0; j + 3< M; j++) {
                count = floor[i][j] + floor[i+1][j] + floor[i+2][j]
                        +floor[i][j+1] + floor[i+1][j+1] + floor[i+2][j+1]
                        +floor[i][j+2] + floor[i+1][j+2] + floor[i+2][j+2];
            }
            if (count > max) {
                max = count;
                x = i;
                y = j;
            }
        }
        i = x;
        j = y;
          floor[i][j] = floor[i+1][j] = floor[i+2][j]
        = floor[i][j+1] = floor[i+1][j+1] = floor[i+2][j+1]
        = floor[i][j+2] = floor[i+1][j+2] = floor[i+2][j+2] = 0;
        return max;
    }
}

