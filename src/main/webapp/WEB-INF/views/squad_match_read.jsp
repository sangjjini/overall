<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-09
  Time: 오전 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>스쿼드 매치</title>
    <%-- 제이쿼리 --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/reset.css" type="text/css">
    <link rel="stylesheet" href="/css/match_read.css" type="text/css">
    <link rel="shortcut icon" href="/images/favicon.ico">
</head>
<body>
<c:import url="header.jsp"/>
        <div class="contents">
            <div class="container">
                <div class="view_back">
                    <a href="/squad/matchList" class="btn_back_list">
                        <em class="tm">
                            <img src="/images/back_icon.png" alt="" class="icon_return">
                        </em>
                        <span class="text">목록으로 돌아가기</span>
                    </a>
                </div>
                <div class="sub_container">
                    <div>
                        <h1 id="title"></h1>
                    </div>
                    <div class="match-preview">
<%--                        <input type="hidden" id="log_temp" name="lot_temp" value="${log}">--%>
<%--                        <input type="hidden" id="log_temp" name="log_temp" value="kevin@gmail.com">--%>
<%--                        <input type="hidden" id="log_temp" name="log_temp" value="neymar@gmail.com">--%>
                        <input type="hidden" id="log_temp" name="log_temp" value="SIUUU@naver.com">
                        <div class="match_info">
                            <section>
                                <ul>
                                    <li id="schedule">
                                        <span></span>
                                    </li>
                                   <li id="author">
                                        <span></span>
                                    </li>
                                    <li id="deadline">
                                        <span></span>
                                    </li>
                                </ul>
                            </section>
                        </div>
                        <div class="logo_container">
                            <div>
                                <div id="squadA_logo">
                                    <img src="" alt="">
                                </div>
                                <div id="squadA_name">
                                    <h1></h1>
                                </div>
                            </div>
                            <div class="vs">
                                <h1>vs</h1>
                            </div>
                            <div>
                                <div id="squadB_logo">
                                    <img src="" alt="">
                                </div>
                                <div id="squadB_name">
                                    <h1></h1>
                                </div>
                            </div>
                        </div>
                        <div class="match_btn_container">
                            <input type="button" id="match_btn" name="match_btn" value="">
                        </div>
                        <div id="applyContainer" class="hidden">
                            <div id="applyContent">
                                <div id="squad_container">
                                    <select name="squads" id="squads" size="1">

                                    </select>
                                </div>
                                <div>
                                    <button id="apply_btn" onclick="applyMatch()">참가신청</button>
                                    <button id="modalCloseButton">닫기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<script src="/script/match_read.js"></script>
<c:import url="footer.jsp"/>
</body>
</html>