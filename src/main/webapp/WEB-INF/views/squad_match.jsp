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
    <div class="contents">
        <div class="content">
            <div class="contents_left">
                <div class="contents_list">
                    <label for="title" class="small_title">방제</label>
                    <input type="text" id="title" class="input_area">
                </div>
                <div class="contents_list">
                    <label for="author" class="small_title">방장</label>
                    <input type="text" id="author" class="input_area">
                </div>
                <div class="contents_list">
                    <label for="contents" class="small_title">매치 설명</label>
                    <textarea id="contents" name="contents"></textarea>
                </div>
                <div class="contents_list squad">
                    <label for="squadA" class="small_title">A팀</label>
                    <input type="text" id="squadA" class="input_area">
                    <label for="squadB" class="small_title">B팀</label>
                    <input type="text" id="squadB" class="input_area">
                </div>
                <div class="contents_list date">
                    <label for="startAt" class="small_title">시작</label>
                    <input type="datetime-local" id="startAt" class="input_area">
                    <label for="endAt" class="small_title">종료</label>
                    <input type="datetime-local" id="endAt" class="input_area">
                </div>
                <div class="contents_list"></div>
            </div>

            <div class="center_line"></div>

            <div class="contents_right">

            </div>
        </div>

    </div>
<c:import url="footer.jsp"/>
</body>
</html>
