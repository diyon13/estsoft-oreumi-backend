package com.example.step01.ex02;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
    public LoginServlet2() {
        super();
        System.out.println("CALL: LoginServlet2#LoginServlet2()");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("CALL: LoginServlet2#init()");
    }

    @Override
    public void destroy() {
        System.out.println("CALL: LoginServlet2#destroy()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: LoginServlet2#doGet()");

        // 클라이언트에서 전송한 데이터의 문자 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // HttpServletRequest 인스턴스의 getParameter 메서드로
        // 클라이언트에서 전송한 데이터(request parameters; 요청 매개 변수)를 참조

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //클라이언트에서 전송한 데이터를 출력
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // TODO: 비지니스 로직(business logic)

        // 응답할 데이터의 미디어 형식(media type; MIME type)설정
        response.setContentType("text/html;charset=UTF-8");

        // 서블릿 컨테이너에게 응답할 데이터를 전달하기 위해 출력 스트림(printWriter 인스턴스)을 생성
        PrintWriter out = response.getWriter();

        // 응답할 데이터를 HTML 형식으로 생성
        String output = "<!DOCTYPE html>\n<html><head><meta charset=\"UTF-8\">" +
                "<title>Your Request</title></head>" +
                "<body><h1>Your Request</h1>" +
                "<ul><li>Username: " + username + "</li><li>Password: " + password + "</li>" +
                "</ul></body></html>";

        // PrintWriter 인스턴스의 print 메서드로 출력 스트림에 응답 데이터를 출력
        out.println(output);

        // 출력 스트림(output stream) 닫기
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: LoginServlet2#doPost()");
    }
}