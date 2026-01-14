<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2026-01-14 수
  Time: 오후 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="exception.jsp" %>
<%
    // 클라이언트에서 전송한 데이터를 참조
    request.setCharacterEncoding("UTF-8");
    int num = Integer.parseInt(request.getParameter("num"));

    // 합계를 저장할 변수
    int sum = 0;

    // for 구문으로 1부터 변수 num까지의 자연수들의 합겨를 계산
    for (int i = 1; i <= num; i++) {
        sum += i;
    }
%>
<html lang="ko">
<head>
    <title>Calculate the Sum of Integers</title>
</head>
<body>

<h1>Sum of Integers</h1>
<p>
    The sum from 1 to <%= num %> is <%= sum %>
</p>

</body>
</html>
