<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-09
  Time: 오전 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>스쿼드 리스트</title>
    <link type="text/css" href="/css/squad_list.css" rel="stylesheet" />
    <%-- 제이쿼리--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link rel="shortcut icon" href="/images/favicon.ico">
</head>
<body>
<c:import url="header.jsp"/>
    <div class="page_title">
        <h1>스쿼드 리스트</h1>
        <p>OVERALL에서 다양한 스쿼드에 참여하거나 직접 만들어보세요</p>
    </div>
    <div class="contents">
        <div class="contents_left">
            <div class="left_menu">
                <button onclick="show_make()" class="make_btn">스쿼드 만들기</button>
                <div id="show_make">
                    <input type="text" id="squad_name" placeholder="스쿼드 이름">
                    <input class="error_name" readonly>
                    <div class="sub_section">
                        <button onclick="squadMake()" class="enter_btn make_sub">만들기</button>
                        <button onclick="close_make()" class="cancel_btn make_sub">취소</button>
                    </div>
                </div>
                <div id="my_squad"></div>
            </div>
        </div>
        <div class="contents_right">
            <div class="bar">
                <div class="bar_num">번호</div>
                <div class="bar_over">OVERALL</div>
                <div class="bar_name">이름</div>
                <div class="bar_content">소개</div>
                <div class="bar_join">가입 신청</div>
            </div>
            <div id="squad_list"></div>
        </div>
    </div>
<c:import url="footer.jsp"/>
</body>
<script src="/script/squad_list.js"></script>
</html>

