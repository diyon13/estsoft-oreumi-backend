package com.example.step01.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {
    public ExchangeServlet() {
        super();
        System.out.println("CALL: ExchangeServlet#ExchangeSurvlet()");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("CALL: ExchangeServlet#init()");
    }

    @Override
    public void destroy() {
        System.out.println("CALL: ExchangeServlet#destroy()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: ExchangeServlet#doGet()");

        // 클라이언트에서 전송한 데이터의 문자 인코딩 설저
        request.setCharacterEncoding("UTF-8");

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
                    <title>Currency Exchange Calculation</title>
                </head>
                <body>
                <h1>Currency Exchange Calculation</h1>
                
                <form action="exchange" method="get">
                    <input type="hidden" name="command" value="calculate">
                    KRW <input type="text" name="krw"> to
                    <select name="currency">
                        <option value="usd">US Dollar</option>
                        <option value="eur">Euro</option>
                        <option value="jpy">Japan Yen</option>
                        <option value="cny">China Yuan</option>
                    </select>
                
                    <input type="submit" name="calc" value="Calculate">
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
        System.out.println("CALL: ExchangeServlet#doPost()");
    }
}