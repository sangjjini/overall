<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-09
  Time: 오전 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <link type="text/css" href="/css/header.css" rel="stylesheet" />
    <script src="/script/header.js"></script>
</head>
<body>
<header id = "header">
    <div class="main_nav">
        <div class="logo"><a href="/">LOGO</a></div>
        <div class="nav_wrap">
            <div class="nav_list"><a href="/introduce">오버롤소개</a></div>
            <div class="nav_list"><a href="/squad">스쿼드</a></div>
            <div class="nav_list"><a href="/squad/matchList">스쿼드매치</a></div>
        </div>
    </div>
    <div class="nav_right">
    <c:choose>
        <c:when test="${empty log}">
            <div class="login_wrap">
                <div class="login"><a href="/login">로그인</a></div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="mypage_wrap">
                <div class="mypage"><a href="/mypage">마이페이지</a></div>
            </div>
        </c:otherwise>
    </c:choose>
    </div>
</header>
</body>
