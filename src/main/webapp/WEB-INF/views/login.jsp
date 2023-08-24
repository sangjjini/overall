<%--
  Created by IntelliJ IDEA.
  User: 상진
  Date: 2023-08-10
  Time: 오전 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link type="text/css" href="/css/reset.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/login.css">
    <link rel="shortcut icon" href="/images/favicon.ico">
    <script src="/script/login.js"></script>
    <title>로그인</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div id="wrap">
    <div id="root">
        <section id="main_section">
            <div class="login_container">
                <div class="logo">
                    <a href="/"><img src="/images/logo_black.png"></a>
                </div>
                <form id="login">
                    <div class="id_txt">이메일</div>
                    <div class="login-box">
                        <input type="text" name="email" id="email" maxlength="30"
                               autocapitalize="off">
                    </div>
                    <small class="small_hint" id="hint_email">이메일을 입력해 주세요</small>

                    <div class="pw_txt">비밀번호</div>
                    <div class="login-box">
                        <input type="password" name="password" id="password"
                               maxlength="30" autocapitalize="off">
                    </div>
                    <small class="small_hint" id="hint_password">비밀번호를 입력해 주세요</small>

                    <input type="button" id="submit_button" value="로그인"
                           onclick="loginChk()">
                    <div class="login_signup">
                        <h1>회원으로 이용을 하고싶으시다면 ?</h1>
                        <a href="/join">회원가입</a>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
