package com.steel.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author gang.qin
 * @date 2018-10-24.
 */
public class HelloWorld extends HttpServlet {

    private String message;

    @Override
    public void init() throws ServletException {
        System.out.println("begin init ...");
        message = "Hello Word";
        System.out.println("end init ...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("begin doGet ...");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<h1>" + message + "</h1>");
        System.out.println("end doGet ...");
    }

    @Override
    public void destroy() {
        System.out.println("begin destroy ...");
        super.destroy();
        System.out.println("end destroy ...");
    }
}
