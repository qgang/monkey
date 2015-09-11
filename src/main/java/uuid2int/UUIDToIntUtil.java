package uuid2int;

import java.util.UUID;

/**
 * Created by gang.qin on 2015/9/2.
 */
public class UUIDToIntUtil {

    public static int UUID2Int(String str) {
        UUID uuid = UUID.fromString(str);
        return uuid.hashCode();
    }
}
