package com.example.step01.ex01;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    @Override
    public void init() {
        System.out.println("CALL: FirstServlet#init()");
    }

    @Override
    public void destroy() {
        System.out.println("CALL: FirstServlet#destroy()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("CALL: FirstServlet#doGet()");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("CALL: FirstServlet#doPost()");
    }
}
