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
</div>
<div class="contents_filter">
    <div class="filter">
        <div class="filter_input">
            <input type="text" id="search" placeholder="검색">
            <button id="filter_btn" onclick="search()">검색</button>
        </div>
        <div class="filter_option" onchange="all_match()">
            <div>
                <input type="radio" name="filter_sel" id="date_asc" value="date_asc" checked>
                <label for="date_asc" class="option_text">시간순</label>

                <input type="radio" name="filter_sel" id="title_asc" value="title_asc">
                <label for="title_asc" class="option_text">제목순</label>

                <input type="radio" name="filter_sel" id="overall_asc" value="overall_asc">
                <label for="overall_asc" class="option_text">OVR 낮은순</label>

                <input type="radio" name="filter_sel" id="overall_desc" value="overall_desc">
                <label for="overall_desc" class="option_text">OVR 높은순</label>
            </div>
        </div>
    </div>
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
                <input class="error_startAt" readonly>
                <input type="datetime-local" id="endAt">
                <input class="error_endAt" readonly>
                <div class="sub_section">
                    <input type="hidden" id="author">
                    <button onclick="matchMake()" class="enter_btn make_sub">만들기</button>
                    <button onclick="close_make()" class="cancel make_sub">취소</button>
                </div>
            </div>
            <div class="make_list">
                <div class="list_title">
                    <h1>만든 매치</h1>
                </div>
                <div id="my_match"></div>
            </div>
            <div class="apply_list">
                <div class="list_title">
                    <h1>참가 매치</h1>
                </div>
                <div id="apply_list"></div>
            </div>
        </div>
    </div>
    <div class="contents_right">
<%--        <div class="bar">--%>
<%--            <select id="sorts" onchange="all_match()">--%>
<%--                <option value="" selected>정렬 선택</option>--%>
<%--                <option value="title_desc">제목 내림차순</option>--%>
<%--                <option value="title_asc">제목 오름차순</option>--%>
<%--                <option value="date_desc">시간 내림차순</option>--%>
<%--                <option value="date_asc">시간 오름차순</option>--%>
<%--            </select>--%>

<%--        </div>--%>
<%--        <input type="hidden" id="sort" value="">--%>
        <div class="bar">
            <div class="bar_date">일정</div>
            <div class="bar_squad">팀명(평균OVR)</div>
            <div class="bar_title">제목</div>
            <div class="bar_content">소개</div>
            <div class="bar_join">참가 신청</div>
        </div>
        <div id="lines"></div>
    </div>
    <div id="applyContainer" style="display: none">
        <div id="applyContent">
            <div id="close_btn">
                <button class="cancel_btn" id="modalCloseButton" onclick="modalClose()">X</button>
            </div>
            <div id="squad_container">
                <div id="squad_select_container">
                    <select name="squads" id="squadB" size="1">

                    </select>
                </div>
                <div class="squad_partIn_btn_container">
                    <input type="hidden" id="no_temp" value="">
                    <button id="partIn_btn" onclick="partInMatch()">참가</button>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
</body>
<script src="/script/match_list.js"></script>
</html>
