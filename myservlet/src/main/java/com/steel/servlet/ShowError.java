package com.steel.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gang.qin
 * @date 2018-10-31.
 */
public class ShowError extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("123 begin doGet ...");
        resp.sendError(504, "need auth!");
        System.out.println("123 end doGet ...");
    }
}
