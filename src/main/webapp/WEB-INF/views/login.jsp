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
    <title>로그인</title>
</head>
<body>
    <div id="wrap">
        <div id="root">
            <jsp:include page="header.jsp"></jsp:include>
            <section id="main_section">
                <div class="login_container">
                    <div class="logo">
                        <a href="/index"><img src="#"></a>
                    </div>
                    <form id="lofin">
                        <div class="id_txt">아이디</div>
                        <div class="login_box">
                            <ion-icon name="person-outline"></ion-icon>
                            <input type="text" name="id" id="id" maxlength="30"
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
                                <form:checkbox path="rememberId"/>아이디 기억
                            </label>
                        </div>
                        <input type="button" id="submit_button" value="login"
                               onclick="loginChk()">
                        <div class="login_signup">
                            <a href="finduser" class="forget_password">아이디 / 비밀번호 찾기</a>
                            <a href="/join">회원가입</a>
                        </div>
                    </form>
                </div>
            </section>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </div>
</body>
</html>
