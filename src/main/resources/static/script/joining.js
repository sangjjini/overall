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

    var getEmail = RegExp(/^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,6}$/); // 이메일 형식만 입력 가능 (@.com)
    var getPw = RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@!%*#?&]{8,16}$/); // 문자, 숫자, 특수문자 최소 1개씩 포함시켜야함. [ 최소 8자리이상 입력 ]
    var getName = RegExp(/^[가-힣]{2,6}|[a-zA-Z]{2,15}\s[a-zA-Z]{2,15}$/);
    var getNickName = RegExp(/^[가-힣a-zA-Z0-9]{2,8}$/); // 한글, 영어 대소문자, 숫자 [ 2~8자리 까지 입력 가능 ]
    var phoneFirst = $('#phone-first').val();
    var phoneSecond = $('#phone-second').val();
    var phoneThird = $('#phone-third').val();
    var getPhone = regExp = /^01(?:0|1|[6-9])-?([0-9]{3,4})-?([0-9]{4})$/;
    var phone = phoneFirst + '-' + phoneSecond + '-' + phoneThird;

    //이메일 공백 확인
    if ($("#email").val() == "") {
        // alert("이메일 형식에 맞게 작성해 주세요");
        $("#hint_email").text("이메일을 입력해 주세요");
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

    if(!verified){
        $("#hint_email").text("이메일이 인증되지 않았습니다.");
        $("#hint_email").show();
        return false;
    }

    //비밀번호 유효성 검사
    if (!getPw.test($("#password").val())) {
        // alert("비밀번호를 확인해 주세요.");
        $("#hint_password").text("최소 8자리 이상 영문, 숫자, 특수문자 최소 1글자 포함해 입력해야 합니다").css("color", "#ff3860");
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

    if (!ChkNickDupl) {
        $("#hint_nickName").text("닉네임 중복체크를 해야합니다");
        $("#hint_nickName").show();
        return false;
    }

    if (nickDupl) {
        $("#hint_nickName").text("중복된 닉네임 입니다");
        $("#hint_nickName").show();
        return false;
    }

    // 휴대폰 번호 유효성
    if (!getPhone.test(phone)) {
        alert('잘못된 휴대폰 번호입니다');
        $('#phone-first').focus();
        return false;
    }

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

    // console.log("email:" + formData.email);

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
            $("#hint_email").text("중복된 이메일입니다.").css("color", "red");
            $("#hint_email").show();
        }
    });
}


// 이메일 인증번호
function checkEmail(){
    const addr = $('#email').val();
    $.ajax({
        type : "POST",
        url : "login/mailConfirm?email=" + addr,
        data : {
            "email" : $("#email").val()
        },
        success : function(data){
            alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
            // console.log("data : "+data);
            chkEmailConfirm(data, $("#verify_btn"), $("#memailconfirmTxt"));
        }
    });
        // console.log(error);
    //     error = function(error) {
    //     console.error("실패:", error);
    //     alert("오류");
    // }
}

// 이메일 인증번호 체크 함수
function chkEmailConfirm(data, $verify_btn, $memailconfirmTxt){
    $verify_btn.on("keyup", function(){
        if (data !== $verify_btn.val()) { //
            emconfirmchk = false;
            $memailconfirmTxt.html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>")
            $("#emconfirmchk").css({
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            })
            // console.log("중복아이디");
        } else { // 아니면 중복아님
            emconfirmchk = true;
            $memailconfirmTxt.html("<span id='emconfirmchk'>인증번호 확인 완료</span>")

            $("#emconfirmchk").css({
                "color" : "#0D6EFD",
                "font-weight" : "bold",
                "font-size" : "10px"

            })
        }
    })
}

// 클라이언트에서 닉네임 중복 확인 버튼을 누를 때 실행되는 함수
function nickDuplChk() {
    var nickName = $('#nickName').val();

    $.ajax({
        type: "GET",
        url: "/api/v1/members/checkNickname/" + nickName, // nickName을 실제 값으로 대체
        success: function(response) {
            console.log(response)
            if (response === true) {
                //닉네임이 중복되는 경우에 실행
                $("#hint_nickName").text("이미 사용중인 닉네임입니다").css("color", "#ff3860");
            } else {
                $("#hint_nickName").text("사용 가능한 닉네임입니다").css("color", "green");
            }
            $("#hint_nickName").show();
        },
        error: function(error) {
            console.error("닉네임 중복 확인 실패:", error);
            alert("닉네임을 먼저 입력해 주세요.");
        }
    });
}


