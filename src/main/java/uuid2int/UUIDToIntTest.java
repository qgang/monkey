package uuid2int;

import java.io.*;

/**
 * Created by gang.qin on 2015/9/2.
 */
public class UUIDToIntTest {
    public void getUUID(String sourcePath, String targetPath) {
        try {
            File sourceFile = new File(sourcePath);
            File targetFile = new File(targetPath);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(sourceFile));
            BufferedReader read = new BufferedReader(reader);

            targetFile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(targetPath, true));

            String line = "";
            String outLine = "";

            line = read.readLine();
            while (line != null) {
                outLine = outline(line);
                if (outLine != null) {
                    out.write(outLine);
                }
                line = read.readLine();
            }

            read.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String outline(String line) {
        String outline = null;
        try {
            int uuid = UUIDToIntUtil.UUID2Int(line);
            outline = Integer.toString(uuid) + "\n";
        } catch (Exception e) {
            throw new RuntimeException("error:" + line, e);
        }

        return outline;
    }

    public static void main(String[] args) {
        String sourcePath = "D:\\dianping\\event-2015-09-01\\uuid.txt";
        String targetPath = "D:\\dianping\\event-2015-09-01\\uuidint.txt";
        UUIDToIntTest uuidToIntTest = new UUIDToIntTest();
        uuidToIntTest.getUUID(sourcePath, targetPath);


//        String sourcePath = "D:\\dianping\\event-2015-09-01\\uuidtest.txt";
//        String targetPath = "D:\\dianping\\event-2015-09-01\\uuidint.txt";
//        UUIDToIntTest uuidToIntTest = new UUIDToIntTest();
//        uuidToIntTest.getUUID(sourcePath, targetPath);
    }
}
