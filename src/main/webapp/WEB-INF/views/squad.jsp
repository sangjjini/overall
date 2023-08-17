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
    <link type="text/css" href="/css/squad.css" rel="stylesheet" />
    <%-- 제이쿼리--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link rel="shortcut icon" href="/images/favicon.ico">
</head>
<body>
<c:import url="header.jsp" />
    <div class="page_title">
        <h1>스쿼드 메이커</h1>
        <p>스쿼드를 만들어보세요.</p>
    </div>
    <div class="contents">
        <div class="contents_top">
            <div class="contents_left">
                <div class="contents_list">
                    <label for="name" class="small_title">스쿼드 이름</label>
                    <input type="text" id="name" class="input_area">
                </div>
                <div class="contents_list">
                    <label for="contents" class="small_title">스쿼드 소개</label>
                    <textarea id="contents" class="input_area"></textarea>
                </div>
                <div class="contents_list">
                    <input type="hidden" id="host">
                    <button class="squad_edit" onclick="update()">정보 변경</button>
                </div>
                <div class="contents_list">
                    <div class="squad_member">
                        <div class="small_title">스쿼드 멤버</div>
                        <button onclick="show_invite()" class="invited_btn">초대하기</button>
                    </div>
                    <div id="invite_list">
                        <div class="invite_area">
                            <div>
                                <button onclick="close_invite()" class="cancel_btn">X</button>
                            </div>
                            <div class="invite_title">
                                <label for="email" class="small_title">초대할 이메일</label>
                            </div>
                            <div class="email_area">
                                <input type="text" id="email">
                                <button onclick="invite()" class="small_btn">초대</button>
                            </div>
                            <div id="inviting"></div>
                        </div>
                    </div>
                </div>
                <div class="contents_member">
                    <div class="list_pos small_title">포지션</div>
                    <div class="list_name small_title">이름</div>
                    <div class="list_out small_title">방출</div>
                </div>
                <div id="invited"></div>
                <div class="contents_list">
                    <button onclick="leave()" class="squad_edit delete_btn">스쿼드 탈퇴</button>
                </div>
            </div>

            <div class="center_line"></div>

            <div class="right_area">
                <div class="formation">포메이션</div>
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
