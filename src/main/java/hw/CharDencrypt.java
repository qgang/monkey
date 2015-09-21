package hw;

/**
 * Created by gang.qin on 2015/9/18.
 * 字符串加密
 */

import java.util.HashMap;
import java.util.Map ;
import java.util.Scanner ;

public class CharDencrypt {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in) ;
        String key = scan.nextLine().trim() ;
        String data = scan.nextLine().trim() ;
        System. out.println(dencrypt (key, data));
    }

    public static String dencrypt(String key , String data) {
        Map<Character, Character> keyValue = getKeyValue(key);
        char[] datas = data.toCharArray() ;
        int length = datas. length;
        char[] encrypt = new char[length];
        for ( int i = 0 ; i < length; i++) {
            encrypt[i] = keyValue.get(datas[i]);
        }

        return new String(encrypt);
    }

    private static Map<Character, Character> getKeyValue(String key) {
        char [] keys = key.toUpperCase().toCharArray() ;
        Map<Character , Character> valueKey = new HashMap<Character , Character>() ;
        Map<Character , Character> keyValue = new HashMap<Character , Character>() ;
        int i = 0;
        int end = keys. length;
        int index = 'A';
        for ( ; i < end; i++) {
            if (valueKey.get(keys[i]) == null) {
                valueKey.put(keys[i], ( char)index);
                index++;
            }
        }

        int begin = 'A';
        end = 26;
        for (i = 0; i < end ; i++) {
            if (valueKey.get((char)begin) == null) {
                valueKey.put((char)begin , (char )index);
                index++;
            }
            begin++;
        }

        for (Map.Entry<Character , Character> entry : valueKey.entrySet()) {
            keyValue.put(entry.getValue(), entry.getKey()) ;
        }

        index = 'a' ;
        int diff = 'A' - 'a' ;
        for (i = 0; i < end ; i++) {
            keyValue.put((char) index , (char )(keyValue.get((char)(index + diff)) - diff));
            index++ ;
        }

        keyValue.put(' ' , ' ') ;
        return keyValue ;
    }

}