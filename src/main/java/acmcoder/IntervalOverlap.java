package acmcoder;

/**
* Created by gang.qin on 2015/9/20.
* 考点：两个区间有重叠
*/
import java.util.Scanner;

public class IntervalOverlap {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int p = scan.nextInt();
        int q = scan.nextInt();
        int r = scan.nextInt();
        int l = scan.nextInt();
        point[] pn = new point[p];
        point[] qn = new point[q];
        for (int i = 0; i < p; i++) {
            point pi = new point(scan.nextInt(), scan.nextInt());
            pn[i] = pi;
        }
        for (int i = 0; i < q; i++) {
            point qi = new point(scan.nextInt(), scan.nextInt());
            qn[i] = qi;
        }
        System.out.println(fun(p, q, r, l, pn, qn));
    }

    public static int fun(int p, int q, int r, int l, point[] pn, point[] qn) {
        int count = 0;
        for (int t = l; t <= r; t++) {
            point[] qnt = getQnt(t, qn);
            if (isCanChat(pn, qnt)) {
                count++;
            }
        }
        return count;
    }

    ////已知t,求出小菇的在线时间区间
    public static point[] getQnt(int t, point[] qn) {
        point[] qnt = new point[qn.length];
        for (int i = 0; i < qn.length; i++) {
            qnt[i] = new point(qn[i].x + t, qn[i].y + t);
        }
        return qnt;
    }

    //判断小蘑和小菇能否聊天
    public static boolean isCanChat(point[] pn, point[] pnt) {
        for (int i = 0; i < pn.length; i++) {
            for (int j = 0; j < pnt.length; j++) {
                if (hasSame(pn[i], pnt[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    ////判断两个区间是否相交
    public static boolean hasSame(point p1, point p2) {
        int x = getMax(p1.x, p2.x);
        int y = getMin(p1.y, p2.y);
        if (x <= y) {
            return true;
        } else {
            return false;
        }
    }

    //求两个数的最大值
    public static int getMax(int x, int y) {
        return x > y ? x : y;
    }

    //求两个数的最小值
    public static int getMin(int x, int y) {
        return x > y ? y : x;
    }

}

class point {
    public int x;
    public int y;

    public point(){};

    public  point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
