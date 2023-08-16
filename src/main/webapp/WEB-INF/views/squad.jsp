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
        #chat{
            width: 500px;
            height: 300px;
            background-color: antiquewhite;
            overflow: auto;
        }
        .myChat{
            display: flex;
            flex-direction: column;
            align-items: flex-end;
        }
        .otherChat{
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }
    </style>
</head>
<body>
<c:import url="header.jsp" />
    <div class="contents" style="background-color: aquamarine">
        <div>
            <label for="name">스쿼드 이름</label>
            <input type="text" id="name">
            <label for="contents">스쿼드 소개</label>
            <input type="text" id="contents">
            <input type="hidden" id="host">
            <button onclick="update()">정보 변경</button>
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

        <div id="chat"></div>
        <div>
            <input type="text" id="chatting" placeholder="내용">
            <button onclick="send()">전송</button>
        </div>
        <br>

        <button onclick="leave()">스쿼드 탈퇴</button>
        <br>
    </div>
<c:import url="footer.jsp" />
</body>
<script src="/script/squad.js"></script>
</html>
