package com.example.step04.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setCookie")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("CALL: SetCookieServlet#doGet()");

        // 쿠키의 이름과 값
        String name = "course";
        String value = "Java Back-End Development";

        //Cookie 인스턴스 생성
//        Cookie cookie = new Cookie(name, value);
//        Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
        Cookie cookie = new Cookie(name, URLEncoder.encode(value, StandardCharsets.UTF_8));

        // 쿠키의 유효 기간을 초 단위로 설정
        cookie.setMaxAge(24 * 60 * 60);

        //HttpServletResponse 인스턴스에 쿠키를 추가
        response.addCookie(cookie);
        System.out.println("COOKIE: " + cookie);

        // 클라이언트에 응답
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String output = String.format("""
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Set Cookie</title>
</head>
<body>

<h1>Set Cookie</h1>

<ul>
    <li>Cookie Name: %s</li>
    <li>Cookie Value: %s</li>
</ul>

<p>
    <a href="getCookie">Go to the getCookie page</a>
</p>

</body>
</html>
""", name, value);

        out.println(output);
        out.close();
    }
}