<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="exception" type="java.lang.Exception" scope="request" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>An Exception Has Occurred!</title>
</head>
<body>

<h1>An Exception Has Occurred!</h1>

<pre>${exception.message}</pre>

</body>
</html>