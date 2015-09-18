package note;

/**
 * Created by gang.qin on 2015/9/18.
 * 字符串反转
 */

import java.util.Scanner;

public class ReveseString {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in) ;
        String str = scan.nextLine() ;
        System. out.println(revese (str));
    }

    private static String revese(String str) {
        if (str == null) {
            return null;
        }

        char [] strChar = str.toCharArray() ;
        int i = 0;
        int j = strChar. length - 1 ;
        char c ;
        while (i < j) {
            c = strChar[i];
            strChar[i] = strChar[j] ;
            strChar[j] = c ;
            i++ ;
            j-- ;
        }

        return new String(strChar);
    }
}