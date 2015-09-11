package Util;

import java.io.*;

/**
 * Created by gang.qin on 2015/9/2.
 */
public class FileUtil {

    /**
     * 以bufferedReader方式打开文件，若文件打开失败返回null
     * @param filePath
     * @return
     */
    public static BufferedReader openFileReader(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 以BufferedWriter方式打开文件，若打开时失败返回null
     * @param filePath
     * @param append
     * @return
     */
    public static BufferedWriter openFileWriter(String filePath, boolean append) {
        try {
            File file = new File(filePath);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            return bufferedWriter;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭读文件
     * @param reader
     */
    public static void close(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭写文件
     * @param writer
     */
    public static void close(Writer writer) {
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
