package com.example.step03.ex01;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: FirstServlet#doGet()");

        // RequestDispatcher 인스턴스의 forward 메서드로 second 서블릿으로 포워드
        /*
        RequestDispatcher dispatcher = request.getRequestDispatcher("second");
        dispatcher.forward(request, response);
        */
        request.getRequestDispatcher("second").forward(request, response);
    }

}