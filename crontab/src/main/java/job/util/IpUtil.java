package job.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by gang.qin on 2015/12/18.
 */
public class IpUtil {

    /**
     * 获取本机第一个IPv4地址,多网卡
     */
    public static String getLocalIpv4Address(){
        String localIpAddress = "0.0.0.0";
        Enumeration<NetworkInterface> networkInterfaces = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            return localIpAddress;
        }

        while(networkInterfaces.hasMoreElements()){
            NetworkInterface element = networkInterfaces.nextElement();
            Enumeration<InetAddress> addresses = element.getInetAddresses();
            while (addresses.hasMoreElements()){
                InetAddress ip = addresses.nextElement();

                if (ip instanceof Inet4Address){
                    if (ip.isSiteLocalAddress()){
                        localIpAddress = ip.getHostAddress();
                        break;
                    }
                }
            }
        }
        return localIpAddress;
    }
}
