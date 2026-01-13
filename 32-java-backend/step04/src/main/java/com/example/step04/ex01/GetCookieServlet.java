package com.example.step04.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getCookie")
public class GetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: GetCookieServlet#doGet()");

        // 클라이언트에서 전송한 데이터의 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 쿠키를 읽어옴
       Cookie[] cookies = request.getCookies();

        // 클라이언트에 응답
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        StringBuilder output = new StringBuilder("""
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Set Cookie</title>
</head>
<body>

<h1>Set Cookie</h1>

<ul>
""");

        // Cookie 인스턴스의 배열에서 쿠키를 차례대로 확인
        for (Cookie cookie : cookies) {
//            output.append(String.format("<li>%s = \"%s\"</li>", cookie.getName(), cookie.getValue()));
            output.append(String.format("<li>%s = \"%s\"</li>",
                    cookie.getName(), URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8)));
        }

        output.append("</ul></body></html>");

        out.println(output);
        out.close();
    }
}
