package com.example.step05.ex06;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/member.do")
public class MemberController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: MemberController#doGet()");

        // Model 역할을 수행하는 MemberDao 인스턴스를 생성
        MemberDao memberDao = new MemberDao();

        // MemberDao 인스턴스로 회원 목록을 조회
        List<MemberVo> memberList = memberDao.getMemberList();

        // 뷰(view)에서 회원 목록에 접근할 수 있도록 HttpServletRequest 인스턴스에 바인딩
        request.setAttribute("memberList", memberList);

        // 뷰(view) 역할을 수행하는 "member-list.jsp"로 포워딩
        request.getRequestDispatcher("/ex06/member-list.jsp").forward(request, response);
    }
}