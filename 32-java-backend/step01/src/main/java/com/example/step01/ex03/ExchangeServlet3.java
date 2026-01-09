package com.example.step01.ex03;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;

@WebServlet("/exchange3")
public class ExchangeServlet3 extends HttpServlet {
    // 환율 정보를 담고 있는 정적 상수(static constant)
    private static final double USD_RATE = 0.00068;
    private static final double EUR_RATE = 0.00058;
    private static final double JPY_RATE = 0.106;
    private static final double CNY_RATE = 0.00475;

    // 환율 정보를 저장할 HashMap 인스턴스
    private final Map<String, Double> exchangeRates = new HashMap<>();

    public ExchangeServlet3() {
        super();
        System.out.println("CALL: ExchangeServlet3#ExchangeSurvlet()");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("CALL: ExchangeServlet3#init()");

        // 환율 정보를 HashMap 인스턴스에 저장
        exchangeRates.put("usd", USD_RATE);
        exchangeRates.put("eur", EUR_RATE);
        exchangeRates.put("jpy", JPY_RATE);
        exchangeRates.put("cny", CNY_RATE);
    }

    @Override
    public void destroy() {
        System.out.println("CALL: ExchangeServlet3#destroy()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: ExchangeServlet3#doGet()");

        // 클라이언트에서 전송한 데이터의 문자 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 클라이언트에서 전송한 데이터를 참조
        String command = request.getParameter("command");
        String krw = request.getParameter("krw");
        String currency = request.getParameter("currency");

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
        """;

            // 입력 양식에서 데이터가 전달된 경우, 환전 금액을 계산해서 출력
            if (command != null && command.equals("calculate") && exchangeRates.containsKey(currency)) {
                //환전할 통화에 따른 환율
//                double exchangeRate = switch (currency) {
//                    case "usd" -> USD_RATE;
//                    case "eur" -> EUR_RATE;
//                    case "jpy" -> JPY_RATE;
//                    case "cny" -> CNY_RATE;
//                    default -> 0;
//                };

                // 환전 금액을 계산
                double amount = Integer.parseInt(krw) * exchangeRates.get(currency);

                //계산한 환전 금액을 클라이언트에 응답할 데이터에 추가
                output += String.format("<p>KRW %s = %s %.2f</p>", krw, currency.toUpperCase(), amount);
            }

            output += """
        <form action="exchange3" method="get">
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
        System.out.println("CALL: ExchangeServlet3#doPost()");
    }
}