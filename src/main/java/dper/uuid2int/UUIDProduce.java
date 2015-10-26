package dper.uuid2int;

import java.io.*;

/**
 * Created by gang.qin on 2015/9/2.
 */
public class UUIDProduce {

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
        String uuid = null;

        if (line != null ) {
            String[] lines = line.split(",");
            if (lines != null && lines.length > 0) {
                String[] subLines = lines[0].split(" ");
                if (subLines != null && subLines.length > 0) {
                    uuid = subLines[subLines.length - 1];
                }
            }
        }

        if (uuid != null && uuid.length() == 36) {
            uuid = uuid + "\n";
        }

        return uuid;
    }

    public static void main(String[] args) {
//        String sourcePath1 = "D:\\dianping\\event-2015-09-01\\rs-mq01.nh__rs-mq.eda_info_daily.log.2015-08-26";
//        String sourcePath2 = "D:\\dianping\\event-2015-09-01\\rs-mq01.nh__rs-mq.eda_info_daily.log.2015-08-28";
//        String sourcePath3 = "D:\\dianping\\event-2015-09-01\\rs-mq01.nh__rs-mq.eda_info_daily.log.2015-08-31";
//        String targetPath = "D:\\dianping\\event-2015-09-01\\uuidtemp.txt";
//        UUIDProduce uuidProduce = new UUIDProduce();
//        uuidProduce.getUUID(sourcePath1, targetPath);
//        uuidProduce.getUUID(sourcePath2, targetPath);
//        uuidProduce.getUUID(sourcePath3, targetPath);

        UUIDProduce uuidProduce = new UUIDProduce();
        uuidProduce.test();
    }


    private void test() {
        String uuid = "()b6d32f3b-d32d-4b5f-8762-d1aee38259c4";
        if (uuid != null && uuid.length() == 36) {
            uuid = uuid + "\n";
        }
    }
}
