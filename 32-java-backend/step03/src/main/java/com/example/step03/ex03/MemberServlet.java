package com.example.step03.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: MemberServlet#doGet()");

        // MemberDao 인스턴스 생성
        MemberDao memberDao = new MemberDao();

        // members 테이블을 조회
        List<MemberVo> memberList = memberDao.getMemberList();

        // HttpServletRequest 인스턴스에 회원 목록을 바인딩
        request.setAttribute("memberList", memberList);

        // ViewMember.List 서블리승로 포워딩
        request.getRequestDispatcher("viewMemberList").forward(request, response);
    }
}