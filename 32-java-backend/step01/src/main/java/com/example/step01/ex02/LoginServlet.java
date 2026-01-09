package com.example.step01.ex02;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
        System.out.println("CALL: LoginServlet#LoginServlet()");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("CALL: LoginServlet#init()");
    }

    @Override
    public void destroy() {
        System.out.println("CALL: LoginServlet#destroy()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: LoginServlet#doGet()");

        // 클라이언트에서 전송한 데이터의 문자 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // HttpServletRequest 인스턴스의 getParameter 메서드로
        // 클라이언트에서 전송한 데이터(request parameters; 요청 매개 변수)를 참조

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //클라이언트에서 전송한 데이터를 출력
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: LoginServlet#doPost()");
    }
}