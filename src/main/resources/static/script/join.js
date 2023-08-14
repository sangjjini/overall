var emailDupl = false; // 아이디 중복 여부
var ChkEmailDupl = false; // 아이디 중복 확인 후 결과를 저장.
var nickDupl = false; // 닉네임 중복 여부
var ChkNickDupl = false; // 닉네임 중복 확인 후 결과를 저장.
var verified = false; // 사용자의 이메일 인증 상태 여부

$(document).ready(function(){
    $('#div_code').hide();
    $('.hint').hide();

    $('input').focusin(function(){
        $(this).parent().find('.hint').hide();
        //$('.hint').hide();
    });

    $('#id').keyup(function(){
        idDupl = false;
        ChkIdDupl = false;
        $("#hint_email").text("").css("color", "#ff3860");
        $("#hint_email").hide();
    });

    $('#nickName').keyup(function(){
        nickDupl = false;
        ChkNickDupl = false;
        $("#hint_nickName").text("").css("color", "#ff3860");
        $("#hint_nickName").hide();
    });
})

function signupChk(){
    $('#submit_btn').prop("disabled", true);

    setTimeout(function(){
        $('#submit_btn').prop("disabled", false);
    }, 500);

    var getNickName= RegExp(/^[가-힣a-zA-Z0-9]{2,8}$/); // 한글, 영어 대소문자, 숫자 [ 2~8자리 까지 입력 가능 ]
    var getEmail = RegExp(/^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,6}$/); // 이메일 형식만 입력 가능 (@)
    var getPw= RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@!%*#?&]{8,16}$/); // 문자, 숫자, 특수문자 최소 1개씩 포함시켜야함. [ 최소 8자리이상 입력 ]
    var phoneFirst = $('#phone-first').val();
    var phoneSecond = $('#phone-second').val();
    var phoneThird = $('#phone-third').val();
    // var regPhone = /(01[0|1|6|9|7])[-](\d{3}|\d{4})[-](\d{4}$)/g;
    var getPhone = regExp = /^01(?:0|1|[6-9])-?([0-9]{3,4})-?([0-9]{4})$/;
    var phone = phoneFirst + '-' + phoneSecond + '-' + phoneThird;

    //이메일 공백 확인
    if($("#email").val() == ""){
        // alert("이메일 형식에 맞게 작성해 주세요");
        $("#hint_email").text("이메일을 확인해 주세요!");
        $("#hint_email").show();
        //$("#email").focus();
        return false;
    }

    //이메일 유효성 검사
    if(!getEmail.test($("#email").val())){
        //alert("이메일 형식에 맞게 입력해 주세요");
        $("#hint_email").text("이메일 형식에 맞지 않습니다.");
        $("#hint_email").show();
        $("#email").val("");
        //$("#email").focus();
        return false;
    }

    if (!ChkEmailDupl){
        $("#hint_email").text("이메일 중복체크를 해야합니다.");
        $("#hint_email").show();
        return false;
    }

    if (emailDupl){
        $("#hint_email").text("중복된 이메일 입니다.");
        $("#hint_email").show();
        return false;
    }

    //비밀번호
    if(!getPw.test($("#password").val())){
        // alert("비밀번호를 확인해 주세요.");
        $("#hint_password").text("최소 8자리 이상 영문, 숫자, 특수문자 최소 1글자 포함해 입력해야 합니다.");
        $("#hint_password").show();
        $("#password").val("");
        //$("#password").focus();
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
            //$("#passwordChk").focus();
        }
        $("#hint_passwordChk").show();
        return false;
    }

    // 닉네임 유효성
    if (!getNickName.test($("#nickName").val())){
        // alert("사용할 수 없는 닉네임 입니다");
        $("#hint_nickName").text("최소 2자리 이상 8자리 이하 한글, 영문, 숫자만 입력해야 합니다.").css("color", "#ff3860");
        $("#hint_nickName").show();
        $("#nickName").val("");
        //$("#nickName").focus();
        return false;
    }

    if (!ChkNickDupl){
        $("#hint_nickName").text("닉네임 중복체크를 해야합니다.");
        $("#hint_nickName").show();
        return false;
    }

    if (nickDupl){
        $("#hint_nickName").text("중복된 닉네임 입니다.");
        $("#hint_nickName").show();
        return false;
    }

    if(!verified){
        $("#hint_email").text("이메일이 인증되지 않았습니다.");
        $("#hint_email").show();
        return false;
    }

    if (!getPhone.test(phone)) {
        alert('잘못된 휴대폰 번호입니다.');
        $('#phone-first').focus();
        return false;
    }

    var formData = {
        email: $("#email").val(),
        password: $("#password").val(),
        passwordChk: $("#passwordChk").val(),
        nickName: $("#nickName").val(),
    };


    console.log("email:"+formData.email);

    //servlet에 ajax 요청 보내기
    $.ajax({
        type: "POST",
        url: "/joinForm", // 서블릿의 URL
        //contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: formData,
        //dataType: "json",
        //complete: poll, timeout: 30000,
        /*success: function(response) {
            //console.log(response.message);
            // 서버로부터의 응답을 처리
            if (response.message == "Success") {
                alert("회원가입이 성공적으로 완료되었습니다.");
                location.href = '/login'; // 리다이렉트(회원가입 완료 시 로그인창으로 이동하도록 설정)
            } else {
                alert("회원가입에 실패했습니다.");
            }
        },
        error: function(xhr, status, error) {
            // 에러 처리
            console.error(error);
        }*/
    }).done(json => {
        if(json.result == true){
            alert("회원가입에 성공했습니다.");
            location.href = "/login";
        } else{
            alert("회원가입에 실패했습니다.");
        }
    });
}
