package acmcoder;

/**
 * Created by gang.qin on 2015/10/10.
 在N*M的草地上，提莫种了K个蘑菇，蘑菇的威力极大，兰博不想贸然去创，而且蘑菇是隐形的，
 只有一种叫做扫描镜的物品可以扫描出隐形蘑菇，于是他回了一趟战争学院，买了两个扫描透镜，
 一个扫描透镜可以扫描出（3*3）的方格中所以蘑菇，然后兰博就可以清理带哦一些隐形的蘑菇。
 问兰博最多可以清理多少个蘑菇。
 输入：第一行三个整数N,M,K（1<=N,M<=20,K<=20）,N,M代表了草地的大小；
 接下来K行，每行两个整数x,y。代表（x,y）处提莫种了一个蘑菇。
 一个方格可以有无穷多个蘑菇。
 输出：在一行里输出一个整数，代表兰博最多可以清理多个个蘑菇。
 例子输入：
 3 3 3
 1 1
 1 2
 1 3

 例子输出：
 3
 */
import java.util.Scanner;
public class Mushroom {
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
        // 此处调用两次，表示两个扫描镜各用一次
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
                        floor[i][j] = 0; // 扫描镜扫描一次后，要清零，以便下次扫描时不会重复
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
                    while (i++ < N) { // 扫描镜扫描一次后，要清零，以便下次扫描时不会重复
                        floor[i][j] = floor[i][j+1] = floor[i][j+2] = 0;
                    }
                    return max;
                }
            }
            if (N > 3 && M <=3) {
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
                    while (j++ < M) { // 扫描镜扫描一次后，要清零，以便下次扫描时不会重复
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
        floor[i][j] = floor[i+1][j] = floor[i+2][j] // 扫描镜扫描一次后，要清零，以便下次扫描时不会重复
                = floor[i][j+1] = floor[i+1][j+1] = floor[i+2][j+1]
                = floor[i][j+2] = floor[i+1][j+2] = floor[i+2][j+2] = 0;
        return max;
    }
}
