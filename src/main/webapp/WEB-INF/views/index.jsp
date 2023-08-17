<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-08
  Time: 오후 3:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>오버롤</title>
    <%-- 제이쿼리--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <link type="text/css" href="/css/index.css" rel="stylesheet" />
    <link rel="shortcut icon" href="/images/favicon.ico">
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div id = container>
        <section id="main_slider">
            <div class="slide_img">
                <div class="box">

                </div>
            </div>
            <div class="slide_txt">
                <p>오버롤에 오신것을 환영합니다</p>
                <div class="button"><a href="introduce">오버롤 소개</a></div>
            </div>
        </section>
        <section id="squad_contents">
            <div class="squad_maker">
                <div class="squad_maker_txt">
                <p>당신의 취향의 스쿼드를 만들어 보세요</p>
                    <div class="button"><a href="squad">스쿼드만들기</a></div>
                </div>
            </div>
            <div class="squad_match">
                <div class="squad_match_txt">
                <p>OVERALL에서  스쿼드를</p>
                    <p>만들어 풋살 매치를 즐기세요</p>
                    <div class="button"><a href="/squad/match">스쿼드매치</a></div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    <section>
    </section>
</body>
</html>
