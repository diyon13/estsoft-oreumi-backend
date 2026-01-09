package com.example.step01.ex05;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/multiplication")
public class MultiplicationServlet extends HttpServlet {
    public MultiplicationServlet() {
        super();
        System.out.println("CALL: MultiplicationServlet#MultiplicationServlet()");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("CALL: MultiplicationServlet#init()");
    }

    @Override
    public void destroy() {
        System.out.println("CALL: MultiplicationServlet#destroy()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: MultiplicationServlet#doGet()");

        // 클라이언트에서 전송한 데이터의 문자 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 클라이언트에서 전송한 데이터를 참조
        String command = request.getParameter("command");
        String numberParam = request.getParameter("number");

        // 응답할 데이터의 미디어 형식(MIME type) 설정
        response.setContentType("text/html;charset=UTF-8");

        // printWriter 인스턴스 생성
        PrintWriter out = response.getWriter();

        // 클라이언트에 응답할 데이터 생성
        String output = """
        <!DOCTYPE html>
        <html lang="ko">
        <head>
            <meta charset="UTF-8">
            <title>Multiplication Table</title>
        </head>
        <body>
        <h1>Multiplication Table</h1>
        """;

        //구구단 계산
        if (command != null && command.equals("print")) {
            int number = Integer.parseInt(request.getParameter("number"));

            if (number >= 2 && number <= 9) {
                for (int i = 1; i <= 9; i++) {
                    output += String.format("<p>%d × %d = %d</p>", number, i, number * i);
                }
            }
        }

        output += """
        <form action="multiplication" method="get">
            <p>
                <label>Multiplication Table for
                    <input type="text" name="number">
                    <input type="hidden" name="command" value="print">
                    <input type="submit" value="Print">
                </label>
            </p>
        </form>
        </body>
        </html>
        """;

        // 출력 스트림에 응답할 데이터를 출력
        out.println(output);

        // 출력 스트림 (output stream) 닫기
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: MultiplicationServlet#doPost()");
    }
}