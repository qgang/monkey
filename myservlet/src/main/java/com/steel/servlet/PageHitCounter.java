package com.steel.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 以下是实现一个简单的基于 Servlet 生命周期的网页点击计数器需要采取的步骤：
   1.在 init() 方法中初始化一个全局变量。
   2.每次调用 doGet() 或 doPost() 方法时，都增加全局变量。
   3.如果需要，您可以使用一个数据库表来存储全局变量的值在 destroy() 中。在下次初始化 Servlet 时，该值可在 init() 方法内被读取。这一步是可选的。
   4.如果您只想对一个 session 会话计数一次页面点击，那么请使用 isNew() 方法来检查该 session 会话是否已点击过相同页面。这一步是可选的。
   5.您可以通过显示全局计数器的值，来在网站上展示页面的总点击量。这一步是可选的。
 在这里，我们假设 Web 容器将无法重新启动。如果是重新启动或 Servlet 被销毁，计数器将被重置。
 */
public class PageHitCounter extends HttpServlet {
    private int hitCount;

    public void init()
    {
        // 重置点击计数器
        hitCount = 0;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        // 增加 hitCount
        hitCount++;
        PrintWriter out = response.getWriter();
        String title = "总点击量";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<h2 align=\"center\">" + hitCount + "</h2>\n" +
                "</body></html>");
    }

    public void destroy()
    {
        // 这一步是可选的，但是如果需要，您可以把 hitCount 的值写入到数据库
    }
}
