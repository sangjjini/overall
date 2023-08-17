var emailDupl = false; // 이메일 중복 여부
var ChkEmailDupl = false; // 이메일 중복 확인 후 결과를 저장.
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

    $('#email').keyup(function(){
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

    $("#email").click(function() {
        $("hint_email").hide();
    });
});

function signupChk() {
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
    // var regPhone = /(01[0|1|6|9|7])[-](\d{3}|\d{4})[-](\d{4}$)/g;
    var getPhone = regExp = /^01(?:0|1|[6-9])-?([0-9]{3,4})-?([0-9]{4})$/;
    var phone = phoneFirst + '-' + phoneSecond + '-' + phoneThird;


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
        $("#hint_email").text("이메일 형식에 맞지 않습니다.");
        $("#hint_email").show();
        $("#email").val("");
        return false;
    }

    // if (!Chkverified){
    //     $("#hint_email").text("이메일 인증을 해야합니다.");
    //     $("#hint_email").show();
    //     return false;
    // }

    if (emailDupl) {
        $("#hint_email").text("중복된 이메일 입니다.");
        $("#hint_email").show();
        return false;
    }

    //비밀번호
    if (!getPw.test($("#password").val())) {
        // alert("비밀번호를 확인해 주세요.");
        $("#hint_password").text("최소 8자리 이상 영문, 숫자, 특수문자 최소 1글자 포함해 입력해야 합니다.");
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
    if (!getName.test($("#name").val())){
        alert("성함을 입력해 주세요!");
        $("#name").val("");
        $("#name").focus();
        return false;
    }

    // 닉네임 유효성
    if (!getNickName.test($("#nickName").val())) {
        // alert("사용할 수 없는 닉네임 입니다");
        $("#hint_nickName").text("최소 2자리 이상 8자리 이하 한글, 영문, 숫자만 입력해야 합니다.").css("color", "#ff3860");
        $("#hint_nickName").show();
        $("#nickName").val("");
        //$("#nickName").focus();
        return false;
    }

    // if (!ChkNickDupl) {
    //     $("#hint_nickName").text("닉네임 중복체크를 해야합니다");
    //     $("#hint_nickName").show();
    //     return false;
    // }
    //
    // if (nickDupl) {
    //     $("#hint_nickName").text("중복된 닉네임 입니다");
    //     $("#hint_nickName").show();
    //     return false;
    // }

    // if (!verified) {
    //     $("#hint_email").text("이메일이 인증되지 않았습니다");
    //     $("#hint_email").show();
    //     return false;
    // }

    if (!getPhone.test(phone)) {
        alert('잘못된 휴대폰 번호입니다');
        $('#phone-first').focus();
        return false;
    }


    // // 이메일 인증 코드 요청을 보내는 함수
    // function sendVerificationCode() {
    //     var email = $(#email).val();
    //
    //     $.ajax({
    //         type: "POST",
    //         url: "/emailConfirm", // 이메일 인증 코드 요청을 처리하는 컨트롤러 URL
    //         data: { email: email }, // 이메일 데이터 전송
    //         success: function(response) {
    //             alert("인증 코드가 이메일로 전송되었습니다.");
    //         },
    //          error: function(xhr, status, error){
    //             alert("인증 코드 발송중 오류가 발생했습니다.");
    //          }
    //     });
    // }




    var data = {
        email: $("#email").val(),
        password: $("#password").val(),
        passwordChk: $("#passwordChk").val(),
        name: $("#name").val(),
        nickName: $("#nickName").val(),
    };

    // console.log("email:" + formData.email);

        // var email = $("#email").val();
        // var verificationCode = $("#verificationCode").val();

    $.ajax({
        // 회원가입 수행 요청
        type: "POST",
        url: "/api/v1/members/join",
        data: JSON.stringify(data), // http body 데이터
        contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지 (MIME)
        dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String(문자열), 만약 생긴게 json이라면 javascript 오브젝트로 변경
    }).done(function (resp) {
        // 결과가 정상이면 done 실행
        alert("회원가입이 완료되었습니다.");
        //console.log(resp);
        location.href = "/login";
    }).fail(function (error) {
        // 실패하면 fail 실행
        alert("회원가입이 실패하였습니다.");
        alert(JSON.stringify(error));
    });
        // $.ajax({
        //     type: "POST",
        //     url: "join", // 회원 가입 완료 처리를 하는 컨트롤러 URL
        //     //contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        //     data: formData,
        //     //dataType: "json",
        //     //complete: poll, timeout: 30000,
        //     /*success: function(response) {
        //         //console.log(response.message);
        //         // 서버로부터의 응답을 처리
        //         if (response.message == "Success") {
        //             alert("회원가입이 성공적으로 완료되었습니다.");
        //             location.href = '/login'; // 리다이렉트(회원가입 완료 시 로그인창으로 이동하도록 설정)
        //         } else {
        //             alert("회원가입에 실패했습니다.");
        //         }
        //     },
        //     error: function(xhr, status, error) {
        //         // 에러 처리
        //         console.error(error);
        //     }*/
        // }).done(json => {
        //     if(json.result == true){
        //         alert("회원가입에 성공했습니다.");
        //         location.href = "login";
        //     } else{
        //         alert("회원가입에 실패했습니다.");
        //     }
        // });
        // }


        // function sendCode(){
        //     const addr = $('#email').val();
        //     $('#send_btn').prop("disabled", true);
        //
        //     setTimeout(function(){
        //         $('#send_btn').prop("disabled", false)
        //     }, 3000);
        //
        //     if ($("#email").val() == "") {
        //         $("#hint_email").text("이메일 형식에 맞지 않습니다.");
        //         $("#hint_email").show();
        //         return false;
        //     } else {
        //         var data = {"addr":addr};
        //
        //         $.ajax({
        //             "method":"POST",
        //             "url":"DuplEmail",
        //             "data":JSON.stringify(data),
        //             "Content-Type":"application/json"
        //             //"headers":{'Content-Type':'application/json'}
        //         }).done(json => {
        //             if(json.dupl == true){
        //                 $("#hint_email").text("중복된 이메일 입니다.");
        //                 $("#hint_email").show();
        //             } else {
        //                 $.ajax({
        //                     "method":"POST",
        //                     "url":`SendCode?addr=${addr}`
        //                 }).done(json => {
        //                     $('#div_code').show();
        //                     alert('이메일로 코드가 전송되었습니다.');
        //                     console.log("인증발송 코드 : " + json.code);
        //                     console.log("인증발송 제한 시간 : " + json.time);
        //                 })
        //             }
        //         });
        //     }
        // }

        // function verify(){
        //     $('#hint_email').hide();
        //     $.ajax({
        //         "method":"POST",
        //         "url":"GetCode"
        //     }).done(json => {
        //         let currentTime = new Date();
        //         let currentTimeMillis = currentTime.getTime();
        //
        //         console.log("인증버튼 코드 : " + json.code);
        //         console.log("인증버튼 제한 시간 : " + json.time);
        //         console.log("인증버튼 현재 시간 : " + currentTimeMillis);
        //
        //         if(currentTimeMillis > json.time){
        //             alert("유효하지 않은 코드 입니다.");
        //         } else {
        //             let code = $('#code').val();
        //             if(code == json.code){
        //                 $('#email').prop("disabled", true);
        //                 $('#send_btn').prop("disabled", true);
        //                 $('#code').prop("disabled", true);
        //                 $('#verify_btn').prop("disabled", true);
        //                 verified = true;
        //             } else {
        //                 alert("유효하지 않은 코드 입니다.");
        //             }
        //         }
        //     })
        // }

        // function emailDuplChk(){
        //     $("#hint_email").hide();
        //     let id = $('#email').val();
        //     $('#email_dup_btn').prop("disabled", true);
        //     $.ajax({
        //         "method":"POST",
        //         "url":`/DuplEmail?email=${email}`
        //     }).done(json => {
        //         console.log(json.check);
        //         if(json.check == true){
        //             console.log(json.check);
        //             idDupl = true;
        //             $("#hint_email").text("중복된 아이디 입니다.");
        //             $("#hint_email").show();
        //         }else{
        //             $("#hint_email").text("중복되는 아이디가 없습니다.").css("color", "green");
        //             $("#hint_email").show();
        //             emailDupl = false;
        //             ChkEmailDupl = true;
        //         }
        //     })
        //     setTimeout(function(){
        //         $('#email_dup_btn').prop("disabled", false)
        //     }, 3000);
        // }


        // function nickDuplChk(){
        //     $("#hint_nickName").hide();
        //     let nickname = $('#nickName').val();
        //     $('#nick_dup_btn').prop("disabled", true);
        //     $.ajax({
        //         "method":"POST",
        //         "url":`/DuplNickname?nickname=${nickname}`
        //     }).done(json => {
        //         console.log("nick:"+json.check);
        //         if(json.check == true){
        //             console.log(json.check);
        //             nickDupl = true;
        //             $("#hint_nickName").text("중복된 닉네임 입니다.");
        //             $("#hint_nickName").show();
        //         }else{
        //             $("#hint_nickName").text("중복되는 닉네임이 없습니다.").css("color", "green");
        //             $("#hint_nickName").show();
        //             nickDupl = false;
        //             ChkNickDupl = true;
        //         }
        //     })
        //     setTimeout(function(){
        //         $('#nick_dup_btn').prop("disabled", false)
        //     }, 3000);
        //
}
