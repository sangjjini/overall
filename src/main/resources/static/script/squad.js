let squadNo;

$(window).on('load', function (){
    const urlParams = new URL(location.href).searchParams;
    squadNo = urlParams.get('no');
    squad();
    invited();
    // 실시간 적용 필요
    chat();
    read();
});

function squad(){
    $.ajax({
        url: "squad/" + squadNo,
        type: "get"
    }).done(function (response){
        $('#name').val(response.name);
        $('#contents').val(response.contents);
        $('#host').val(response.host);
    });
}

function invited(){
    $.ajax({
        url: "joining/" + squadNo + "/invited",
        type: "get"
    }).done(function (response){
        $('#invited').empty();
        response.forEach(members => {
            // 방장 탈퇴 버튼 제거 필요
            if(members.email === $('#host').val()){
                $('#invited').append(
                    `<div>
                    <p>${members.nickname}(방장)</p>
                    <button onclick="out(this.id)" id="${members.code}">방출</button>
                    </div><br>`
                );
            } else {
                $('#invited').append(
                    `<div>
                    <p>${members.nickname}</p>
                    <button onclick="out(this.id)" id="${members.code}">방출</button>
                    </div><br>`
                );
            }
        });
    });
}

function show_invite(){
    $('#invite_list').toggle();
    inviting();
}

function inviting(){
    $.ajax({
        url: "joining/" + squadNo + "/inviting",
        type: "get"
    }).done(function (response){
        $('#inviting').empty();
        response.forEach(members => {
            $('#inviting').append(
                `<div id="${members.code}">
                    <p>${members.nickname}(${members.email})</p>
                    <button onclick="accept(this.id)" id="${members.code}">수락</button>
                    <button onclick="refuse(this.id)" id="${members.code}">거절</button>
                </div>`
            );
        });
    });
}

function invite(){
    let email = $('#email').val();
    $.ajax({
        url: "joining/" + squadNo + "/invite?email=" + email,
        type: "post",
    }).done(function (){
        $('#email').val('');
        inviting();
    });
}

function leave(){
    if(confirm("탈퇴하겠습니까?")){
        $.ajax({
            url: "joining/" + squadNo + "/leave",
            type: "delete"
        });
    }
}

function accept(id){
    $.ajax({
        url: "joining/" + squadNo + "/accept?code=" + id,
        type: "post"
    }).done(function (){
        $("div").remove('#' + id);
        invited();
    });
}

function refuse(id){
    $.ajax({
        url: "joining/" + squadNo + "/refuse?code=" + id,
        type: "delete"
    }).done(function (){
        $("div").remove('#' + id);
        invited();
    });
}

function out(id){
    if(confirm("방출하겠습니까?")){
        $.ajax({
            url: "joining/" + squadNo + "/refuse?code=" + id,
            type: "delete"
        }).done(function (){
            invited();
        });
    }
}

function update(){
    const data = {
        no : squadNo,
        host : "kevin@gmail.com",
        name : $('#name').val(),
        contents : $('#contents').val()};
    $.ajax({
        url: "squad/"+squadNo+"/update",
        type: "post",
        dataType: "json",
        contentType : "application/json",
        data: JSON.stringify(data)
    }).done(function (response){
        const result = Object.values(response)[0];
        if(result === "success"){
            alert("변경 완료");
        } else {
            alert("중복된 스쿼드 이름입니다.");
        }
    });
}

function chat(){
    $.ajax({
        url:"chat/"+squadNo,
        type:"get"
    }).done(function (response){
        let email = "kevin@gmail.com"
        $('#chat').empty();
        response.forEach(chat => {
            if(chat.email === email){
                $('#chat').append(
                    `<div class="myChat" id="${chat.no}">
                        <div>${chat.nickname}</div>
                        <div>${chat.contents}</div>
                    </div><br>`
                );
            } else {
                $('#chat').append(
                    `<div class="otherChat" id="${chat.no}">
                        <div>${chat.nickname}</div>
                        <div>${chat.contents}</div>
                    </div><br>`
                );
            }
        });
        $('#chat').scrollTop($('#chat')[0].scrollHeight)
    });
}

function send(){
    const data = { contents : $('#chatting').val() };
    $.ajax({
        url:"chat/"+squadNo+"/send",
        type:"post",
        dataType: "json",
        contentType : "application/json",
        data: JSON.stringify(data),
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    }).done(function (){
        $('#chatting').val('');
        chat();
    });
}

function read(){
    $.ajax({
        url:"joining/"+squadNo+"/read",
        type:"post"
    });
}
