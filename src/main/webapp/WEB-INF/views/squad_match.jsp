<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2023-08-17
  Time: 오후 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link rel="shortcut icon" href="/images/favicon.ico">
    <link rel="stylesheet" href="/css/reset.css" type="text/css">
    <link rel="stylesheet" href="/css/match.css" type="text/css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="page_title">
    <h1>매치 생성</h1>
    <input type="hidden" id="log" value="${log}">
</div>
<div class="contents">
    <div class="content">
        <div class="contents_left">
            <div class="contents_list">
                <label for="title" class="small_title">방제</label>
                <input type="text" id="title" class="input_area">
                <input type="text" class="error_title" readonly>
            </div>
            <div class="contents_list">
                <label for="contents" class="small_title">매치 설명</label>
                <textarea id="contents" name="contents" spellcheck="false"></textarea>
            </div>
            <div class="contents_list">
                <label for="author" class="small_title">방장</label>
                <input type="text" id="author" class="input_area" readonly>
            </div>
            <div class="contents_list squad">
                <div class="div_team">
                <label for="squadA" class="small_title">A팀</label>
                <input type="text" id="squadA" class="input_area" readonly>
                </div>
                <div class="div_team">
                <label for="squadB" class="small_title">B팀</label>
                <input type="text" id="squadB" class="input_area" readonly>
                </div>
            </div>
            <div class="contents_list date">
                <span class="small_title">시간: </span>
                <span id="time"></span>
<%--                <label for="time" class="small_title">시간</label>--%>
<%--                <input type="text" id="time" class="input_area" readonly>--%>
            </div>
            <div class="contents_list">
                <input type="hidden" id="host">
                <button class="squad_edit" id="update_btn" onclick="updateMatch()"></button>
            </div>
            <div class="contents_list">
                <button class="squad_edit" id="leave_btn" onclick="leaveMatch()">매치 퇴장</button>
            </div>
            <div class="contents_list">
                <button class="squad_edit" id="a_win" value="A" onclick="resultMatch(this)">A팀 승리</button>
                <button class="squad_edit" id="draw" value="D" onclick="resultMatch(this)">무승부</button>
                <button class="squad_edit" id="b_win" value="B" onclick="resultMatch(this)">B팀 승리</button>
            </div>
        </div>
        <div class="center_line"></div>
        <div class="contents_right"></div>
        <div id="applyContainer" style="display: none">
            <div id="applyContent">
                <div id="close_btn">
                    <button class="cancel_btn" id="modalCloseButton" onclick="modalClose()">X</button>
                </div>
                <div id="squad_container">
                    <div id="squad_select_container">
                        <select name="squads" id="squads" size="1">

                        </select>
                    </div>
                    <div class="squad_partIn_btn_container">
                        <input type="hidden" id="no_temp" value="">
                        <button id="partIn_btn" onclick="partInMatch()">참가신청</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
</body>
<script src="/script/match.js"></script>
</html>