<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-09
  Time: 오전 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>스쿼드</title>
    <%-- 제이쿼리--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>
<c:import url="header.jsp" />
    <div class="contents" style="background-color: aquamarine">
        <div>
            <label for="name">스쿼드 이름</label>
            <input type="text" id="name" readonly>
            <label for="contents">스쿼드 소개</label>
            <input type="text" id="contents" readonly>
        </div>
        <br />

        <div>
            <label for="email">초대할 이메일</label>
            <input type="text" id="email">
            <button onclick="invite()">초대</button>
        </div>
        <br />

        <div>
            <h6>스쿼드 멤버</h6>
            <div id="invited"></div>
        </div>
        <br />

        <div>
            <button onclick="inviting()">초대 목록</button>
            <div id="inviting"></div>
        </div>
        <br />

        <button onclick="leave()">스쿼드 탈퇴</button>
    </div>
<c:import url="footer.jsp" />
</body>
<script src="/script/squad.js"></script>
</html>
