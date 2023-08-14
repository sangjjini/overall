var idDupl = false; // 아이디 중복 여부
var ChkIdDupl = false; // 아이디 중복 확인 후 결과를 저장.
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

    var getnickName = RegExp(/^[가-힣a-zA-Z0-9]{2,8}$/); // 한글, 영어 대소문자, 숫자 [ 2~8자리 까지 입력가능 ]
    var getid= RegExp(/^[a-z0-9]{4,16}$/); // 영어 소문자, 숫자 [ 4~16자리 까지 입력가능 ]
    var getpw= RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/); // 문자, 숫자, 특수문자 최소 1개씩 포함시켜야함. [ 최소 8자리이상 입력 ]
    var getMail = RegExp(/^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,6}$/); // 이메일 형식만 입력가능
}
