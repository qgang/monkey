package hw;

/**
 * Created by gang.qin on 2015/9/18.
 * 配置文件恢复
 */

import java.util.ArrayList;
import java.util.HashMap ;
import java.util.Map ;
import java.util.Scanner ;

public class ArgsExcute {

    static Map<String, String> argMap = new HashMap<String, String>() ;
    static String[] firstPart = {"reset" , "board", "reboot", "backplane" };
    static String[] secondPart = {"board" , "add", "delet", "backplane" , "abort" };

    static {
        argMap.put( "reset", "reset what" );
        argMap.put( "reset board" , "board fault");
        argMap.put( "board add" , "where to add");
        argMap.put( "board delet" , "no board at all");
        argMap.put( "reboot backplane" , "impossible");
        argMap.put( "backplane abort" , "install first");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in) ;
        while (scan.hasNext()) {
            String argss = scan.nextLine().trim();
            String result = execute(argss) ;
            System. out.println(result);
        }
    }

    private static String execute(String argss) {
        ArrayList<String> args = getArg(argss.split("\\ s"));
        String result = null;
        for (String arg : args) {
            result = argMap.get(arg) ;
            if (result != null ) {
                break;
            }
        }

        if (result == null) {
            return "unkown command" ;
        }
        return result;
    }

    private static ArrayList<String> getArg(String[] argss) {
        ArrayList<String> arg = new ArrayList<String>() ;
        if (argss == null) {
            return null;
        }

        if (argss.length == 1 && argss[0 ].length() != 0) {
            if (contain("reset", argss[0])) {
                arg.add("reset") ;
                return arg;
            }
        }

        if (argss.length == 2) {
            String[] first = new String[ 4];
            String[] second = new String[5 ];
            int j = 0 ;
            for (int i = 0; i < 4; i++) {
                if ( contain( firstPart[i], argss[0])) {
                    first[j++] = firstPart[i];
                }
            }

            int k =0;
            for (int i = 0; i < 5; i++) {
                if ( contain( secondPart[i], argss[1])) {
                    second[k++] = secondPart[i];
                }
            }

            if (j == 1 && k > 0) {
                for ( int i = 0 ; i < 5 && second[i] != null; i++) {
                    arg.add(first[ 0] + " " + second[i]) ;
                }
            }

            if ((j > 0 && k == 1)) {
                for ( int i = 0 ; i < 4 && first[i] != null; i++) {
                    arg.add(first[i] + " " + second[0 ]);
                }
            }
        }

        return arg;
    }

    private static boolean contain (String source, String target) {
        char [] sc = source.toCharArray() ;
        char[] tc = target.toCharArray() ;

        int i ;
        for (i = 0; i < sc. length && i < tc.length; i++) {
            if (sc[i] != tc[i]) {
                break;
            }
        }

        if (i == tc.length) {
            return true;
        }

        return false;
    }
}