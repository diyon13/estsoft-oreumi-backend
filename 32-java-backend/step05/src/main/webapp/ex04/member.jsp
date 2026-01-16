<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2026-01-14 수
  Time: 오후 3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         import="com.example.step05.ex02.*"
         import="java.util.List"
         language="java" %>
<%
    //클라이언트에 전송한 데이터를 참조
    request.setCharacterEncoding("UTF-8");
%>
<%-- useBean 액션 태그로 자바빈 생성 --%>
<jsp:useBean id="param" class="com.example.step05.ex02.MemberVo" />

<%-- setProperty 액션 태그로 자바빈의 속성을 설정 --%>
<%--<jsp:setProperty name="param" property="username" value="<%= request.getParameter("username")%>" />--%>
<%--<jsp:setProperty name="param" property="password" value="<%= request.getParameter("password")%>" />--%>
<%--<jsp:setProperty name="param" property="name" value="<%= request.getParameter("name")%>" />--%>
<%--<jsp:setProperty name="param" property="email" value="<%= request.getParameter("email")%>" />--%>

<%--<jsp:setProperty name="param" property="username" param="username" />--%>
<%--<jsp:setProperty name="param" property="password" param="password" />--%>
<%--<jsp:setProperty name="param" property="name" param="name" />--%>
<%--<jsp:setProperty name="param" property="email" param="email" />--%>

<%--<jsp:setProperty name="param" property="username" />--%>
<%--<jsp:setProperty name="param" property="password" />--%>
<%--<jsp:setProperty name="param" property="name" />--%>
<%--<jsp:setProperty name="param" property="email" />--%>

<jsp:setProperty name="param" property="*" />


<%
    // MemberDao 인스턴스 생성
    MemberDao memberDao = new MemberDao();

    // 새로운 회원 정보 등록
    if (param.getUsername() != null) {
        memberDao.insertMember(param);
    }


    // 전체 회원 목록을 조회
    List<MemberVo> memberList = memberDao.getMemberList();
%>
<html lang="ko">
<head>
    <title>Title</title>
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
            <th>created At</th>
        </tr>
    </thead>

    <tbody>
<% if (memberList.isEmpty()) { %>
        <tr>
            <td colspan="6">There are no registered members.</td>
        </tr>
<% } else { %>
<% for (MemberVo memberVo : memberList) { %>
        <tr>
            <td> <%= memberVo.getId() %></td>
            <td> <%= memberVo.getUsername() %></td>
            <td> <%= memberVo.getPassword() %></td>
            <td> <%= memberVo.getName() %></td>
            <td> <%= memberVo.getEmail() %></td>
            <td> <%= memberVo.getCreatedAt() %></td>
        </tr>
<% } %>
<% } %>
    </tbody>
</table>

<p><a href="member-form.html">Register New Member</a></p>

</body>
</html>
