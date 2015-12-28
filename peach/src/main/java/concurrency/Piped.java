package concurrency;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Created by gang.qin on 2015/10/30.
 * 管道输入/输出
 * 1. 媒介：内存 （不同于文件输入/输出流和网络输入/输出流）
 * 2. 面向字节实现：PipeOutputStream/PipeInputStream
 *    面向字符实现：PipeReader/PipeWriter
 * 3. 对于Piped类型的流，必须要先进行绑定，即调用 connect() 方法
 */
public class Piped {
    public static void main (String[] args) throws Exception{
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();

        // 将输出流和输入流进行连接， 否则在使用时会抛出 IOException
        out.connect(in);

        Thread printThread = new Thread(new Print(in), "PrintThread");
        printThread.start();

        int recieve = 0;
        try {
            while ((recieve = System.in.read()) != -1) {
                out.write(recieve);
            }
        } finally {
            out.close();
        }
    }

    static class Print implements Runnable {
        private PipedReader in;

        public Print (PipedReader in) {
            this.in = in;
        }
        public void run () {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char)receive);
                }
            } catch (IOException e) {
            }
        }
    }
}
