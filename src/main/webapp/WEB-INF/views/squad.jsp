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
    <link type="text/css" href="/css/squad.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>
<c:import url="header.jsp" />
    <div class="contents">
        <div class="contents_top">
            <div class="contents_left">
                <div class="contents_list">
                    <label for="name" class="small_title">스쿼드 이름</label>
                    <input type="text" id="name">
                </div>
                <div class="contents_list">
                    <label for="contents" class="small_title">스쿼드 소개</label>
                    <textarea id="contents"></textarea>
                </div>
                <div class="contents_list">
                    <input type="hidden" id="host">
                    <button class="title_btn" onclick="update()">정보 변경</button>
                </div>
                <div class="contents_list">
                    <div class="small_title_btn">
                        <div class="small_title">스쿼드 멤버</div>
                        <button onclick="show_invite()">초대 목록</button>
                    </div>
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
                <div class="contents_member">
                    <div class="list_pos small_title">포지션</div>
                    <div class="list_name small_title">이름</div>
                    <div class="small_title">방출</div>
                </div>
                <div id="invited"></div>
                <div class="contents_list">
                    <button onclick="leave()">스쿼드 탈퇴</button>
                </div>
            </div>

            <div class="contents_right">
                <div id="select_box">
                    <button onclick="close_select()">X</button>
                    <div id="member_list"></div>
                </div>
                <div class="position_area">
                    <button class="position_add" id="A">+</button>
                    <div id="sel_A"></div>
                </div>
                <div class="position_row">
                    <div class="position_area">
                        <button class="position_add" id="B">+</button>
                        <div id="sel_B"></div>
                    </div>
                    <div class="position_area">
                        <button class="position_add" id="C">+</button>
                        <div id="sel_C"></div>
                    </div>
                </div>
                <div class="position_area">
                    <button class="position_add" id="D">+</button>
                    <div id="sel_D"></div>
                </div>
                <div class="position_area">
                    <button class="position_add" id="E">+</button>
                    <div id="sel_E"></div>
                </div>
            </div>
        </div>

        <div class="contents_bottom">
            <div id="chat"></div>
            <div>
                <input type="text" id="chatting" placeholder="내용">
                <button onclick="send()">전송</button>
            </div>
        </div>
    </div>
<c:import url="footer.jsp" />
</body>
<script src="/script/squad.js"></script>
</html>
