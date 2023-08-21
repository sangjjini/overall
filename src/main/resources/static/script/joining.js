function join(){
    // $('#submit_btn').prop("disabled", true);
    //
    // setTimeout(function () {
    //     $('#submit_btn').prop("disabled", false);
    // }, 500);


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
            $("#hint_email").hide();
        });
    });



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
            alert("중복된 이메일이 있습니다.");
        }
    });
}
