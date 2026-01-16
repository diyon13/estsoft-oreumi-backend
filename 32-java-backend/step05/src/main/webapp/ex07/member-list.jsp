<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="memberList" type="java.util.List" scope="request" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Member List</title>
    <script>
        window.addEventListener('load', function () {
            document.querySelectorAll('.delete').forEach(function (element) {
                element.addEventListener('click', function (event) {
                    if (!window.confirm('Are you sure to delete the member?')) {
                        event.preventDefault();
                        return;
                    }

                    if (!window.confirm('Once deleted, it cannot be recovered. Are you sure to delete the member?')) {
                        event.preventDefault();
                    }
                });
            })
        });
    </script>
</head>
<body>

<h1>Member List</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Name</th>
        <th>Email</th>
        <th>Created At</th>
        <th>Action</th>
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
                    <td>${memberVo.name}</td>
                    <td>${memberVo.email}</td>
                    <td>${memberVo.createdAt}</td>
                    <td>
                        [<a href="${contextPath}/member/update?username=${memberVo.username}">UPDATE</a>]
                        [<a href="${contextPath}/member/delete?id=${memberVo.id}" class="delete">DELETE</a>]
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<p>
    [<a href="${contextPath}/member/register">Register New Member</a>]
</p>

</body>
</html>