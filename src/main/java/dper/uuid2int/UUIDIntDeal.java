package dper.uuid2int;

import base.TextBase;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gang.qin on 2015/9/2.
 */
public class UUIDIntDeal extends TextBase{

    private String UUID = "";

    @Override
    public void readWrite(BufferedReader read, BufferedWriter out) {
        try {
            String line = "";
            int outLine = 0;
            Set<Integer> result = new HashSet();

            line = read.readLine();
            while (line != null) {
                outLine = (Integer)outDeal(line);
                if (outLine != 0) {
                    result.add(outLine);
                }
                line = read.readLine();
            }

            for (Integer output : result) {
                out.write(output.toString() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object outDeal(String line) {
        return Integer.parseInt(line);
    }

    public static void main(String[] args) {
        String sourcePath = "D:\\dianping\\event-2015-09-01\\uuidint.txt";
        String targetPath = "D:\\dianping\\event-2015-09-01\\uuidintfinal.txt";
        UUIDIntDeal uuidIntDeal = new UUIDIntDeal();
        uuidIntDeal.execute(sourcePath, targetPath);
    }
}
