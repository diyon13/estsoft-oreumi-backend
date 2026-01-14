package com.example.step04.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("CALL: MessageServlet#doPost()");

        // 클라이언트에서 전송한 데이터를 참조
        String message = request.getParameter("message");

        // 클라이언트에 응답
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String output = String.format("""
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>메시지 입력</title>
</head>
<body>

<h1>메시지 입력</h1>

<p>당신의 메시지는 &quot;%s&quot;입니다.</p>

</body>
</html>
""", message);

        out.println(output);
        out.close();
    }
}