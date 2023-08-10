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
    <title>오버롤</title>
    <%-- 제이쿼리--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link type="text/css" href="${pageContext.request.contextPath}/css/reset.css" rel="stylesheet" />
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div id = container>
        <h1>index</h1>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
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
