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
    <link rel="stylesheet" href="/css/reset.css" type="text/css">
    <link rel="stylesheet" href="/css/match_list.css" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css"/>
    <link rel="shortcut icon" href="/images/favicon.ico">
    <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
    <div class="page_title">
        <h1>매치 리스트</h1>
        <p>OVERALL에서 자신의 스쿼드로 풋살매치를 즐기세요</p>
        <input type="hidden" id="log" value="${log}">
<%--        <input type="hidden" id="log" value="neymar@gmail.com">--%>
    </div>
    <div class="contents">
        <div class="contents_left">
            <div class="left_menu">
                <button onclick="show_make()" class="make_btn">매치 만들기</button>
                <div id="show_make">
                    <input type="text" id="match_title" placeholder="매치 이름">
                    <input class="error_title" readonly>
                    <select name="squads" id="squadA" size="1">

                    </select>
                    <input class="error_squadA" readonly>
                    <input type="datetime-local" id="startAt">
                    <input type="datetime-local" id="endAt">
                    <input class="error_squadA" readonly>
                    <div class="sub_section">
                        <input type="hidden" id="author">
                        <button onclick="matchMake()" class="enter_btn make_sub">만들기</button>
                        <button onclick="close_make()" class="cancel_btn make_sub">취소</button>
                    </div>
                </div>
                <div id="my_match"></div>
            </div>
        </div>
        <div class="contents_right">
            <div class="bar">
                <div class="bar_date">일정</div>
                <div class="bar_title">제목</div>
                <div class="bar_content">소개</div>
                <div class="bar_join">참가 신청</div>
            </div>
            <div id="lines"></div>
        </div>
<%--        <div id="applyContainer" class="hidden">--%>
<%--            <div id="applyContent">--%>
<%--                <div id="squad_container">--%>
<%--                    <select name="squads" id="squadB" size="1">--%>

<%--                    </select>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <button id="apply_btn" onclick="apply()">참가신청</button>--%>
<%--                    <button id="modalCloseButton" onclick="modalClose()">닫기</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
    </div>
<c:import url="footer.jsp"/>
</body>
<script src="/script/match_list.js"></script>
</html>
