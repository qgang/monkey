package base;

import Util.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * Created by gang.qin on 2015/9/6.
 */
public abstract class TextBase {

    public void execute(String sourcePath, String targetPath) {
        BufferedReader read = FileUtil.openFileReader(sourcePath);
        BufferedWriter out = FileUtil.openFileWriter(targetPath, true);

        readWrite(read, out);

        FileUtil.close(read);
        FileUtil.close(out);
    }

    abstract public void readWrite(BufferedReader read, BufferedWriter out);

    abstract public Object outDeal(String line);
}
