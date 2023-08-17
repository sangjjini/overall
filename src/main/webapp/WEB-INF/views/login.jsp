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
    <link rel="stylesheet" href="/css/login.css">
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
                    <form id="login" method="post" action="/login">
                        <div class="id_txt">이메일</div>
                        <div class="login-box">
                            <ion-icon name="person-outline"></ion-icon>
                            <input type="text" name="email" id="email" maxlength="30"
                                   autocapitalize="off">
                        </div>
                        <div class="pw_txt">비밀번호</div>
                        <div class="login-box">
                            <ion-icon name="lock-closed-outline"></ion-icon>
                            <input type="password" name="password" id="password"
                                   maxlength="30" autocapitalize="off">
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="rememberId"/>아이디 저장
                            </label>
                        </div>
                        <input type="button" id="submit_button" value="login"
                               onclick="loginChk()">
                        <div class="login_signup">
                            <a href="/finduser" class="forget_password">아이디 / 비밀번호 찾기</a>
                            <a href="/join">회원가입</a>
                        </div>
                    </form>
                </div>
            </section>
            <script type="module"
                    src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
            <script nomodule
                    src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        </div>
    </div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
