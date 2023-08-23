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
    <script src="/script/overall_update.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<input type="hidden" id="homin" value="${log}">
<div class="wrap">
    <div class="contents">
        <div class="contents_menu">
            <div class="menu"><a href="mypage"><h1>오버롤</h1></a></div>
<%--            <div class="menu"><a href="/profileUpdate"><h1>프로필 수정</h1></a></div>--%>
            <div class="menu on"><a href="/overallUpdate"><h1>오버롤 설정</h1></a></div>
        </div>
        <div class="overall_contents">
            <div class="overall_update">
                <h1>오버롤 설정</h1>
                <div class="update_contents_wrap">
                    <div class="update_contents">
                        <div class="txt">당신은 50m달리기가 몇초입니까?</div>
                        <input type="text" id="speed" placeholder="시간 입력">
                    </div>
                    <div class="update_contents">
                        <div class="txt">나이를 입력해주세요</div>
                        <input type="text" id="age" placeholder="나이 입력">
                    </div>
                    <div class="update_contents">
                        <div class="txt">키를 입력해주세요</div>
                        <input type="text" id="height" placeholder="키 입력">
                    </div>
                    <div class="update_contents">
                        <div class="txt">몸무게를 입력해주세요</div>
                        <input type="text" id="weight" placeholder="몸무게 입력">
                    </div>
                    <div class="update_contents">
                        <div class="txt">당신의 발 숙련도를 입력해주세요</div>
                        <h2>왼발</h2>
                        <input type="text" id="rightfoot" placeholder="왼발수치(1~5)">
                        <h2>오른발</h2>
                        <input type="text" id="leftfoot" placeholder="오른발수치(1~5)">
                    </div>
                    <div class="update_contents">
                        <div class="txt">당신의 플레이스타일은?</div>
                        <div class="playstyle_wrap">
                        <label class="playstyle_label" for="playstyle">플레이스타일</label>
                        <select id ="playstyle">
                            <option value="초보자">초보자</option>
                            <option value="스트라이커">스트라이커</option>
                            <option value="딥라잉플레이메이커">딥라잉플레이메이커</option>
                            <option value="플레이메이커">플레이메이커</option>
                            <option value="인버티드풀백">인버티드 풀백</option>
                            <option value="메짤라">메짤라</option>
                            <option value="올라운더">올라운더</option>
                            <option value="유리몸">유리몸</option>
                            <option value="예리한감아차기">예리한 감아차기</option>
                            <option value="트러블메이커">트러블메이커</option>
                        </select>
                        </div>
                    </div>
                    <div class="update_contents">
                        <div class="txt">당신의 포지션은 어디입니까</div>
                        <div class="pos_wrap">
                            <label class="pos_label" for="pos">포지션</label>
                        <select id="pos">
                            <option value="FW">FW(공격)</option>
                            <option value="ST">ST(공격)</option>
                            <option value="LW">LW(공격)</option>
                            <option value="RW">RW(공격)</option>
                            <option value="FIVO">FIVO(공격)</option>
                            <option value="CAM">CAM(미드필더)</option>
                            <option value="LM">LM(미드필더)</option>
                            <option value="RM">RM(미드필더)</option>
                            <option value="CM">CM(미드필더)</option>
                            <option value="CDM">CDM(미드필더)</option>
                            <option value="MF">MF(미드필더)</option>
                            <option value="CB">CB(수비)</option>
                            <option value="LB">LB(수비)</option>
                            <option value="RB">RB(수비)</option>
                            <option value="DF">DF(수비)</option>
                        </select>
                        </div>

                    </div>
                    <div class="update_contents">
                    <div class="button">
                        <input onclick="overallupdate()" type="button" value="완료">
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
