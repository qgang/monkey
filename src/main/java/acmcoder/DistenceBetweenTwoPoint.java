package acmcoder;

/**
 * Created by gang.qin on 2015/9/19.
 * 计算两点间距离
 * 输入：两点坐标（x1，x2），（y1，y2），计算并输出两点间距离
 */
import java.util.Scanner;

public class DistenceBetweenTwoPoint {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double x1 = scan.nextDouble();
        double x2 = scan.nextDouble();
        double y1 = scan.nextDouble();
        double y2 = scan.nextDouble();
        System.out.println(String.format("%.2f", fun(x1, x2, y1, y2)));
    }

    public static double fun(double x1, double x2, double y1, double y2) {
        double distance = 0;
        distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) );
        return distance;
    }
}
