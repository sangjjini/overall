let title;
const log = $('#log').val();
$(window).on('load', function (){
    const urlParams = new URL(location.href).searchParams;
    no = urlParams.get('no');
    match();
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
            alert("퇴장하셨습니다.")
            window.location.href = "/squad/matchList"
        });
    }
}

// function squad_list(){
//     $.ajax({
//         url: "/squad/match/" + no,
//         type: "get"
//     }).done(function(response){
//         let select = $('#select_squad')
//         select.empty();
//         select.append(
//              `<option value="${response.squadA}">${response.squadA}</option>
//               <option value="${response.squadB}">${response.squadB}</option>`
//
//         );
//     });
// }

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

    const author = $('#author').val();
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
        // let log = $('#log').val();
        let title = response.title;
        let author = response.author;
        let contents = response.contents;
        let squadA = response.squadA;
        let squadB = response.squadB;
        let startAt = response.startAt.substring(0,16);
        let endAt = response.endAt;
        if(startAt.substring(0,10) === endAt.substring(0,10)){
            endAt = response.endAt.substring(11,16);
        }else{
            endAt = response.endAt.substring(0,16);
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
            if (log === response.author) {
                update_btn.text("정보 변경");
                update_btn.attr({onclick: "updateMatch()"})
                if(response.squadB === null){
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
                if(response.squadB === null){
                    $('#leave_btn').text("매치 신청")
                    $('#leave_btn').attr({onclick:"partInMatch()"})
                }
            }
        }

        $('#title').val(title);
        $('#contents').val(contents);
        $('#author').val(author);
        $('#squadA').val(squadA);
        $('#squadB').val(squadB);

        $('#time').val(startAt + " ~ " +endAt);
        $('#endAt').val(response.endAt);
        $('#host').val(response.author);
        match_title = response.title;
        match_contents = response.contents;
        $('.contents_right').append(
            `<div id="squadA">${squadA}</div>
             <div id="vs">vs</div>
             <div id="squadB">${squadB}</div>
            `
        )
    })
}
function partInMatch(){
    if(log === ''){
        alert('로그인 후 ')
        window.location.href="/login";
    }else {
        $.ajax({
            url: "/squad/match/" + no + "/partIn",
            type: "post"
        }).done(function (response) {
            const result = Object.values(response)[0];
            if (result === "fail") {
                alert("이미 참가신청을 했거나 신청할 수 없는 매치입니다.")
            } else {
                alert("신청이 완료되었습니다.")
                window.location.href = "/squad/match?no=" + no;
            }
        });
    }
}
// function mySquad(){
//     const author = document.getElementById("author").value.split(":")[1];
//     let obj = {"email":author}
//     $.ajax({
//         url:"/squad/match/mysquad",
//         method: "post",
//         data: JSON.stringify(obj),
//         contentType: 'application/json',
//         dataType:'json',
//         success : (response) => {
//             let select = $('#squadA');
//             select.empty();
//             select.append(
//                 '<option value="" selected>팀 선택</option>'
//             );
//             for(let i = 0; i < response.length; i++){
//                 let data = response[i];
//                 console.log(data)
//
//                 select.append(
//                     '<option value="' + data + '">' + data + '</option>'
//                 )
//             }
//         },
//         error : function(errorThrown) {
//             console.log("실패");
//             console.log(errorThrown.statusText);
//         }
//     })
// }