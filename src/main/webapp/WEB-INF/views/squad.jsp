<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-09
  Time: 오전 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>스쿼드</title>
    <link type="text/css" href="/css/squad.css" rel="stylesheet" />
    <%-- 제이쿼리--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link rel="shortcut icon" href="/images/favicon.ico">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
</head>
<body>
<c:import url="header.jsp" />
    <input id="host" style="display: none">
    <input id="log" value="${sessionScope.log}" style="display: none">
    <div class="shadow"></div>
    <div class="page_title">
        <h1>스쿼드 메이커</h1>
        <p>함께할 팀을 만들어보세요.</p>
    </div>
    <div class="contents">
        <div class="contents_top">
            <div id="invite_list">
                <div class="popup_area">
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
                    <input type="text" class="error" id="error_invite" readonly>
                    <div id="inviting" class="list_area"></div>
                </div>
            </div>
            <div class="contents_left">
                <div class="contents_list">
                    <label for="name" class="small_title">스쿼드 이름</label>
                    <input type="text" id="name" class="input_area">
                    <input type="text" class="error" id="error_name" readonly>
                </div>
                <div class="contents_list">
                    <label for="contents" class="small_title">스쿼드 소개</label>
                    <textarea id="contents" class="input_area" spellcheck="false"></textarea>
                </div>
                <div class="contents_list" id="edit_btn"></div>
                <div class="contents_list">
                    <div class="squad_member">
                        <div class="small_title">스쿼드 멤버</div>
                        <button onclick="show_invite()" class="invited_btn">
                            초대/신청
                            <input type="text" id="invite_cnt" readonly>
                        </button>
                    </div>
                </div>
                <div class="contents_member">
                    <div class="list_pos small_title"><h1>포지션</h1></div>
                    <div class="list_over small_title"><h1>OVR</h1></div>
                    <div class="list_name small_title">
                        <div class="name">이름</div>
                        <div class="out_btn_wrap"></div>
                    </div>

                </div>
                <div id="invited"></div>
                <div class="contents_list">
                    <button onclick="leave()" class="squad_edit delete_btn">스쿼드 탈퇴</button>
                </div>
            </div>

            <div class="center_line"></div>

            <div class="right_area">
                <div class="formation_top">
                    <div class="formation">포메이션 1-2-1</div>
                    <div class="formation_ovr">
                        <div class="formation_txt">평균 OVR : </div>
                        <input type="text" id="ovr" readonly>
                    </div>
                </div>
                <div class="contents_right">
                    <div id="select_box">
                        <div class="popup_area">
                            <div>
                                <button onclick="close_select()" class="cancel_btn">X</button>
                            </div>
                            <div class="invite_title small_title">스쿼드 선수</div>
                            <div id="member_list" class="list_area"></div>
                        </div>
                    </div>
                    <div class="position_area">
                        <div class="area_img" id="area_A">
                            <div class="pos_name small_title">PIVO</div>
                            <button class="position_add" id="A">+</button>
                            <div id="sel_A" class="sel_area"></div>
                        </div>
                    </div>
                    <div class="position_row">
                        <div class="position_area">
                            <div class="area_img" id="area_B">
                                <div class="pos_name small_title">ALA</div>
                                <button class="position_add" id="B">+</button>
                                <div id="sel_B" class="sel_area"></div>
                            </div>
                        </div>
                        <div class="position_area">
                            <div class="area_img" id="area_C">
                                <div class="pos_name small_title">ALA</div>
                                <button class="position_add" id="C">+</button>
                                <div id="sel_C" class="sel_area"></div>
                            </div>
                        </div>
                    </div>
                    <div class="position_area">
                        <div class="area_img" id="area_D">
                            <div class="pos_name small_title">FIXO</div>
                            <button class="position_add" id="D">+</button>
                            <div id="sel_D" class="sel_area"></div>
                        </div>
                    </div>
                    <div class="position_area">
                        <div class="area_img" id="area_E">
                            <div class="pos_name small_title">GOLEIRO</div>
                            <button class="position_add" id="E">+</button>
                            <div id="sel_E" class="sel_area"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="contents_bottom">
            <div class="chat_title">스쿼드 댓글</div>
            <div class="send_area">
                <input type="text" id="chatting" placeholder="내용을 입력하세요">
                <button onclick="send()" class="send_btn">작성</button>
            </div>
            <div class="chat_area">
                <div id="chat"></div>

            </div>
        </div>
    </div>
<c:import url="footer.jsp" />
</body>
<script src="/script/squad.js"></script>
</html>
