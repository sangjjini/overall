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
            <div class="nav_list"><a href="/squad/list">스쿼드</a></div>
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
                <div class="alarm_area">
                    <button class="alarm" onclick="show_alarm()">
                        <div>
                            <img src="/images/alarm.png">
                            <input type="text" id="alarm_cnt" class="alarm_num" readonly>
                        </div>
                    </button>
                    <div id="alarm_popup">
                        <button onclick="close_alarm()" class="cancel_btn">X</button>
                        <div class="invite_title">
                            새로운 알람 :
                            <input type="text" class="alarm_num" id="popup_cnt" readonly>
                        </div>
                        <div id="alarm_area"></div>
                    </div>
                </div>
                <div class="mypage_wrap">
                    <div class="mypage"><a href="/mypage">마이페이지</a></div>
                </div>
                <div class="logout_wrap">
<%--                  <div class="logout"><a href="javascript:logout();" onclick="return false;">로그아웃</a></div>--%>
                   <div class="logout"><a href="javascript:void(0);" onclick="logout()">로그아웃</a></div>
                </div>
        </c:otherwise>
    </c:choose>
    </div>
</header>
</body>
