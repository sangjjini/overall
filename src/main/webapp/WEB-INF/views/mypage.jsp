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
<script src="/script/mypage.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="wrap">
<div class="contents">
<div class="contents_menu">
    <div class="menu"><a href="#"><h1>오버롤</h1></a></div>
    <div class="menu"><a href="#"><h1>프로필 수정</h1></a></div>
    <div class="menu"><a href="#"><h1>스타일 설정</h1></a></div>
    <div class="menu"><a href="#"><h1>고객센터</h1></a></div>

</div>
<div class="mypage_contents">
    <div class="overall">
        <div class="overall_left">
        <div class="nickname_wrap">
            <div class="nickname">ronaldo</div>
        </div>
        <div class="overall_param">
        </div>
        <div class="position">
                CF
        </div>
        </div>
        <div class="stats_wrap">

        </div>
        <div class="style_wrap">

        </div>
    </div>
    <div class="match_history">
        <div class="history_contents">
            <div class="team1">레알마드리드</div>
            <h2>2</h2>
            <span>vs</span>
            <h2>1</h2>
            <div class="team2">알힐랄</div>
        </div>
    </div>
</div>

</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
