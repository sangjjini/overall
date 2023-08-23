<%--
Created by IntelliJ IDEA.
User: Administrator
Date: 2023-08-09
Time: 오전 10:13
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>마이 페이지</title>
<%-- 제이쿼리--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link type="text/css" href="/css/reset.css" rel="stylesheet" />
<link type="text/css" href="/css/mypage.css" rel="stylesheet" />
    <link rel="shortcut icon" href="/images/favicon.ico">
<script src="/script/mypage.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<input type="hidden" id="homin" value="${log}">
<div class="wrap">
<div class="contents">
<div class="contents_menu">
    <div class="menu on"><a href="/mypage"><h1>오버롤</h1></a></div>
<%--    <div class="menu"><a href="/profileUpdate"><h1>프로필 수정</h1></a></div>--%>
    <div class="menu"><a href="/overallUpdate"><h1>오버롤 설정</h1></a></div>
</div>
<div class="mypage_contents">
    <div class="overall">
        <div class="overall_top">
        <div class="style_wrap">
        </div>
        </div>
        <div class="overall_bottom">
        <div class="overall_left">
        <div class="nickname_wrap">
            <div class="nickname"></div>
        </div>
        <div class="overall_param">
        </div>
        <div class="position">
        </div>
        </div>
        <div class="stats_wrap">
        </div>
        <div class="foot_wrap">
            <div class="left_foot"><h3></h3></div>
            <div class="right_foot"><h3></h3></div>
        </div>

        </div>

    </div>

</div>

</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
