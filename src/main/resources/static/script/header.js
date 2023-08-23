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
        alert("로그아웃 완료");
        location.href = "/";
    });
}

function alarm(){
    $.ajax({
        url:"/joining/alarm",
        type:"get"
    }).done(function (response){
        $('#alarm_area').empty();
        response.forEach(squads => {
            $('#alarm_area').append(
                `<div id="${squads.no}" class="inviting_list">
                    <div>
                        팀 <span class="team_name">${squads.name}</span> 초대
                    </div>
                    <div>
                        <button onclick="refuse_invite(this.id)" id="${squads.no}" 
                        class="answer_btn refuse_btn">X</button>
                        <button onclick="accept_invite(this.id)" id="${squads.no}" 
                        class="answer_btn accept_btn">V</button>
                    </div>
                </div>`
            );
            alarm_check++;
        });
        alarm_show();
    })
}

function show_alarm(){
    $('#alarm_popup').toggle();
}

function close_alarm(){
    $('#alarm_popup').hide();
}

function accept_invite(id){
    $.ajax({
        url: "/joining/" + id + "/accept",
        type: "post"
    }).done(function (){
        $("div").remove('#' + id);
        alarm_check--;
        alarm_show();
    });
}

function refuse_invite(id){
    $.ajax({
        url: "/joining/" + id + "/refuse",
        type: "delete"
    }).done(function (){
        $("div").remove('#' + id);
        alarm_check--;
        alarm_show();
    });
}

function alarm_show(){
    const alarm_cnt = $('#alarm_cnt');
    const alarm_num = $('.alarm_num');
    alarm_num.val(alarm_check);
    if(alarm_check !== 0){
        alarm_cnt.show();
    }else{
        alarm_cnt.hide();
    }
}


