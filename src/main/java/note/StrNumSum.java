package note;

/**
 * Created by gang.qin on 2015/9/18.
 * 输入：一个字符串，包含数字和字母
 * 要求：写一个函数，把这些数字加起来，输出这些数字的和
 * 例子：“我30你40他50” 结果是120
 */
import java.util.Scanner;

public class StrNumSum {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        System.out.println(fun(str));
    }

    public static int fun(String str) {
        char[] strs = str.toCharArray();
        char[] newStr = new char[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] >= '0' && strs[i] <= '9') {
                newStr[i] = strs[i];
            } else {
                newStr[i] = ' ';
            }
        }

        String numtemp = String.copyValueOf(newStr).trim();
        String[] numstr = numtemp.split("\\s");
        Integer sum = 0;
        for (int i = 0; i < numstr.length; i++) {
            sum += Integer.parseInt(numstr[i]);
        }

        return sum;
    }
}
