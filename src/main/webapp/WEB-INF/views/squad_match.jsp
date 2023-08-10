<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-09
  Time: 오전 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>스쿼드 매치</title>
    <%-- 제이쿼리 --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/squad_match.css" type="text/css">
</head>
<body>
<c:import url="header.jsp"/>
    <div class="contents">
        <div>
            <img src="${squadA_logo}">

            
        </div>
    </div>
<c:import url="footer.jsp"/>
</body>
</html>
