package com.example.step05.ex07;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: MemberController#doGet()");

        // 모델(model) 역할을 수행하는 MemberDao 인스턴스 생성
        MemberDao memberDao = new MemberDao();

        // 클라이언트에 응답하기 위해 포워딩할, 즉 뷰(view)로 사용할 JSP
        String viewPage = "member-list";

        // 요청 경로(path info) 확인
        String action = request.getPathInfo().replace("/", "");
        System.out.println("action: " + action);

        // JSP에서 사용하도록 요청 경로를 HttpServletRequest 인스턴스에 바인딩
        request.setAttribute("action", action);

        // 요청 경로(path info)에 따라 필요한 기능을 수행
        switch (action) {
            case "register" ->
                    viewPage = "member-form";
            case "update" -> {
                viewPage = "member-form";

                // 클라이언트에서 전송한 username 확인
                String username = request.getParameter("username");

                // TODO: username 정보가 유효한 값인지, members 테이블에 있는지 확인하고,
                //       그렇지 않으면 적절한 뒷처리를 한다.

                // members 테이블에서 회원 정보를 조회
                MemberVo memberVo = memberDao.getMember(username);

                // JSP에서 사용하도록 회원 정보를 HttpServletRequest 인스턴스에 바인딩
                request.setAttribute("memberVo", memberVo);
            }
            case "delete" -> {
                // 클라이언트에서 전송한 데이터를 참조
                String id =  request.getParameter("id");

                // TODO: id 정보가 유효한 값인지, members 테이블에 있는지 확인하고,
                //       그렇지 않으면 적절한 뒷처리를 한다.

                // members 테이블에서 주어진 id 컬럼으로 회원 정보를 삭제
                memberDao.deleteMember(id);

                // "/step05/member/list"로 URL 재지정하고 doGet 메서드를 종료
                response.sendRedirect(request.getContextPath() + "/member/list");
                return;
            }
        }

        // 회원 목록을 출력하는 경우, 즉 포워딩할 JSP가 "member-list"인 경우
        if (viewPage.equals("member-list")) {
            // 전체 회원 목록을 조회
            List<MemberVo> memberList = memberDao.getMemberList();

            // "member-list.jsp"에서 회원 목록에 접근할 수 있도록 HttpServletRequest 인스턴스에 바인딩
            request.setAttribute("memberList", memberList);
        }

        // 포워딩할 JSP의 URI 생성
        String viewPageUri = String.format("/ex07/%s.jsp", viewPage);

        // 뷰(view)로 사용할 JSP로 포워딩
        request.getRequestDispatcher(viewPageUri).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: MemberController#doPost()");

        // 모델(model) 역할을 수행하는 MemberDao 인스턴스 생성
        MemberDao memberDao = new MemberDao();

        // 요청 경로(path info) 확인
        String action = request.getPathInfo().replace("/", "");
        System.out.println("action: " + action);

        // TODO: 클라이언트에서 전송한 데이터는 반드시 검증하고 사용한다.

        // 클라이언트에서 전송한 값으로 MemberVo 인스턴스를 생성
        MemberVo memberVo = new MemberVo(
                0,
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"),
                null
        );

        // 요청 경로(path info)에 따라 필요한 기능을 수행
        if (action.equals("register")) {
            // TODO: username이나 email처럼 중복되면 안 되는 컬럼은 꼭 확인한다.

            // MemberVo 인스턴스를 members 테이블에 추가
            memberDao.insertMember(memberVo);

            // TODO: 테이블에 새로운 레코드가 제대로 추가됐는지 확인한다.
        } else if (action.equals("update")) {
            // TODO: username 정보가 members 테이블에 있는지 확인한다.
            // TODO: username이나 email처럼 중복되면 안 되는 컬럼은 꼭 확인한다.

            // members 테이블의 레코드를 갱신
            memberDao.updateMember(memberVo);

            // TODO: 테이블에 새로운 레코드가 제대로 갱신됐는지 확인한다.
        }

        // "/step05/member/list"로 URL 재지정
        response.sendRedirect(request.getContextPath() + "/member/list");
    }
}
