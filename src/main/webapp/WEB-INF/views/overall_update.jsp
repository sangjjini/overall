<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-17
  Time: 오후 2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>오버롤 수정</title>
    <%-- 제이쿼리--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link type="text/css" href="/css/reset.css" rel="stylesheet" />
    <link type="text/css" href="/css/overall_update.css" rel="stylesheet" />
    <link rel="shortcut icon" href="/images/favicon.ico">
    <script src="/script/mypage.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="wrap">
    <div class="contents">
        <div class="contents_menu">
            <div class="menu"><a href="mypage"><h1>오버롤</h1></a></div>
            <div class="menu"><a href="/profileUpdate"><h1>프로필 수정</h1></a></div>
            <div class="menu on"><a href="/overallUpdate"><h1>오버롤 설정</h1></a></div>
        </div>
        <div class="overall_contents">
            <div class="overall_update">
                <h1>오버롤 설정</h1>
                <div class="update_contents_wrap">
                    <div class="update_contents">
                        <div class="txt">당신은 50m달리기가 몇초입니까?</div>
                        <input type="text">
                    </div>
                    <div class="update_contents">
                        <div class="txt">나이를 입력해주세요</div>
                        <input type="text">
                    </div>
                    <div class="update_contents">
                        <div class="txt">키를 입력해주세요</div>
                        <input type="text">
                    </div>
                    <div class="update_contents">
                        <div class="txt">몸무게를 입력해주세요</div>
                        <input type="text">
                    </div>
                    <div class="update_contents">
                        <div class="txt">몸무게를 입력해주세요</div>
                        <input type="text">
                    </div>
                    <div class="update_contents">
                        <div class="txt">당신의 발 숙련도를 입력해주세요</div>
                        <input type="text">
                        <input type="text">
                    </div>
                    <div class="update_contents">
                        <div class="txt">당신의 플레이스타일은?</div>
                        <select>
                            <option value="메짤라">메짤라</option>
                        </select>
                    </div>
                    <div class="update_contents">
                        <div class="txt">당신의 포지션은 어디입니까</div>
                        <select>
                            <option value="CF">CF</option>
                        </select>
                    </div>
                    <div class="update_contents">
                    <div class="button">
                        <input type="submit" value="완료">
                    </div>

                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
