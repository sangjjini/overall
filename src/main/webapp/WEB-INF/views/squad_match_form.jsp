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
    <title>스쿼드 매치 생성</title>
    <%-- 제이쿼리 --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/reset.css" type="text/css">
    <link rel="stylesheet" href="/css/squad_match.css" type="text/css">
    <script src="/script/squad_match.js"></script>
    <script src="/script/mysquad_select.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
    <section class="wrap">
        <div class="contents">
            <form method="post" action="/squad/match/making">
                <input type="text" id="title" name="title" placeholder="제목을 입력해주세요.">
                <%--                <input type="text" id="author" name="author" value="작성자:${log}" readonly>--%>
<%--                <input type="text" id="author" name="author" value="작성자:kevin@gmail.com" readonly>--%>
                <input type="text" id="author" name="author" value="작성자:neymar@gmail.com" readonly>
                <input type="datetime-local" id="startAt" name="startAt">
                <input type="datetime-local" id="endAt" name="endAt">
                <div class="squad-input">
                    <select name="squads" id="squads" size="1">

                    </select>
<%--                    <input type="text" id="squadA" name="squadB" placeholder="자신이 속한 팀명 입력">--%>
                    <input type="text" id="squadB" name="squadB" placeholder="상대 팀명 입력">
                </div>
                <textarea id="contents" name="contents" placeholder="내용을 입력해주세요."></textarea>
                <%--                <input type="hidden" id="deadline" name="deadline" value="0">--%>
                <input type="button" id="submit-btn" name="submit-btn" value="등록" onclick="checkValue()">
            </form>
        </div>
    </section>
<c:import url="footer.jsp"/>
</body>
</html>