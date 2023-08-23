$(document).ready(function () {
    $('#div_code').hide();
    $('.small_hint').hide();

    $('input').focusin(function () {
        $(this).parent().find('.hint').hide();
        //$('.hint').hide();
    });

    $('#email').keyup(function () {
        emailDupl = false;
        ChkEmailDupl = false;
        $("#hint_email").text("").css("color", "#ff3860");
        $("#hint_email").hide();
    });

    $('#password').keyup(function () {
        $("#hint_password").text("").css("color", "#ff3860");
        $("#hint_password").hide();
    });

    $("#email").click(function () {
        $("hint_email").hide();
    });
});

// 유효성 검사 메서드
function loginChk() {
    const id = $('#email').val();
    const password = $('#password').val();
    let check = true;

    // 아이디 공백 확인
    // if (id === "") {
    //     // alert("아이디를 입력해 주세요");
    //     // $("#id").focus();
    //     check = false;
    // }
    //이메일 공백 확인
    if ($("#email").val() == "") {
        // alert("이메일 형식에 맞게 작성해 주세요");
        $("#hint_email").text("이메일을 입력해 주세요");
        $("#hint_email").show();
        $("#email").val("");
        check = false;
    }

    //비밀번호 공백확인
    else if ($("#password").val() == "") {
        // alert("이메일 형식에 맞게 작성해 주세요");
        $("#hint_password").text("비밀번호를 입력해 주세요");
        $("#hint_password").show();
        $("#password").val("");
        check = false;
    }

    // 비밀번호 유효성 검사
    // else if (password === "") {
    //     // alert("비밀번호를 입력해주세요.");
    //     // $("#password").focus();
    //     check = false;
    // }

    if (check) {
        let obj = {email: id, password: password};
        $.ajax({
            url: 'sign_in',
            type: "post",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(obj)
        }).done(function (response) {
            const result = Object.values(response)[0];
            if (result === "success") {
                const nickname = response.nickname;

                alert("★풋살 매칭 사이트★\nOVERALL에 오신 것을 환영합니다!\n활기찬 하루 보내세요^_^" );
                location.href = "/";
            } else {
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
