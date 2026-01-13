package com.example.step03.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/first2")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: FirstServlet2#doGet()");

        // HttpServletRequest 인스턴스에 데이터를 바인딩
        request.setAttribute("name","potter");

        // RequestDispatcher 인스턴스의 forward 메서드로 second2 서블릿으로 포워드
        request.getRequestDispatcher("second2").forward(request, response);
    }

}