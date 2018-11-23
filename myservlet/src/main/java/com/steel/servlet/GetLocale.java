package com.steel.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * @author gang.qin
 * @date 2018-11-19.
 */
public class GetLocale extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取客户端的区域设置
        Locale locale = request.getLocale();
        String country = locale.getCountry();
        String displayCountry = locale.getDisplayCountry();
        String language = locale.getLanguage();
        String displanyLanguage = locale.getDisplayLanguage();
        String iso3Country = locale.getISO3Country();
        String iso3Language = locale.getISO3Language();


        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String title = "检测区域设置";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + language + "</h1>\n" +
                "<h1 align=\"center\">" + displanyLanguage + "</h1>\n" +
                "<h2 align=\"center\">" + country + "</h2>\n" +
                "<h2 align=\"center\">" + displayCountry + "</h2>\n" +
                "<h2 align=\"center\">" + iso3Country + "</h2>\n" +
                "<h2 align=\"center\">" + iso3Language + "</h2>\n" +
                "</body></html>");
    }
}
