let squadNo;
const log = $('#log').val();
let apply_cnt;
$(window).on('load', function (){
    const urlParams = new URL(location.href).searchParams;
    squadNo = urlParams.get('no');
    squad();
    applying();
    chat();
    // read();
    $('#invite_cnt').hide();
});

let name_squad;
let contents_squad;
let host;
function squad(){
    $.ajax({
        url: "squad/" + squadNo,
        type: "get"
    }).done(function (response){
        $('#name').val(response.name);
        $('#contents').val(response.contents);
        host = response.host;
        $('#host').val(host);
        $('#ovr').val(response.stats);
        name_squad = response.name;
        contents_squad = response.contents;
        if(log === host){
            $('#edit_btn').append(
                `<input type="hidden" id="host">
             <button class="squad_edit" onclick="update()">정보 변경</button>`
            );
        }
        invited();
    });
}

function invited(){
    $.ajax({
        url: "joining/" + squadNo + "/invited",
        type: "get"
    }).done(function (response){
        const invited = $('#invited');
        invited.empty();
        response.forEach(members => {
            if(members.email === host){
                invited.append(
                    `<div class="contents_member">
                        <div class="list_pos">
                            <input type="text" class="pos_input" id="pos_${members.code}" readonly>
                        </div>
                            <div class="list_over"><h1>${members.stats}</h1></div>
                        <div class="list_name">
                            <div class="name">${members.nickname}(방장)</div>
                            <div class="out_btn_wrap"></div>
                        </div>
                    </div>`
                );
            } else {
                if(log === host){
                    invited.append(
                        `<div class="contents_member">
                            <div class="list_pos">
                                <input type="text" class="pos_input" id="pos_${members.code}" readonly>
                            </div>
                            <div class="list_out">
                                    <div class="list_over"><h1>${members.stats}</h1></div>
                                <div class="list_name">
                                    <div class="name">${members.nickname}</div>
                                <div class="out_btn_wrap"><button onclick="out(this.id)" id="${members.code}" class="out_btn">방출</button></div>
                                </div>
                            </div>
                        </div>`
                    );
                } else{
                    invited.append(
                        `<div class="contents_member">
                            <div class="list_pos">
                                <input type="text" class="pos_input" id="pos_${members.code}" readonly>
                            </div>
                                <div class="list_over"><h1>${members.stats}</h1></div>
                            <div class="list_name">
                                <div class="name">${members.nickname}</div>
                                <div class="out_btn_wrap"></div>
                            </div>
                        </div>`
                    );
                }
            }
        });
        get_position();
    });
}

function show_invite(){
    $('#invite_list').show();
}

function close_invite(){
    $('#email').val("");
    $('#invite_list').hide();
    $('#error_invite').hide();
}

function inviting(){
    $.ajax({
        url: "joining/" + squadNo + "/inviting",
        type: "get"
    }).done(function (response){
        response.forEach(members => {
            $('#inviting').append(
                `<div id="${members.code}" class="inviting_list">
                    <div class="inviting_left">
                        <div class="inviting_num">${members.stats}</div>
                        <div class="inviting_name">${members.nickname}(${members.email})</div>
                    </div>
                    <div>
                        <button onclick="refuse(this.id)" id="${members.code}" 
                        class="answer_btn refuse_btn">X</button>
                    </div>
                    
                </div>`
            );
        });
    });
}

function applying(){
    $.ajax({
        url: "joining/" + squadNo + "/applying",
        type: "get"
    }).done(function (response){
        $('#inviting').empty();
        apply_cnt = 0;
        response.forEach(members => {
            $('#inviting').append(
                `<div id="${members.code}" class="inviting_list">
                    <div class="inviting_left">
                        <div class="inviting_num">${members.stats}</div>
                        <div class="inviting_name">${members.nickname}(${members.email})</div>
                    </div>
                    <div>
                        <button onclick="refuse(this.id)" id="${members.code}" 
                        class="answer_btn refuse_btn">X</button>
                        <button onclick="accept(this.id)" id="${members.code}" 
                        class="answer_btn accept_btn">V</button>
                    </div>
                </div>`
            );
            apply_cnt++;
        });
        const cnt = $('#invite_cnt');
        if(apply_cnt === 0){
            cnt.hide();
        }else{
            cnt.val(apply_cnt);
            cnt.show();
        }
        inviting();
    });
}

function invite(){
    let email = $('#email').val();
    const error = $('#error_invite');
    if(email !== ""){
        $.ajax({
            url: "joining/" + squadNo + "/invite?email=" + email,
            type: "post",
        }).done(function (response){
            const result = Object.values(response)[0];
            if(result === "fail"){
                error.val("존재하지 않는 회원입니다.");
                error.show();
            }else if(result === "already"){
                error.val("이미 가입신청이 완료된 회원입니다.");
                error.show();
            }else if(result === "stats"){
                error.val("OVERALL 입력이 필요한 회원입니다.");
                error.show();
            }else{
                error.hide();
                $('#email').val("");
                inviting();
            }
        });
    } else{
        error.val("이메일을 입력해주세요.");
        error.show();
    }
}

function leave(){
    if(confirm("탈퇴하겠습니까?")){
        $.ajax({
            url: "joining/" + squadNo + "/leave",
            type: "delete"
        }).done(function (){
           location.href = "squad/list"
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
        applying();
    });
}

function refuse(id){
    $.ajax({
        url: "joining/" + squadNo + "/refuse?code=" + id,
        type: "delete"
    }).done(function (){
        $("div").remove('#' + id);
        applying();
    });
}

function out(id){
    if(confirm("방출하시겠습니까?")){
        $.ajax({
            url: "joining/" + squadNo + "/refuse?code=" + id,
            type: "delete"
        }).done(function (){
            invited();
            squad();
        });
    }
}

function update(){
    let name = $('#name').val();
    let contents = $('#contents').val();
    const error = $('#error_name');
    if(name === ""){
        error.val("스쿼드 이름을 입력해주세요.");
        error.show();
    }else{
        if(name_squad === name && contents_squad === contents){
            error.val("변경된 내용이 없습니다.");
            error.show();
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
                    name_squad = $('#name').val();
                    contents_squad = $('#contents').val();
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
        $('#chat').empty();
        response.forEach(chat => {
            if(chat.email === log){
                $('#chat').append(
                    `<div class="chat_list" id="chat${chat.no}">
                        <div class="chat_name">
                            ${chat.nickname}
                            <button class="chat_delete refuse_btn" id="${chat.no}" onclick="chat_del(this.id)">X</button>
                        </div>
                        <div class="chat_contents">
                            ${chat.contents}
                            <div class="chat_date">${chat.createdAt}</div>
                        </div>
                    </div><br>`
                );
            } else {
                $('#chat').append(
                    `<div class="chat_list" id="chat${chat.no}">
                        <div class="chat_name">
                            ${chat.nickname}
                        </div>
                        <div class="chat_contents">
                            ${chat.contents}
                            <div class="chat_date">${chat.createdAt}</div>
                        </div>
                    </div><br>`
                );
            }
        });
        $('#chat').scrollTop($('#chat')[0].scrollHeight)
    });
}

function chat_del(id){
    $.ajax({
        url:"chat/"+id+"/delete",
        type:"delete"
    }).done(function (){
        $("div").remove('#chat' + id);
    })
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
    member_list();
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
            $('#area_'+positions[i]).css("background-image", "url(../images/pos_area.png)");
            $('#pos_'+code).val("");
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
        $('#area_'+pos).css("background-image", "url(../images/pos_area.png)");
        $('#pos_'+code).val("");
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
                const sel_pos = $('#sel_'+positions[i]);
                sel_pos.empty();
                sel_pos.append(
                    `<div class="pos_text">
                        ${response.nickname}
                     </div>
                    <button onclick="delete_pos(this.id, this.name)" id="${response.code}"
                     name="${positions[i]}" class="cancel_btn pos_delete">X</button>`
                );
                $('#area_'+positions[i]).css("background-image", "url(../images/sel_area.png)");
                let pos_name = "";
                if(positions[i] === "A"){
                    pos_name = "PIVO";
                }else if(positions[i] === "B" || positions[i] === "C"){
                    pos_name = "ALA";
                }else if(positions[i] === "D"){
                    pos_name = "FIXO";
                }else if(positions[i] === "E"){
                    pos_name = "GOLEIRO";
                }
                $('#pos_'+response.code).val(pos_name);
            }
        })
    }
}
