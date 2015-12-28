package dper.uuid2int;

import java.util.UUID;

/**
 * Created by gang.qin on 2015/9/2.
 */
public class UUIDToIntUtil {

    public static int UUID2Int(String str) {
        UUID uuid = UUID.fromString(str);
        return uuid.hashCode();
    }

    public static void main(String[] args) {
        System.out.println(UUID2Int("01b9bb48-0ff3-4406-bdc1-ef7afeb462af"));
    }
}
