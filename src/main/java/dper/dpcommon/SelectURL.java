package dper.dpcommon;

import Util.FileUtil;
import Util.NumUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by gang.qin on 2015/10/26.
 */
public class SelectURL {
    public static void main(String[] args) {
        BufferedReader reader = FileUtil.openFileReader("D:\\dianping\\dp-common\\originUrl.txt");
        BufferedWriter writer = FileUtil.openFileWriter("D:\\dianping\\dp-common\\url.txt", true);
        Set<String> outLines = new HashSet<String>();
        try {
            String line = reader.readLine();
            String out = null;
            while (line != null) {
                out = writerLine(line);
                if (out != null) {
                    outLines.add(out);
                }
                line = reader.readLine();
            }

            for (String str : outLines) {
                writer.write(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUtil.close(reader);
        FileUtil.close(writer);
    }

    private static String writerLine(String line) {
        String out = null;
        line = line.trim();
        if (line != null || line.length() > 1) {
            String[] strs = line.split("/");
            out = "";
            for (String str : strs) {
                if (NumUtil.isInteger(str) || isSerializedId(str)) {
                    break;
                }
                if (isImage(str)) {
                    out = null;
                    break;
                }
                if (str.length() > 1) {
                    out += "/" + str;
                }
            }
            out += "\n";
        }
        return out;
    }

    private static boolean isSerializedId(String str) {
        Pattern pattern = Pattern.compile("^[\\dA-Z]+$");
        return pattern.matcher(str).matches();
    }

    private static boolean isImage(String str) {
        Pattern pattern = Pattern.compile(".*\\.png$");
        return pattern.matcher(str).matches();
    }
}
