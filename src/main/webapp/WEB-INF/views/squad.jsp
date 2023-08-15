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
    <style>
        #invite_list{
            display: none;
            position: absolute;
            background-color: aqua;
        }
        #new_squad{
            display: none;
            position: absolute;
            background-color: aqua;
        }
    </style>
</head>
<body>
<c:import url="header.jsp" />
    <div class="contents" style="background-color: aquamarine">
        <div>
            <label for="name">스쿼드 이름</label>
            <input type="text" id="name" readonly>
            <label for="contents">스쿼드 소개</label>
            <input type="text" id="contents" readonly>
            <input type="hidden" id="host">
        </div>
        <br>

        <div>
            <h5>스쿼드 멤버</h5>
            <div id="invited"></div>
        </div>
        <br>

        <div>
            <button onclick="show_invite()">초대 목록</button>
            <div id="invite_list">
                <div>
                    <label for="email">초대할 이메일</label>
                    <input type="text" id="email">
                    <button onclick="invite()">초대</button>
                </div>
                <br>

                <div id="inviting"></div>
            </div>
        </div>
        <br>

        <button onclick="show_edit()">정보 변경</button>
        <div id="new_squad">
            <label for="new_name">변경 이름</label>
            <input type="text" id="new_name">
            <label for="new_contents">변경 소개</label>
            <input type="text" id="new_contents">
            <button onclick="update()">변경하기</button>
        </div>
        <button onclick="leave()">스쿼드 탈퇴</button>
    </div>
<c:import url="footer.jsp" />
</body>
<script src="/script/squad.js"></script>
</html>
