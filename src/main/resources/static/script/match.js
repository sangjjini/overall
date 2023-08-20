let title;

$(window).on('load', function (){
    const urlParams = new URL(location.href).searchParams;
    no = urlParams.get('no');
    match();
});

function updateMatch(){
    const match_title = $('#title').val();
    const match_contents = $('#contents').val();
    const error = $('.error_name');
    if(match_title === ""){
        error.val("매치 제목을 입력해주세요.");
        error.show();
    }else{
        if(title === match_title && contents_squad === match_contents){
            $.ajax({
                url:"/squad/match"+ no + "/update",
                type:"post"

            });
        }else{

        }
    }
}

function deleteMatch(){
    const log = $('#log').val();
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
    }
}
let match_title;
let match_contents;

function match(){
    $.ajax({
        url:"/squad/match/"+ no,
        type: "get"
    }).done(function (response){
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

        $('#title').val(response.title);
        $('#contents').val(response.contents);
        $('#author').val(response.author);
        $('#squadA').val(response.squadA);
        $('#squadB').val(response.squadB);
        //
        // if($('#squadB').val() !== ""){
        //     $('#squadB').attr('readonly','readonly')
        // }

        $('#time').val(startAt + " ~ " +endAt);
        $('#endAt').val(response.endAt);
        $('#host').val(response.author);
        match_title = response.title;
        match_contents = response.contents;
    })
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