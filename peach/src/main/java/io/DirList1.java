package io;

import Util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by gang.qin on 2016/9/2.
 * Java编程思想第18章p528练习1
 * 修改DirList.java，以便FilenameFilter能够打开每个文件，并检查命令行尾随的参数是否存在于那个文件中，以此检查结果来觉得是否接受这个文件
 */
public class DirList1 {

    public static void main(String[] args) {
        File path = new File("C:\\Program Files (x86)\\Java\\jdk1.7.0_80\\jre\\lib\\accessibility.properties");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(filter(args[0]));
        }

        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }

    public static FilenameFilter filter(final String regex) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                BufferedReader reader = FileUtil.openFileReader(dir.getPath() + File.separator + name);
                if (reader ==  null) {
                    return false;
                }
                try {
                    String temp = reader.readLine();
                    while (temp != null) {
                        if (temp.contains(regex)) {
                            FileUtil.close(reader);
                            return true;
                        }
                        temp = reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileUtil.close(reader);
                return false;
            }
        };
    }
}
