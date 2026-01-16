<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="memberList" type="java.util.List" scope="request" />
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

</body>
</html>