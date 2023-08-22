var emailDupl = false; // 이메일 중복 여부
var ChkEmailDupl = false; // 이메일 중복 확인 후 결과를 저장.
var nickDupl = false; // 닉네임 중복 여부
var ChkNickDupl = false; // 닉네임 중복 확인 후 결과를 저장.
var verified = false; // 사용자의 이메일 인증 상태 여부
var Chkverified = false;

$(document).ready(function(){
    $('#div_code').hide();
    $('.hint').hide();

    $('input').focusin(function(){
        $(this).parent().find('.hint').hide();
        //$('.hint').hide();
    });

    $('#email').keyup(function(){
        emailDupl = false;
        ChkEmailDupl = false;
        $("#hint_email").text("").css("color", "#ff3860");
        $("#hint_email").hide();
    });

    $('#nickName').keyup(function(){
        nickDupl = false;
        ChkNickDupl = false;
        $("#hint_nickName").text("").css("color", "#ff3860");
        $("#hint_nickName").hide();
    });

    $("#email").click(function() {
        $("hint_email").hide();
    });
});

function join(){
    $('#submit_btn').prop("disabled", true);

    setTimeout(function () {
        $('#submit_btn').prop("disabled", false);
    }, 500);

    const data = {
        email: $("#email").val(),
        password: $("#password").val(),
        passwordChk: $("#passwordChk").val(),
        name: $("#name").val(),
        nickname: $("#nickName").val(),
        phone: $("#phone-first").val()+$('#phone-second').val()+$('#phone-third').val(),
        province: $("#province").val(),
        city: $("#city").val(),
    };

    //이메일 공백 확인
    if ($("#email").val() == "") {
        // alert("이메일 형식에 맞게 작성해 주세요");
        $("#hint_email").text("올바른 이메일을 입력해 주세요");
        $("#hint_email").show();
        $("#email").val("");
        return false;
    }

    //이메일 유효성 검사
    if (!getEmail.test($("#email").val())) {
        //alert("이메일 형식에 맞게 입력해 주세요");
        $("#hint_email").text("이메일 형식에 맞지 않습니다");
        $("#hint_email").show();
        $("#email").val("");
        return false;
    }

    //비밀번호 유효성 검사
    if (!getPw.test($("#password").val())) {
        // alert("비밀번호를 확인해 주세요.");
        $("#hint_password").text("최소 8자리 이상 영문, 숫자, 특수문자 최소 1글자 포함해 입력해야 합니다");
        $("#hint_password").show();
        $("#password").val("");
        return false;
    }

    //비밀번호 똑같은지 재확인
    else if ($("#password").val() !== $("#passwordChk").val()) {
        if ($("#passwordChk").val() !== "") {
            // alert("비밀번호가 일치하지 않습니다.");
            $("#password").val("");
            //$("#password").focus();
        } else {
            //alert("비밀번호 확인을 입력해 주세요.");
        }
        $("#hint_passwordChk").show();
        return false;
    }

    //이름 유효성
    if (!getName.test($("#name").val())) {
        // alert("성함을 입력해 주세요!");
        $("#hint_name").text("성함을 입력해 주세요").css("color", "#ff3860");
        $("#hint_name").show();
        $("#name").val("");
        return false;
    }

    // 닉네임 유효성
    if (!getNickName.test($("#nickName").val())) {
        // alert("사용할 수 없는 닉네임 입니다");
        $("#hint_nickName").text("최소 2자리 이상 8자리 이하 한글, 영문, 숫자만 입력해야 합니다").css("color", "#ff3860");
        $("#hint_nickName").show();
        $("#nickName").val("");
        //$("#nickName").focus();
        return false;
    }

    if (!getPhone.test(phone)) {
        alert('잘못된 휴대폰 번호입니다');
        $('#phone-first').focus();
        return false;
    }

    $.ajax({
        // 회원가입 수행 요청
        type: "POST",
        url: "api/v1/members/join",
        data: JSON.stringify(data), // http body 데이터
        contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지 (MIME)
        dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String(문자열), 만약 생긴게 json이라면 javascript 오브젝트로 변경
    }).done(function (response) {
        const result = Object.values(response)[0];
        if(result === "success"){
            alert("회원가입이 완료되었습니다.");
            location.href = "/login";
        }else{
            if (emailDupl) {
                $("#hint_email").text("중복된 이메일 입니다");
                $("#hint_email").show();
                return false;
            }
        }
    });
}

function sendCode() {
    var email = $("#email").val();

    // 서버에 이메일 발송 요청을 보내는 AJAX 요청
    $.ajax({
        type: "POST",
        url: "/join/mailConfirm",
        data: { email: email },
        success: function(response) {
            console.log("인증 코드 발송 성공:", response);
            alert("인증 코드가 이메일로 발송되었습니다.");
        },
        error: function(error) {
            console.error("인증 코드 발송 실패:", error);
            alert("인증 코드 발송에 실패했습니다. 다시 시도해주세요.");
        }
    });
}

function verify() {
    var code = $("#code").val();

    // 서버에 인증 코드 확인 요청을 보내는 AJAX 요청
    $.ajax({
        type: "POST",
        url: "/verifyCode",
        data: { code: code },
        success: function(response) {
            if (response === "success") {
                console.log("인증 코드 확인 성공");
                alert("이메일 인증이 완료되었습니다!");
                // 이후 회원가입 버튼 활성화 등 추가 작업 수행
            } else {
                console.error("인증 코드 확인 실패:", response);
                alert("인증 코드가 일치하지 않습니다. 다시 확인해주세요.");
            }
        },
        error: function(error) {
            console.error("인증 코드 확인 실패:", error);
            alert("인증 코드 확인에 실패했습니다. 다시 시도해주세요.");
        }
    });
}
