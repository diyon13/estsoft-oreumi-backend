package com.example.step03.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/getContext")
public class GetContextServlet extends HttpServlet {
    public GetContextServlet() {
        super();
        System.out.println("CALL: GetContextServlet#GetContextServlet()");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("CALL: GetContextServlet#init()");
    }

    @Override
    public void destroy() {
        System.out.println("CALL: GetContextServlet#destroy()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: GetContextServlet#doGet()");

        // ServletContext 인스턴스를 받아옴
        ServletContext context = getServletContext();

        // ServletContext 인스턴스에 바인딩된 자원을 받아옴
        List<?> memberList = (List<?>) getServletContext().getAttribute("memberList");

        // 응답할 데이터의 MIMe 타입 설정
        response.setContentType("text/html;charset=UTF-8");

        //  PrintWriter 인스턴스를 생성
        PrintWriter out = response.getWriter();

        // 클라이언트에 응답할 데이터를 HTML 형식으로 생성
        String output = String.format("""
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Get servletContext</title>
</head>
<body>

<h1>Get servletContext</h1>

<ul>
    <li>%s</li>
    <li>%s</li>
</ul>

</body>
</html>
""", memberList.get(0), memberList.get(1));

        out.println(output);
        out.close();
    }
}