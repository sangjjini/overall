// let session = "SIUUU@naver.com";
// $(window).on('load', function(){
//     if(session!=null){
//         $('#header .mypage_wrap').addClass('logined');
//         $('#header .login_wrap').addClass('logined');
//     } else {
//         $('#header .mypage_wrap').removeClass('logined');
//         $('#header .login_wrap').removeClass('logined');
//     }
// })
let alarm_check = 0;
$(window).on('load', function (){
    alarm();
});
function logout(){
    $.ajax({
        url:"/logout",
        type:"post"
    }).done(function (){
        location.href = "/";
    });
}

function alarm(){
    $.ajax({
        url:"joining/alarm",
        type:"get"
    }).done(function (response){
        $('#alarm_area').empty();
        response.forEach(squads => {
            $('#alarm_area').append(
                `<div>
                    <div>${squads.name} 초대</div>
                    <button onclick="refuse_invite(this.id)" id="${squads.no}" 
                    class="answer_btn refuse_btn">X</button>
                    <button onclick="accept_invite(this.id)" id="${squads.no}" 
                    class="answer_btn accept_btn">V</button>               
                </div>`
            );
            alarm_check++;
        });
    })
}

function show_alarm(){
    $('#alarm_popup').show();
}

function accept_invite(id){
    $.ajax({
        url: "joining/" + squadNo + "/accept?code=" + id,
        type: "post"
    }).done(function (){
        $("div").remove('#' + id);
    });
}

function refuse_invite(id){
    $.ajax({
        url: "joining/" + squadNo + "/refuse?code=" + id,
        type: "delete"
    }).done(function (){
        $("div").remove('#' + id);
    });
}

