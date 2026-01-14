package com.example.step04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/viewMember")
public class ViewMemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: ViewMemberServlet#doGet()");

        // HttpSession 인스턴스 생성
        HttpSession session = request.getSession(false);

        // 세션이 생성되어 있는지 확인
        if (session == null) {
            // 세션이 생성되어 있지 않으면 "login.html"로 재지정(redirect)
            response.sendRedirect("login.html");
            return;
        }

        // 세션에 바인딩된 정보를 참조
        boolean isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
        String username = (String) session.getAttribute("username");

        // 세션에 데이터가 바인딩되어 있는지 확인
        if (!isLoggedIn || username == null) {
            response.sendRedirect("login.html");
            return;
        }

        //MemberDao 인스턴스 생성
        MemberDao memberDao = new MemberDao();

        // members 테이블에서 회원 정보를 조회
        MemberVo memberVo = memberDao.getMember(username);

        // 회원 정보가 올바른지 확인
        if (memberVo.getUsername() == null) {
            response.sendRedirect("login.html");
            return;
        }

        // 클라이언트 응답
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String output = String.format("""
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Member Information</title>
</head>
<body>

<h1>Member Information</h1>

<ul>
    <li>Username: %s</li>
    <li>Name: %s</li>
    <li>Email: %s</li>
    <li>Created At: %s</li>
</ul>

</body>
</html>""", memberVo.getUsername(), memberVo.getName(), memberVo.getEmail(), memberVo.getCreatedAt());

        out.println(output);
        out.close();
    }
}