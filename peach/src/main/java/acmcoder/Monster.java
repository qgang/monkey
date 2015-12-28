package acmcoder;

/**
 * Created by gang.qin on 2015/10/9.
 小易经常沉迷于网络游戏。有一次打怪升级，他角色的初能力值为a。接下来依次遇见n个怪兽，
 每个怪兽的防御力为b1,b2,b3,...,bn。如果遇到怪物的能力值小于等于小易当前的能力值c，
 那么他就轻松打败怪兽，并且使自己的能力值增加bi；如果bi大于c，那么他的能力值只能增加
 bi与c的最大公约数。问小易最终能力值为多少？
 输入：每组数据，第一行两个整数n（1<=n<=100000）表示怪物的数量和a表示小易初能力值，
 第二行n个整数，b1,b2...bn（1<=bi<=n）表示每个怪兽的防御能力
 例子输入：
 3 50
 50 105 200

 5 20
 30 20 15 40 100

 例子输出：
 110
 205
 */
import java.util.Scanner;
public class Monster {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int a = scan.nextInt();
        int[] bi = new int[n];
        for (int i = 0; i < n; i++) {
            bi[i] = scan.nextInt();
        }
        System.out.println(Monster(a, n, bi));
    }
    //求小易最终的能力值
    private static int Monster(int c, int n, int[] bi) {
        int b;
        for (int i = 0; i < n; i++) {
            b = bi[i];
            if (b > c) {// bi大于c的时候，能力值增加bi与c的最大公约数
                c += maxFactor(b, c);
            } else { // bi小于等于c的时候，能力值增加bi
                c += b;
            }
        }
        return c;
    }
    //求最大公约数
    private static int maxFactor(int b, int c) {
        int factor = b > c ? c : b;//factor初始值为b,c中较小的
        while (factor > 0) {
            if (b % factor == 0 && c % factor == 0) {
                break;
            }
            factor--;
        }
        return factor;
    }
}