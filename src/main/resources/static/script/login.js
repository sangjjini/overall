$(document).ready(function(){
    $('#login').on('keyup', function(key){
        if(key.keyCode===13){
            loginChk();
        }
    })

});

// 유효성 검사 메서드
function loginChk() {
    var email = $('#email').val();
    var password = $('#password').val();

    // 아이디 공백 확인
    if (email === "") {
        alert("이메일을 입력해 주세요");
        $("#email").focus();
        return false;
    }

    // 비밀번호 유효성 검사
    else if (password === "") {
        alert("비밀번호를 입력해주세요.");
        $("#password").focus();
        return false;
    }

    var loginData = {"email": email, "password": password};
    // 유효성 검사 통과
    $.ajax({
        type: 'POST',
        url: '/LogController/login',
        contentType: 'application/json', // JSON 형식으로 데이터를 전송
        data: JSON.stringify(loginData), // 데이터를 JSON
        // error: function (request, status, error) {
        //     console.log("code: " + request.status)
        //     console.log("message: " + request.responseText)
        //     console.log("error: " + error);
        // },
        // dataType: "json", // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String(문자열), 만약 생긴게 json이라면 javascript 오브젝트로 변경

        success: function (response){
            // console.log(response.result);
            if (result === 0) {
                alert('로그인이 완료되었습니다.\n환영합니다.');
                window.location.href = '/';
            } else if (response.result === "noMember") {
                alert('유저정보가 존재하지 않습니다.');
            }
        },
        // error: () => {
        //     alert('로그인 오류');
        // },
    }).error()
}