package note;

import java.util.Scanner;

//水仙花数
public class Flower {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int m = 0;
        int n = 0;
        while (scan.hasNext()) {
            m = scan.nextInt();
            n = scan.nextInt();
            System.out.println(fun(m, n));
        }
    }

    public static String fun(int m, int n) {
        String result = "";
        if (m > n) {
            result = "no";
        } else {
            int count = 0;
            for (; m <= n; m++) {
                if (isFlower(m)) {
                    result += m + " ";
                    count++;
                }
            }
            if (count == 0) {
                result += "no";
            }
        }
        return result.trim();
    }

    private static boolean isFlower(int m) {
        char[] numbers = new Integer(m).toString().toCharArray();
        int sum = 0;
        int temp = 0;
        for (int i = 0; i < numbers.length; i++) {
            temp = numbers[i] - '0';
            sum += temp * temp * temp;
        }

        if (m == sum) {
            return true;
        } else {
            return false;
        }
    }
}


/*
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //获取N
        int N = scan.nextInt();

        //获取名字列表
        String[] names = new String[N];
        for (int i = 0; i < N; i++) {
            names[i] = scan.next().toLowerCase();
        }

        //计算每个字母的漂亮度
        int[] array = getArray(names);
        System.out.println(getCount(names[0]));
        for (int i = 1; i < N; i++) {
            System.out.print(" " + getCount(names[i]));
        }


    }

    private static int[] getArray(String[] names) {
        int[] array = new int[26];
        for (int i = 0; i < names.length; i++) {

        }
        return new int[0];
    }

    private static int getCount(String name) {

        return 0;
    }
}

*/



/*
//合唱队
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int count = 0;
        int n = -1;
        String h = "";
        while (s.hasNextLine() && count < 2) {
            if (count == 0) {
                n = Integer.parseInt(s.nextLine().trim());
            } else {
                h = s.nextLine().trim();
                break;
            }
            count++;
        }
        s.close();
        String[] hts = h.split(" ");
        int[] height = new int[hts.length];
        for (int i = 0; i < hts.length; i++) {
            height[i] = Integer.parseInt(hts[i]);
        } // 在height数组中找出一个先增后减或者单调的子队列，选出人数最多的子队列
        if (n == 1) {
            System.out.println(0);
            return;
        } else {
            List<int[]> list = new ArrayList<int[]>();

            int[] number = height;
            int maxPeople = 0;
            for (int i = n; i >= 2; i--) {
                // 从n个中挑出i个人，相对顺序不变
                getList("", 0, i, number, list);
                // 包含i个人的定序队列集合
                for (int[] t : list) {
                    if (isRight(t) && t.length > maxPeople) {
                    // 在集合中找到合法队列
                        maxPeople = t.length; break;
                    }
                }
            }
            System.out.println(n - maxPeople);
        }
    }

    // 从数组中获取子队列的集合
    public static void getList(String result, int start, int length, int[] n, List<int[]> l)
    {
        if (length == 0) {
            if (result.substring(result.length() - 1).equals(",")) {
                result = result.substring(0, result.length() - 1);
            } String[] s = result.split(",");
            int[] t = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                t[i] = Integer.parseInt(s[i]);
            }
            l.add(t);
        } else {
            if (length <= (n.length - start)) {
                getList(result, start + 1, length, n, l);
                result += n[start] + ",";
                getList(result, start + 1, length - 1, n, l);
            }
        }
    }
    // 给定的数组是否是一个合法的合唱队列
    private static boolean isRight(int[] num) {
        int Max = getMaxIndex(num);
        int[] left = getArray(num, 0, Max - 1);
        int[] right = getArray(num, Max + 1, num.length - 1);
        if (isUp(left) && isDown(right)) {
            return true;
        } return false;
    }

    // 获得给定数据的子数组
    private static int[] getArray(int[] num, int startIndedx, int endIndex) {
        if (startIndedx > endIndex) {
            return new int[] {};
        } else {
            int[] r = new int[endIndex + 1 - startIndedx];
            for (int j = 0; j < r.length; j++) {
                r[j] = num[startIndedx + j];
            } return r;
        }
    } // 数组是否递减
    private static boolean isDown(int[] right) {
        if (right.length == 0) {
            return true;
        } else {
            for (int i = 1; i < right.length; i++) {
                if (right[i - 1] <= right[i]) {
                    return false;
                }
            }
            return true;
        }
    }
    // 数组是否递增
    private static boolean isUp(int[] left) {
        if (left.length == 0) {
            return true;
        } else {
            for (int i = 1; i < left.length; i++) {
                if (left[i - 1] >= left[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    // 获得数组中最大值的索引
    private static int getMaxIndex(int[] n) {
        int r = 0; for (int i = 0; i < n.length; i++) {
            if (n[i] > n[r]) {
                r = i;
            }
        }
        return r;
    }
}
*/