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
    <script src="/script/mypage.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="wrap">
    <div class="contents">
        <div class="contents_menu">
            <div class="menu"><a href="mypage"><h1>오버롤</h1></a></div>
            <div class="menu on"><a href="/profileUpdate"><h1>프로필 수정</h1></a></div>
            <div class="menu"><a href="#"><h1>오버롤 설정</h1></a></div>
            <div class="menu"><a href="#"><h1>고객센터</h1></a></div>

        </div>
        <div class="profile_contents">
            <div class="profile_update">
                <h1>프로필 수정</h1>

                <div class="update_contents_wrap">
                    <div class="update_contents">
                        비밀번호변경
                    </div>
                    <div class="update_contents">
                        email변경
                    </div>
                    <div class="update_contents">
                        닉네임변경
                    </div>
                    <div class="update_contents">
                        주소변경
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
