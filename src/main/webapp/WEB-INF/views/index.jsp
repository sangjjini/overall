<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-08
  Time: 오후 3:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>index</title>
</head>
<body>
    <section>
        <c:if test="${!empty log}">
            <div class="user-info">
                <span>${log}님 안녕하세요!</span>
                <div id="like-list"></div>
            </div>
        </c:if>
    </section>
</body>
</html>
