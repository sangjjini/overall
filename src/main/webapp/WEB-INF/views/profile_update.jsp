<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-09
  Time: 오전 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>프로필 수정</title>
    <%-- 제이쿼리--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link type="text/css" href="/css/reset.css" rel="stylesheet" />
    <link type="text/css" href="/css/profile_update.css" rel="stylesheet" />
    <link rel="shortcut icon" href="/images/favicon.ico">
    <script src="/script/profile_update.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="wrap">
    <div class="contents">
        <div class="contents_menu">
            <div class="menu"><a href="mypage"><h1>오버롤</h1></a></div>
            <div class="menu on"><a href="/profileUpdate"><h1>프로필 수정</h1></a></div>
            <div class="menu"><a href="/overallUpdate"><h1>오버롤 설정</h1></a></div>

        </div>
        <div class="profile_contents">
            <div class="profile_update">
                <h1>프로필 수정</h1>
                <div class="update_contents_wrap">
                    <div class="update_contents">
                        <div class="txt">변경하실 비밀번호를 입력해주세요</div>
                        <input type="password" id="newPassword">
                        <div class="button"><a href="/profileUpdate" onclick="changePasswordBtn()">비밀번호 변경</a></div>
                    </div>
                    <small class="small_hint" id="hint_newPassword">이메일이 인증되지 않았습니다</small>
                    <div class="update_contents">
                        <div class="txt">변경하실 닉네임을 입력해주세요</div>
                        <input type="text">
                        <div class="button"><a href="#">닉네임 변경</a></div>
                    </div>
                    <small class="small_hint" id="hint_newNickname">이메일이 인증되지 않았습니다</small>
                </div>
            </div>
        </div>

    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
