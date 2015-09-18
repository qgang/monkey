package note;

/**
 * Created by gang.qin on 2015/9/18.
 *
 */
import java.util.Scanner;

public class GetCharCount {

    private static int englishCount;
    private static int blankCount;
    private static int numberCount;
    private static int otherCount;

    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in) ;
        String str = scan.nextLine() ;
        dealStr(str);
        System. out.println(getEnglishCharCount ());
        System. out.println(getBlankCharCount ());
        System. out.println(getNumberCharCount ());
        System. out.println(getOtherCharCount ());
    }

    public static void dealStr(String str) {
        if (str == null || str.length() == 0) {
            return ;
        }

        char [] strs = str.toCharArray() ;
        englishCount = 0;
        blankCount = 0;
        numberCount = 0;
        otherCount = 0;
        for ( int i = 0 ; i < strs.length ; i++) {
            if ((strs[i] >= 'a' && strs[i] <= 'z') || (strs[i] >= 'A' && strs[i] <= 'Z' ) ) {
                englishCount++ ;
            } else if (strs[i] == ' ' || strs[i] == '\t') {
                blankCount++ ;
            } else if (strs[i] >= '0' && strs[i] <= '9') {
                numberCount++ ;
            } else {
                otherCount++ ;
            }
        }
    }

    public static int getEnglishCharCount() {
        return englishCount;
    }

    public static int getBlankCharCount() {
        return blankCount;
    }

    public static int getNumberCharCount() {
        return numberCount;
    }

    public static int getOtherCharCount() {
        return otherCount;
    }

}