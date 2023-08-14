let squadNo;

$(window).on('load', function (){
    // 스쿼드 번호 호출 필요
    squadNo = 6;
    squad();
    invited();
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
            let email = members.email.split("@");
            email = email[0] + "_" + email[1];
            email = email.split(".");
            email = email[0] + "_" + email[1];
            // 방장 탈퇴 버튼 제거 필요
            if(members.email === $('#host').val()){
                $('#invited').append(
                    `<div>
                    <p>${members.nickname}(방장)</p>
                    <button onclick="out(this.id)" id="`+email+`">방출</button>
                </div>`
                );
            } else {
                $('#invited').append(
                    `<div>
                    <p>${members.nickname}</p>
                    <button onclick="out(this.id)" id="` + email + `">방출</button>
                </div>`
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
            let email = members.email.split("@");
            email = email[0] + "_" + email[1];
            email = email.split(".");
            email = email[0] + "_" + email[1];
            $('#inviting').append(
                `<div id="`+email+`">
                    <p>${members.nickname}</p>
                    <button onclick="accept(this.id)" id="`+email+`">수락</button>
                    <button onclick="refuse(this.id)" id="`+email+`">거절</button>
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
    }).done(function (response){
        // const result = Object.values(response)[0];
        // if(result === "success"){
        //     alert("초대 완료");
        // }
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
    let email = id.split("_");
    email = email[0] + "@" + email[1] + "." + email[2];
    $.ajax({
        url: "joining/" + squadNo + "/accept?email=" + email,
        type: "post"
    }).done(function (){
        $("div").remove('#' + id);
        invited();
    });
}

function refuse(id){
    let email = id.split("_");
    email = email[0] + "@" + email[1] + "." + email[2];
    $.ajax({
        url: "joining/" + squadNo + "/refuse?email=" + email,
        type: "delete"
    }).done(function (){
        $("div").remove('#' + id);
        invited();
    });
}

function out(id){
    let email = id.split("_");
    email = email[0] + "@" + email[1] + "." + email[2];
    if(confirm("방출하겠습니까?")){
        $.ajax({
            url: "joining/" + squadNo + "/refuse?email=" + email,
            type: "delete"
        }).done(function (){
            invited();
        });
    }
}

function show_edit(){
    $('#new_squad').toggle();
    $('#new_name').val($('#name').val());
    $('#new_contents').val($('#contents').val());
}

function update(){
    const data = {
        no : squadNo,
        host : "kevin@gmail.com",
        name : $('#new_name').val(),
        contents : $('#new_contents').val()};
    $.ajax({
        url: "squad/"+squadNo+"/update",
        type: "post",
        dataType: 'json',
        contentType : "application/json",
        data: JSON.stringify(data)
    }).done(function (){
        squad();
    });
}