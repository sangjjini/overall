let squadNo;

$(window).on('load', function (){
    const urlParams = new URL(location.href).searchParams;
    squadNo = urlParams.get('no');
    squad();
    invited();
    // 실시간 적용 필요
    chat();
    read();
    get_position();
    member_list();
});

let name_squad;
let contents_squad;
function squad(){
    $.ajax({
        url: "squad/" + squadNo,
        type: "get"
    }).done(function (response){
        $('#name').val(response.name);
        $('#contents').val(response.contents);
        $('#host').val(response.host);
        name_squad = response.name;
        contents_squad = response.contents;
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
                    `<div class="contents_member">
                        <div class="list_pos"></div>
                        <div class="list_name">${members.nickname}(방장)</div>
                        <div class="list_out">
                            <button onclick="out(this.id)" id="${members.code}" class="out_btn">방출</button>
                        </div>
                    </div>`
                );
            } else {
                $('#invited').append(
                    `<div class="contents_member">
                        <div class="list_pos"></div>
                        <div class="list_name">${members.nickname}</div>
                        <div class="list_out">
                            <button onclick="out(this.id)" id="${members.code}" class="out_btn">방출</button>
                        </div>
                    </div>`
                );
            }
        });
    });
}

function show_invite(){
    $('#invite_list').show();
    inviting();
}

function close_invite(){
    $('#email').val("");
    $('#invite_list').hide();
}

function inviting(){
    $.ajax({
        url: "joining/" + squadNo + "/inviting",
        type: "get"
    }).done(function (response){
        $('#inviting').empty();
        response.forEach(members => {
            $('#inviting').append(
                `<div id="${members.code}" class="inviting_list">
                    - ${members.nickname}(${members.email})
                    <button onclick="refuse(this.id)" id="${members.code}" 
                    class="answer_btn refuse_btn">X</button>
                    <button onclick="accept(this.id)" id="${members.code}" 
                    class="answer_btn accept_btn">V</button>
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
        const result = Object.values(response)[0];
        if(result === "fail"){
            alert("존재하지 않는 회원입니다.");
        }else if(result === "already"){
            alert("이미 가입신청이 완료된 회원입니다.");
        }else{
            $('#email').val('');
            inviting();
        }
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
    if(confirm("방출하시겠습니까?")){
        $.ajax({
            url: "joining/" + squadNo + "/refuse?code=" + id,
            type: "delete"
        }).done(function (){
            invited();
        });
    }
}

function update(){
    const name = $('#name').val();
    const contents = $('#contents').val();
    const error = $('.error_name');
    if(name === ""){
        error.val("스쿼드 이름을 입력해주세요.");
        error.show();
    }else{
        if(name_squad === name && contents_squad === contents){
            alert("변경된 내용이 없습니다.");
        }else{
            const data = {
                no : squadNo,
                name : name,
                contents : contents
            };
            $.ajax({
                url: "squad/"+squadNo+"/update",
                type: "post",
                dataType: "json",
                contentType : "application/json",
                data: JSON.stringify(data)
            }).done(function (response){
                const result = Object.values(response)[0];
                if(result === "success"){
                    alert("변경이 완료되었습니다.");
                    error.hide();
                } else {
                    error.val("중복된 스쿼드 이름입니다.");
                    error.show();
                }
            });
        }
    }
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
        data: JSON.stringify(data)
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

let position;
$('.position_add').click(function (){
    position = $(this).attr("id");
    $('#select_box').show();
    $('.shadow').show();
    close_invite();
})

function close_select(){
    $('#select_box').hide();
    $('.shadow').hide();
}

function member_list(){
    $.ajax({
        url: "joining/" + squadNo + "/invited",
        type: "get"
    }).done(function (response){
        $('#member_list').empty();
        response.forEach(members => {
            $('#member_list').append(
                `<div class="inviting_list">
                    ${members.nickname}<button onclick="change_pos(this.id)" 
                    id="${members.code}" class="answer_btn accept_btn">V</button>
                </div>`
            );
        });
    });
}

const positions = ["A", "B", "C", "D", "E"];
function change_pos(code){
    $.ajax({
        url:"joining/"+squadNo+"/position/change?code="+code+"&state="+position,
        type:"post"
    }).done(function (){
        close_select();
        for(let i=0; i<positions.length; i++){
            $('#'+positions[i]).show();
            $('#sel_'+positions[i]).empty();
        }
        get_position();
    });
}

function delete_pos(code, pos){
    $.ajax({
        url:"joining/"+squadNo+"/position/delete?code="+code,
        type:"post"
    }).done(function (){
        $('#sel_'+pos).empty();
        $('#'+pos).show();
    });
}

function get_position(){
    for(let i=0; i<positions.length; i++){
        $.ajax({
            url:"joining/"+squadNo+"/position?state="+positions[i],
            type:"get"
        }).done(function (response){
            if(response.nickname != null){
                $('#'+positions[i]).hide();
                $('#sel_'+positions[i]).empty();
                $('#sel_'+positions[i]).append(
                    `${response.nickname}<button onclick="delete_pos(this.id, this.name)" id="${response.code}" name="${positions[i]}">X</button>`
                );
            }
        })
    }
}
