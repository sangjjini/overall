<%--
  Created by IntelliJ IDEA.
  User: 상진
  Date: 2023-08-10
  Time: 오전 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div id="wrap">
    <section id="main-section">
        <div class="sign_container">
            <div class="logo"><a href="/"><img src="../resources/static/images/logo_black.png"></a></div>
            <form id="form" class="sign">
                <div class="input-control">
                    <div class="input-control">
                        <label class="label" for="email">E-mail</label>
                        <div class="input_and_button">
                            <input id="email" name="email" type="email" placeholder="예)email2023@domain.com" maxlength="40">
                            <button id="send_btn" type="button" value="send_code" class="send_code" onclick="sendCode()">인증번호 발송
                            </button>
                        </div>
                        <small class="small hint" id="hint_email">이메일이 인증되지 않았습니다.</small>
                    </div>
                    <div id="div_code" class="input-control">
                        <label class="label" for="verify_btn">인증번호 입력</label>
                        <input id="code" name="code" type="number" maxlength="6">
                        <button id="verify_btn" type="button" value="verify" class="verify" onclick="verify()">인증하기</button>
                    </div>
                    <div class="input-control">
                        <label class="label" for="password">비밀번호</label>
                        <input id="password" name="password" type="password" maxlength="40"
                               placeholder="최소 8자리 이상 영문, 숫자, 특수문자 최소 1글자 포함">
                        <small class="small hint" id="hint_password">최소 8자리 이상 영문, 숫자, 특수문자 최소 1글자 포함해 입력해야 합니다.</small>
                    </div>
                    <div class="input-control">
                        <label class="label" for="passwordChk">비밀번호 확인</label>
                        <input id="passwordChk" name="passwordChk" type="password" maxlength="40">
                        <small class="small hint" id="hint_passwordChk">비밀번호가 일치하지 않습니다.</small>
                    </div>
                    <div class="input-control">
                        <label class="label" for="nickName">닉네임</label>
                        <div class="input_and_button">
                            <input id="nickName" name="nickName" type="text" maxlength="8" placeholder="최소 2자리 이상 8자리 이하 한글, 영문, 숫자">
                            <button id="dup_btn" type="button" value="중복확인" class="dup" onclick="nickDuplChk()">중복 확인</button>
                        </div>
                        <small class="small hint" id="hint_nickName">최소 2자리 이상 8자리 이하 한글, 영문, 숫자만 입력해야 합니다.</small>
                    </div>
                    <div class="input-control">
                        <label for="phone-first">휴대폰 번호 :<br>
                            <input type="text" class="aa" id="phone-first" name="phone-first" maxlength="3">
                            <span class="spans">&nbsp;&nbsp;-</span>&nbsp;
                            <input type="text" class="aa" id="phone-second" name="phone-second" maxlength="4">
                            <span class="spans">&nbsp;&nbsp;-</span>&nbsp;
                            <input type="text" class="aa" id="phone-third" name="phone-third" maxlength="4"></label>
                    </div>
                    <div class="sumit_and_cancel">
                        <button id="submit_btn" type="button" onclick="signupChk()">가입하기</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
