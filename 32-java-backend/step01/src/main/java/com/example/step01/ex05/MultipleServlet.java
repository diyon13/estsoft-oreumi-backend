package com.example.step01.ex05;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/multiple")
public class MultipleServlet extends HttpServlet {
    public MultipleServlet() {
        super();
        System.out.println("CALL: MultipleServlet#MultipleServlet()");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("CALL: MultipleServlet#init()");
    }

    @Override
    public void destroy() {
        System.out.println("CALL: MultipleServlet#destroy()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: MultipleServlet#doGet()");

        // 클라이언트에서 전송한 데이터의 문자 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 클라이언트에서 전송한 데이터를 참조
        String number = request.getParameter("number");

        // 응답할 데이터의 미디어 형식(MIME type) 설정
        response.setContentType("text/html;charset=UTF-8");

        // printWriter 인스턴스 생성
        PrintWriter out = response.getWriter();

        // 응답할 데이터를 HTML 형식으로 생성
        StringBuilder output = new StringBuilder("""
            <!DOCTYPE html>
            <html lang="ko">
            <head>
                <meta charset="UTF-8">
                <title>Multiplication Table</title>
            </head>
            <body>
            <h1>Multiplication Table</h1>
            """);

            if (number != null) {
                try {
                    // 클라이언트에서 전송된 값을 정수로 변환
                    int num = Integer.parseInt(number);

                    // 클라이언트에서 전송한 값을 검증
                    if(num < 2 || num > 9) {
                        throw new RuntimeException();
                    }

                    // 구구단을 <pre> 요소에 출력
                    output.append("<pre>");

                    for (int i = 1; i <= 9; i++) {
                        output.append(String.format("%d * %d = %d\n", num, i, num * i));
                    }

                    output.append("</pre>");

                } catch (Exception e) {
                    output.append(String.format("<p>value is an invalid value.</p>", number));
                }
            }

            output.append("""
            <form action="multiple" method="get">
                <p>
                    <label>Multiplication Table for
                        <input type="number" name="number" min="2" max="9" step="1">
                        <input type="hidden" name="command" value="print">
                        <input type="submit" value="Print">
                    </label>
                </p>
            </form>
            </body>
            </html>
            """);

        // 출력 스트림에 응답할 데이터를 출력
        out.println(output);

        // 출력 스트림 (output stream) 닫기
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: MultipleServlet#doPost()");
    }
}