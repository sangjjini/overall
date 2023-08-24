let title;
const log = $('#log').val();
let author;

$(window).on('load', function (){
    const urlParams = new URL(location.href).searchParams;
    no = urlParams.get('no');
    match();
    squads();
    ///squad_list();
});

function updateMatch(){
    const match_title = $('#title').val();
    const match_contents = $('#contents').val();
    const error = $('.error_title');

    if(match_title === ""){
        error.val("매치 제목을 입력해주세요.");
        error.show();
    }else{
        if(title === match_title && contents_squad === match_contents){
            alert("변경된 내용이 없습니다.")
        }else{
            let obj = {
                author:log,
                title: match_title,
                contents : match_contents
            };
            $.ajax({
                url:"/squad/match/"+ no + "/update",
                type:"put",
                dataType: "json",
                contentType : "application/json",
                data: JSON.stringify(obj)
            }).done(function (response){
                const result = Object.values(response)[0];
                console.log(result);
                if(result === "success"){
                    alert("변경이 완료되었습니다.");
                    error.hide();
                    window.location.href = "/squad/match?no=" + no
                }else{
                    alert("변경이 실패되었습니다.");
                }
            });
        }
    }
}

function deleteMatch(){
    // const log = $('#log').val();
    const author = $('#author').val();

    if(log === author){
        if(confirm("매치를 삭제하시겠습니까?")) {
            $.ajax({
                url: "/squad/match/" + no + "/delete",
                type:"delete",
            }).done(function (response){
                window.location.href = "/squad/matchList"
            });
        }
    }else{
        alert("방장만 가능합니다.")
    }
}

function leaveMatch(){
    let squadB = $('#squadB').val();
    let obj = {name:squadB}
    if(confirm("매치에서 퇴장하시겠습니까?")){
        $.ajax({
            url:"/squad/match/" + no + "/leave",
            type:"post",
            dataType: "json",
            contentType : "application/json",
            data: JSON.stringify(obj)
        }).done(function (response){
            if(response.leave === "fail"){
                alert("퇴장할 권한이 없습니다.");
                window.location.href = "/squad/matchList"
            }else {
                alert("퇴장하셨습니다.")
                window.location.href = "/squad/matchList"
            }
        });
    }
}

function resultMatch(button){
    let result = $(button).val()
    let winner;

    if(result === 'A'){
        winner = $("#squadA").val();
    }else if(result === 'B'){
        winner = $("#squadB").val();
    }else{
        winner = "Draw"
    }

    // const author = $('#author').val();
    let obj = {name:winner};
    if(log === author){
        $.ajax({
            url:"/squad/match/" + no + "/matchResult",
            type:"post",
            dataType: "json",
            contentType : "application/json",
            data: JSON.stringify(obj)
        }).done(function(response){
            const result = Object.values(response)[0];
            if(result !== "fail"){
                alert("경기결과가 저장되었습니다.")
                location.href = "/squad/matchList"
            }
        });
    }else{
        alert("방장만 가능합니다.");
    }

}

let match_title;
let match_contents;

function match(){
    $.ajax({
        url:"/squad/match/"+ no,
        type: "get"
    }).done(function (response){
        let match = response.match;
        let nickname = response.nickname;
        // let log = $('#log').val();
        let title = match.title;
        author = match.author;
        let contents = match.contents;
        let startAt = match.startAt.substring(0,16);
        let endAt = match.endAt;

        if(startAt.substring(0,10) === endAt.substring(0,10)){
            endAt = match.endAt.substring(11,16);
        }else{
            endAt = match.endAt.substring(0,16);
        }

        if(startAt.substring(11,13) < 12){
            startAt += ' AM';
        }else{
            startAt += ' PM';
        }

        if(endAt.substring(0,2) < 12){
            endAt += ' AM';
        }else{
            endAt += ' PM';
        }

        let update_btn = $('#update_btn');

        // if(log === "" || response.squadB === null){
        if(log === "") {
            $('#leave_btn').hide();
            $('.contents_list:last-child').hide();
            update_btn.text("매치 참가");
            update_btn.attr({onclick: "partInMatch()"})
        }else {
            if (log === match.author) {
                update_btn.text("정보 변경");
                update_btn.attr({onclick: "updateMatch()"})
                if(match.squadB === null){
                    $('.contents_list:last-child').hide();
                }
                // if(response.squadB === null){
                //     update_btn.hide();
                //     $('#leave_btn').text("매치 신청")
                //     $('#leave_btn').attr({onclick:"applyMatch()"})
                //     $('.contents_list:last-child').hide();
                // }
            } else{
                update_btn.hide()
                $('.contents_list:last-child').hide();
                if(match.squadB === null){
                    $('#leave_btn').text("매치 참가")
                    $('#leave_btn').attr({onclick:"modalAction()"})
                }
            }
        }

        $('#title').val(title);
        $('#contents').val(contents);
        $('#author').val(nickname);
        $('#squadA').val(match.squadA);
        $('#squadB').val(match.squadB);

        $('#time').text(startAt + " ~ " +endAt);
        $('#endAt').val(match.endAt);
        $('#host').val(match.author);
        match_title = match.title;
        match_contents = match.contents;

        let squadA = $('#squadA').val();
        let squadB = $('#squadB').val();

        if(squadB === null){
            squadB = "";
        }

        $('.contents_right').append(
            `<div id="date">${startAt} ~ ${endAt}</div>
             <div id="squadA">${squadA}</div>
             <div id="vs">vs</div>
             <div id="squadB">${squadB}</div>
            `
        )
    })
}
function partInMatch(){
    if(log === ''){
        alert('로그인 후 이용해주세요.')
        window.location.href="/login";
    }else {
        let squadB = $('#squads').val();
        console.log(squadB);
        let obj = {name: squadB}
        $.ajax({
            url: "/squad/match/" + no + "/partIn",
            type: "post",
            data: JSON.stringify(obj),
            contentType: 'application/json',
            dataType: 'json'
        }).done(function (response) {
            const result = Object.values(response)[0];
            if (result === "fail") {
                alert("이미 참가신청을 했거나 신청할 수 없는 매치입니다.")
                modalClose();
            } else {
                alert("신청이 완료되었습니다.")
                $('#squadB').val(squadB);
                location.href = "/squad/match?no=" + no;
            }
        });
    }
}
function modalAction(){
    if(log !== ''){
        $('#applyContainer').show();
    }else{
        alert('로그인 후 이용해주세요.')
        location.href="/login";
    }
}

function modalClose(){
    $('#applyContainer').hide();
}

function squads(){
    let email = $('#log').val();
    let obj = {"email":email}
    $.ajax({
        url:"/squad/match/mysquad",
        method: "post",
        data: JSON.stringify(obj),
        contentType: 'application/json',
        dataType:'json',
        success : (response) => {
            let list = response.list;
            let select = $('#squads');
            select.empty();
            select.append(
                '<option value="" selected>팀 선택</option>'
            );
            for(let i = 0; i < list.length; i++){
                let data = list[i];
                console.log("이게 출력이 : ", data)

                select.append(
                    '<option value="' + data + '">' + data + '</option>'
                )
            }
        },
        error : function(errorThrown) {
            console.log("실패");
            console.log(errorThrown.statusText);
        }
    })
}