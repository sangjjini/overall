$(document).ready(function(){
    $('#login').on('keyup', function(key){
        if(key.keyCode==13){
            loginChk();
        }
    })

});

// 유효성 검사 메서드
function loginChk() {
    var id = $('#id').val();
    var password = $('#password').val();

    // 아이디 공백 확인
    if (id === "") {
        alert("아이디를 입력해 주세요");
        $("#id").focus();
        return false;
    }

    // 비밀번호 유효성 검사
    else if (password === "") {
        alert("비밀번호를 입력해주세요.");

        $("#password").focus();
        check = false;
    }

    if(check) {
        let obj = {email: id, password: password};
        $.ajax({
            url: 'sign_in',
            type: "post",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(obj)
        }).done(function (response) {
            const result = Object.values(response)[0];
            if(result === "success"){
                location.href="/";
            }else{
                alert("입력한 회원정보가 올바르지 않습니다.");
            }
        });
    }
}

    // 유효성 검사 통과
    // $.ajax({
    //     method: 'POST',
    //     url: '/login',
    //     data: JSON.stringify(obj),
    //     contentType: 'application/json',
    //     success: (response) => {
    //         console.log("성공");
    //         alert('로그인이 완료되었습니다.');
    //         window.location.href='/';
    //         // if(response.result === "loginTrue"){
    //         //     alert('로그인이 완료되었습니다.');
    //         //     window.location.href='/';
    //         // }else if(response.result === "noUser"){
    //         //     alert('유저정보가 존재하지 않습니다.');
    //         // }
    //     },
    //     error: function (request, status, error) {
    //         console.log("code: " + request.status)
    //         // console.log("message: " + request.responseText)
    //         // console.log("error: " + error);
    //     }
    // });
