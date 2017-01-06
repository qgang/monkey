package io;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by gang.qin on 2016/9/2.
 * Java编程思想第18章p528练习2
 * 创建一个叫做SortedDirList的类，它具有一个可以接受文件路径信息，并能构建该路径下所有文件的排序目录列表的构造器。
 * 向这个类添加两个重载的list方法：一个产生整个列表，另一个产生与其参数（一个正则表达式）相匹配的列表子集
 */
public class DirList2 {

    private String[] list;

    public static void main(String[] args) {

        DirList2 dirList2 = new DirList2("C:\\Program Files (x86)\\Java\\jdk1.7.0_80\\jre\\lib\\accessibility.properties");
        for (String dirItem : dirList2.list()) {
            System.out.println(dirItem);
        }

        for (String dirItem : dirList2.list(args[0])) {
            System.out.println(dirItem);
        }
    }

    public DirList2(String path) {
        File file = new File(path);
        String[] list = file.list();
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
    }

    public String[] list() {
        return list;
    }

    public String[] list(final String regex) {
        Pattern pattern = Pattern.compile(regex);
        List<String> result = new ArrayList<>();
        for (String str : list) {
            if (pattern.matcher(str).matches()) {
                result.add(str);
            }
        }
        return (String[])result.toArray();
    }
}
