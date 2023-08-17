<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-09
  Time: 오전 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>오버롤 소개</title>
    <%-- 제이쿼리--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link type="text/css" href="/css/introduce.css" rel="stylesheet" />
    <link rel="shortcut icon" href="/images/favicon.ico">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
    <div class="contents">
        <div class="title">
            <h1>
                오버롤이란
            </h1>
            <div class="tab">
                <div class="tab1"><a href="introduce">오버롤소개</a></div>
                <div class="tab2"><a href="team">팀원소개</a></div>

            </div>
        </div>
        <div class="main_contents">
            <h1>오버롤은 자신만의 스쿼드를 구축하고</h1>
            <h1>풋살경기를 매칭해주는 웹 서비스 입니다</h1>
        </div>
    </div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
