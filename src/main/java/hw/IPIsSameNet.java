package hw;

/**
 * Created by gang.qin on 2015/9/18.
 * 判断IP是否为同一个网络
 */
import java.util.Scanner;
import java.util.regex.Matcher ;
import java.util.regex.Pattern ;

public class IPIsSameNet {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in) ;
        String mask = scan.nextLine().trim() ;
        String ip1 = scan.nextLine().trim() ;
        String ip2 = scan.nextLine().trim() ;
        System. out.println(checkNetSegment (mask, ip1, ip2)) ;
    }

    public static int checkNetSegment(String mask, String ip1, String ip2) {
        if (!(isIpv4(mask) && isIpv4(ip1) && isIpv4(ip2))) {
            return 1;
        }

        int maskInt = ip2Int(mask);
        int ip1Int = ip2Int(ip1) ;
        int ip2Int = ip2Int(ip2) ;

        int net1 = ip1Int & maskInt ;
        int net2 = ip2Int & maskInt ;

        if (net1 == net2) {
            return 0;
        } else {
            return 2;
        }
    }

    public static int ip2Int(String ip) {
        String[] parts = ip.split(" \\.") ;
        int[] ips = new int[4 ];
        for ( int i = 0 ; i < 4 ; i++) {
            ips[i] = Integer.parseInt(parts[i]);
        }

        int ipInt = ips[3] & 0xFF;
        ipInt |= (ips[ 2] << 8 ) & 0xFF00;
        ipInt |= (ips[ 1] << 16 ) & 0xFF0000;
        ipInt |= (ips[ 0] << 24 ) & 0xFF000000;

        return ipInt ;
    }

    public static boolean isIpv4(String ip) {
        String regex = "^(25[0-5]|2[0-4] \\d|[0-1]?\\ d?\\d)( \\.(25[0-5]|2[0-4]\\ d|[0-1]?\\d? \\d)){3}$";
        Pattern pattern = Pattern. compile(regex) ;
        Matcher matcher = pattern.matcher(ip) ;

        return matcher.matches() ;
    }

}
