package com.example.step04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: LoginServlet#doGet()");

        // 클라이언트에게 오류 페이지를 응답
       printErrorPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: LoginServlet#doPost()");

        // 클라이언트에서 전송한 데이터를 참조
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 클라이언트에서 전송한 데이터의 유효성 검사(validation)
        if (username == null || password == null) {
            //클라이언트에 오류 페이지를 응답
            printErrorPage(request, response);
            return;
        }

        // MemberVo 인스턴스를 생성해서, 클라이언트에서 전송한 로그인 정보를 저장
        MemberVo memberVo = new MemberVo();

        memberVo.setUsername(username);
        memberVo.setPassword(password);

        // MemberDao 인스턴스 생성
        MemberDao memberDao = new MemberDao();

        // 클라이언트에 응답
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String output = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log-in Status</title>
</head>
<body>

<h1>Log-in Status</h1>
""";
        // 클라이언트에서 전송한 로그인 정보가 올바른지 확인
        if (memberDao.isValidLogin(memberVo)) {
            //로그인 정보가 올바른 경우, 로그인 정보를 세션에 저장해서 다음 요청에서 활ㅇ룡
            HttpSession session = request.getSession();

            session.setAttribute("isLoggedIn", true);
            session.setAttribute("username", username);

            output += String.format("""
<p>You have logged in <code>%s</code></p>
<p><a href="viewMember">View member information</a></p>
""", username);
        } else {
            output += """
<p>Invalid username or password.</p>
<p><a href="login.html">Please log in again</a></p>
""";
        }

        output += "</body></html>";

        out.println(output);
        out.close();
    }

    /**
     * 클라이언트에 오류 페이지를 응답
     *
     * @param request HttpServletRequest 인스턴스
     * @param response HttpServletResponse 인스턴스
     */

    public void printErrorPage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("CALL: LoginServlet#doGet()");

        // 클라이언트에게 오류 페이지를 응답
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String output = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log-in Status</title>
</head>
<body>

<h1>Log-in Status</h1>

<p>Invalid access!</p>
<p><a href="login.html">Please log in again.</a></p>

</body>
</html>
""";
        out.println(output);
        out.close();
    }
}
