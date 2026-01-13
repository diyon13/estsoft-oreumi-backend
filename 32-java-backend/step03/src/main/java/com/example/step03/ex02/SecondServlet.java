package com.example.step03.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/second2")
public class SecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: SecondServlet#doGet()");

        //first2 서블릿에서 바인딩한 데이터를 참조
        String name = (String)request.getAttribute("name");

        // 응답할 데이터의 MIME 타입 설정
        response.setContentType("text/html;charset=UTF-8");

        //PrintWriter 인스턴스 생성
        PrintWriter out = response.getWriter();

        // 클라이언트에 응답할 데이터를 HTML 형식으로 생성
        String output = String.format("""
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Dispatch Forward Test</title>
</head>
<body>
<h1>Dispatch Forward Test</h1>

<ul>
    <li>Name: %s</li>
</u1>

</body>
</html>
""", name);
        //클라이언트에 응답할 데이터를 출력 스트림에 출력
        out.print(output);

        out.close();
    }

}