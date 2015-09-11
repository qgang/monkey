package Util;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gang.qin on 2015/9/6.
 */
public class IPV4Util {
    /**
     * 获取本机IP
     */
    public static String getIp() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "0.0.0.0";
        }
    }

    /**
     * 将整数值进行 >>
     * >>24位，得到数字为第一段IP
     * >>16位，得到数字为第二段IP
     * >>8 位，得到数字为第三段IP
     * >>最后一段为第四段IP
     * @param ip
     * @return
     */
    public static String int2IP(int ip) {
        return  ((ip >> 24) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                ((ip >> 8)  & 0XFF) + "." +
                (ip & 0XFF);
    }

    /**
     * 通过 << 给每一段的数字加权
     * 第一段的权为2^24
     * 第二段的权为2^16
     * 第三段的权为2^8
     * 第四段的权为2^0
     * @param ip
     * @return
     */
    public static int iP2Int(String ip) {
        String[] parts = ip.split("\\.");
        int[] ips = new int[4];
        for (int i = 0; i < 4; i++) {
            ips[i] = Integer.parseInt(parts[i]);
        }

        int ipInt = ips[3] & 0xFF;
        ipInt |= ((ips[2] << 8) & 0xFF00);
        ipInt |= ((ips[1] << 16) & 0xFF0000);
        ipInt |= ((ips[0] << 24) & 0xFF000000);

        return ipInt;

    }

    /**
     * 检查一个字符串是否为ip格式
     * @param ip
     * @return
     */
    public static boolean isIPv4(String ip) {
        String regex = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}
