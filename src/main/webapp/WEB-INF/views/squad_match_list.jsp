<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2023-08-15
  Time: 오후 8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>경기 목록</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/reset.css" type="text/css">
    <link rel="stylesheet" href="/css/match_list.css" type="text/css">
    <script src="/script/squad_match.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
    <div class="contents">
        <div class="container">
            <div class="sub_container">
                <div id="match_table_wrap">
                    <ul class="match_table">
                        <%-- <li class="table_attribute_wrap">
                            <ul class="table_attribute">
                                <li>시간</li>
                                <li>제목</li>
                                <li>팀명</li>
                                <li>아아</li>
                            </ul>
                        </li> --%>
                        <li id="lines">
                        </li>
                        <li></li>
                    </ul>
                </div>
                <div class="buttons">
                    <c:choose>
                        <c:when test="${empty log}">
                            <a href="/squad/match" class="new_match">
                                <img src="/images/write_icon.png" alt="" class="icon">
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/login" class="new_match">
                                <img src="/images/write_icon.png" alt="" class="icon">
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
<c:import url="footer.jsp"/>
</body>
</html>
