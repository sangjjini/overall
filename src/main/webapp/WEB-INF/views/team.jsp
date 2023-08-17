<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-17
  Time: 오전 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>팀원 소개</title>
    <%-- 제이쿼리--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link type="text/css" href="/css/team.css" rel="stylesheet" />
    <link rel="shortcut icon" href="/images/favicon.ico">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="contents">
    <div class="title">
        <h1>
            팀원소개
        </h1>
        <div class="tab">
            <div class="tab1"><a href="introduce">오버롤소개</a></div>
            <div class="tab2"><a href="team">팀원소개</a></div>
        </div>
    </div>
    <div class="main_contents">
        <div class="main_profile" id="sangjin">
            <img src="/images/sangjin.jpg">
            <div class="txt_box">
                <h1>이상진</h1>
                <h2>github:http://github.com/sangjjini</h2>
                <h2>email:sangjini9810@gmail.com</h2>
            </div>

        </div>
        <div class="main_profile" id="gyutae">
            <div class="txt_box">
                <h1>박규태</h1>
                <h2>github:http://github.com/poweiruty</h2>
                <h2>email:rbxo0032@gmail.com</h2>
            </div>
            <img src="/images/gyutae.jpg">
        </div>
        <div class="main_profile" id="homin">
            <img src="/images/homin.jpg">
            <div class="txt_box">
                <h1>김호민</h1>
                <h2>github:http://github.com/coffeepudding97</h2>
                <h2>email:hominin5797@gmail.com</h2>
            </div>
        </div>
        <div class="main_profile" id="seungmin">
            <div class="txt_box">
                <h1>백승민</h1>
                <h2>github:qortmdals120@gmail.com</h2>
                <h2>email:http://github.com/alstmd7</h2>
            </div>
            <img src="/images/seungmin.jpg">
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
