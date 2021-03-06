package concurrency;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by gang.qin on 2015/11/7.
 * 一个基于线程池技术的简单 Web 服务器
 * 1. 用于处理 HTTP 请求，目前只能处理简单的文本和 JPG 图片内容
 * 2. 使用 main 线程不断的接受客户端 Socket 的连接，将连接及请求提交给线程池处理
 */
public class SimpleHttpServer {
    // 处理 httpRequest 的线程池
    static ThreadPool<HttpRequestHandler> threadPool = new DefualtThreadPool<HttpRequestHandler>();

    static String basePath; // SimpleHttpServer 的根路径
    static ServerSocket serverSocket;
    static int port = 8080; // 服务监听器

    public static void setPort (int port) {
        if (port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    // 启动 SimpleHttpServer
    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            // 接收一个客户端 Socket, 生成一个 HttpRequestHandler，放入线程池
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable {
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        public void run () {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                // 由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];

                out = new PrintWriter(socket.getOutputStream());

                // 如果请求资源的后缀为 jpg 或者 ico，则读取资源并输出
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array, 0, array.length);
                } else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                }
                while ((line = br.readLine()) != null) {
                    out.println(line);
                }
                out.flush();
            } catch (Exception e) {
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                clonse(br, in, reader, out, socket);
            }
        }
    }

    // 关闭流或者 Socket
    private static void clonse(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
