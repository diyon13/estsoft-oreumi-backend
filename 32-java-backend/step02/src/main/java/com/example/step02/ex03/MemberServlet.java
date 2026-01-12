package com.example.step02.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/member3")
public class MemberServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("CALL: MemberServlet3#doGet()");

        printMemberList(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: MemberServlet3#doPost()");

        // 클라이언트에서 전송한 데이터의 문자 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 클라이언트에서 전송한 데이터를 참조
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // 클라이언트에서 전송한 데이터는 반드시 검증하고, 유효한 값인 경우에만 사용한다.
        if (username != null && password != null && name != null && email != null) {
            // MemberVo 인스턴스 생성
            MemberVo memberVo = new MemberVo(0, username, password, name, email, null);

            // MemberDao 인스턴스 생성
            MemberDao memberDao = new MemberDao();

            // 새로운 회원 정보 등록
            memberDao.insertMember(memberVo);
        }

        // 회원 목록을 클라이언트에 응답
        printMemberList(request, response);
    }

    /**
     * 회원 목록을 클라이언트에 응답
     */
    public void printMemberList(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // MemberDao 인스턴스 생성
        MemberDao memberDao = new MemberDao();

        // members 테이블을 조회
        List<MemberVo> memberList = memberDao.getMemberList();

        // 응답할 데이터의 MIME 타입 설정
        response.setContentType("text/html; charset=UTF-8");

        // PrintWriter 인스턴스 생성
        PrintWriter out = response.getWriter();

        // 클라이언트에 응답할 데이터를 HTML 형식으로 생성해서 출력
        out.print(getOutput(memberList));

        // 출력 스트림 닫기
        out.close();
    }

    /**
     * HTML 형식으로 회원 목록을 생성
     *
     * @param memberList 회원 목록
     * @return HTML 형식의 문자열
     */
    private StringBuilder getOutput(List<MemberVo> memberList) {
        StringBuilder output = new StringBuilder("""
<!DOCTYPE html>
<html lang="ko">
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

        output.append("""
  </tbody>
</table>

<p>
  <a href="member-form.html">Membership Registration</a>
</p>

</body>
</html>
""");

        return output;
    }
}