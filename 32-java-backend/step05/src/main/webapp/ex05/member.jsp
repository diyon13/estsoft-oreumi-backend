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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //클라이언트에 전송한 데이터를 참조
    request.setCharacterEncoding("UTF-8");
%>
<%-- useBean 액션 태그로 자바빈 생성 --%>
<jsp:useBean id="param" class="com.example.step05.ex02.MemberVo" />
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

    // 표현 언어에서 접근할 수 있도록 memberList 인스턴스를 request 인스턴스에 바인딩
    request.setAttribute("memberList", memberList);
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
<c:choose>
    <c:when test="${empty memberList}">
        <tr>
            <td colspan="6">There are no registered members.</td>
        </tr>
    </c:when>
    <c:otherwise>
        <c:forEach var="memberVo" items="${memberList}">
            <tr>
                <td>${memberVo.id}</td>
                <td>${memberVo.username}</td>
                <td>${memberVo.password}</td>
                <td>${memberVo.name}</td>
                <td>${memberVo.email}</td>
                <td>${memberVo.createdAt}</td>
            </tr>
        </c:forEach>
    </c:otherwise>
</c:choose>
    </tbody>
</table>

<p><a href="member-form.html">Register New Member</a></p>

</body>
</html>
