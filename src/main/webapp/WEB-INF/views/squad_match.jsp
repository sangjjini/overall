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
        <p></p>
        <input type="hidden" id="log" value="${log}">
<%--        <input type="hidden" id="log" value="neymar@gmail.com">--%>
<%--        <input type="hidden" id="log" value="kevin@gmail.com">--%>
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
                    <label for="squadA" class="small_title">A팀</label>
                    <input type="text" id="squadA" class="input_area" readonly>
                    <label for="squadB" class="small_title">B팀</label>
                    <input type="text" id="squadB" class="input_area" readonly>
                </div>
                <div class="contents_list date">
                    <label for="time" class="small_title">시간</label>
                    <input type="text" id="time" class="input_area" readonly>
                </div>
                <div class="contents_list">
                    <input type="hidden" id="host">
                    <button class="squad_edit" id="update_btn" onclick="updateMatch()"></button>
                </div>
                <div class="contents_list">
                    <button class="squad_edit" id="delete_btn" onclick="deleteMatch()">매치 삭제</button>
                </div>
                <div class="contents_list"></div>
            </div>

            <div class="center_line"></div>

            <div class="contents_right">
                <div class="preview">

                </div>
            </div>
        </div>

    </div>
<c:import url="footer.jsp"/>
</body>
<script src="/script/match.js"></script>
</html>
