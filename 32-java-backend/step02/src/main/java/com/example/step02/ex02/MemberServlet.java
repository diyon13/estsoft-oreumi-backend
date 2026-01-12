package com.example.step02.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/member2")
public class MemberServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: MemberServlet#2doGet()");

        // MemberDao 인스턴스 생성
        MemberDao memberDao = new MemberDao();

        // members 테이블을 조회
        List<MemberVo> memberList = memberDao.getMemberList();

        // 응답할 데이터의 MIME 타입 설정
        response.setContentType("text/html;charset=UTF-8");

        // PrintWriter 인스턴스 생성
        PrintWriter out = response.getWriter();

        // 클라이언트에 응답할 데이터를 HTML 형식으로 생성해서 출력
        out.print(getOutput(memberList));

        // 출력 스트림 닫기
        out.close();
    }

    private StringBuilder getOutput(List<MemberVo> memberList) {
        StringBuilder output = new StringBuilder("""
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Member List</title>
    </head>
    <body>
        <h1>Member List</h1>
    
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Created At</th>
                </tr>
            </thead>
            <tbody>
    """);

        for (MemberVo memberVo : memberList) {
            output.append(String.format("""
    <tr>
        <td>%d</td>
        <td>%s</td>
        <td>%s</td>
        <td>%s</td>
        <td>%s</td>
        <td>%s</td>
    </tr>
    """, memberVo.getId(), memberVo.getUsername(), memberVo.getPassword(),
                    memberVo.getName(), memberVo.getEmail(), memberVo.getCreatedAt()));
        }

        output.append("</tbody></table></body></html>");

        return output;
    }
}