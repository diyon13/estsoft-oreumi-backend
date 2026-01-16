<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:useBean id="action" type="java.lang.String" scope="request" />
<jsp:useBean id="memberVo" class="com.example.step05.ex07.MemberVo" scope="request" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Membership Form</title>
    <style>
        #memberForm > p > label > span {
            display: inline-block;
            width: 90px;
            padding-right: 1em;
            text-align: right;
        }
    </style>
    <script>
        window.addEventListener('load', function () {
            document.getElementById('memberForm').addEventListener('submit', function (event) {
                const lengthUsername = this.username.value.length;
                const lengthPassword = this.password.value.length;
                const lengthName = this.name.value.length;
                const lengthEmail = this.email.value.length;

                if (!lengthUsername || !lengthPassword || !lengthName || !lengthEmail) {
                    event.preventDefault();
                    window.alert('You must fill out all fields!');
                }
            });
        });
    </script>
</head>
<body>

<h1>Membership Form</h1>

<form action="${contextPath}/member/${action}" method="post" id="memberForm">
    <c:choose>
        <c:when test="${action == 'update'}">
            <input type="hidden" name="id" value="${memberVo.id}">
            <input type="hidden" name="username" value="${memberVo.username}">
            <p>
                <label>
                    <span>Username: </span>${memberVo.username}
                </label>
            </p>
        </c:when>
        <c:otherwise>
            <p>
                <label>
                    <span>Username: </span><input type="text" name="username" required>
                </label>
            </p>
        </c:otherwise>
    </c:choose>
    <p>
        <label>
            <span>Password: </span><input type="password" name="password" value="${memberVo.password}" required>
        </label>
    </p>
    <p>
        <label>
            <span>Name: </span><input type="text" name="name" value="${memberVo.name}" required>
        </label>
    </p>
    <p>
        <label>
            <span>Email: </span><input type="text" name="email" value="${memberVo.email}" required>
        </label>
    </p>
    <p>
        <input type="submit" value="${fn:toUpperCase(action)}">
    </p>
</form>

</body>
</html>