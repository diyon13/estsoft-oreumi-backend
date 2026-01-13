package com.example.step03.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewMemberList")
public class ViewMemberListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CALL: ViewMemberListServlet#doGet()");

        // HttpServletRequest 인스턴스에 바인딩된 회원 목록을 참조
//        @SuppressWarnings("unchecked")
//        List<MemberVo> memberList = (List<MemberVo>)request.getAttribute("memberList");
        List<?> memberList = (List<?>)request.getAttribute("memberList");

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
    private StringBuilder getOutput(List<?> memberList) {
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
      <th>Delete</th>
    </tr>
  </thead>
  <tbody>
""");

        for (Object obj : memberList) {
            /*
            if (obj instanceof MemberVo {
                MemberVo memberVo = (MemberVo)obj;
             */
            // 위 조건문과 형 변환 문장을 패턴 변수(pattern variable)를 이용해서 작성
            if (obj instanceof MemberVo memberVo) {
                output.append(String.format("""
    <tr>
      <td>%d</td>
      <td>%s</td>
      <td>%s</td>
      <td>%s</td>
      <td>%s</td>
      <td>%s</td>
      <td><a href="member4?command=delete&id=%d">[X]</a></td>
    </tr>
""", memberVo.getId(), memberVo.getUsername(), memberVo.getPassword(),
                    memberVo.getName(), memberVo.getEmail(), memberVo.getCreatedAt(), memberVo.getId()));
            }
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