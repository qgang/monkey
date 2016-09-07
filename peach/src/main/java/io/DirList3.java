package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by gang.qin on 2016/9/2.
 * Java编程思想第18章p528练习2
 * 修改DirList.java, 使其对所以选中的文件计算文件尺寸的总和
 */
public class DirList3 {

    public static void main(String[] args) {
        File path = new File("C:\\Program Files (x86)\\Java\\jdk1.7.0_80\\jre\\lib");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(filter(args[0]));
        }

        System.out.println(size(path, list));

    }

    public static long size(File path, String[]  list) {
        long size = 0;
        if (list == null) {
            size = path.length() / 1024 + 1; // 单位KB
        } else {
            for (String name : list) {
                File file = new File(path.getPath() + File.separator + name);
                if (file.list() == null) {
                    size += file.length() / 1024 + 1;
                } else {
                    size += size(file, file.list()); // 深度优先递归文件夹
                }
            }
        }
        return size;
    }

    public static FilenameFilter filter(final String regex) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }
}
