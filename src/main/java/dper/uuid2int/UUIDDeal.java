package dper.uuid2int;

import base.TextBase;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gang.qin on 2015/9/2.
 */
public class UUIDDeal extends TextBase{

    private String UUID = "";

    @Override
    public void readWrite(BufferedReader read, BufferedWriter write) {
        String line = "";
        String outLine = "";
        Set<String> result = new HashSet<String>();
        try {
            line = read.readLine();

            while (line != null) {
                outLine = (String)outDeal(line);
                if (outLine != null) {
                    result.add(outLine);
                }
                line = read.readLine();
            }

            for (String output : result) {
                write.write(output + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object outDeal(String line) {
        if (UUID != null && UUID.equals(line)) {
            return null;
        }
        UUID = line;

        return UUID;
    }

    public static void main(String[] args) {
        String sourcePath1 = "D:\\dianping\\event-2015-09-01\\uuidtemp.txt";
        String targetPath = "D:\\dianping\\event-2015-09-01\\uuid.txt";
        UUIDDeal uUIDDeal = new UUIDDeal();
        uUIDDeal.execute(sourcePath1, targetPath);


//        String sourcePath1 = "D:\\dianping\\event-2015-09-01\\uuidtest.txt";
//        String targetPath = "D:\\dianping\\event-2015-09-01\\uuidint.txt";
//        UUIDDeal uUIDDeal = new UUIDDeal();
//        uUIDDeal.execute(sourcePath1, targetPath);
    }


}
