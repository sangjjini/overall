const url = new URL(window.location.href);
const urlParam = url.searchParams;
let no = urlParam.get('no');

$(document).ready(function(){
    console.log("no : ",no);

    $.ajax({
        url:"match/"+no,
        method:"GET",
        success : function(response){
            let log = $('#log_temp').val();
            let title = response.title;
            let author = response.author;
            let st = response.startAt.substring(0,16);
            let et = response.endAt;
            if(st.substring(0,10) === et.substring(0,10)){
                et = response.endAt.substring(11,16);
            }else{
                et = response.endAt.substring(0,16);
            }
            let deadline = String.fromCodePoint(response.deadline);
            let squadA = response.squadA;
            let squadB = response.squadB;
            let squadA_logo = response.squadA_logo;
            let squadB_logo = response.squadB_logo;
            let email = response.email;
            let squadB_host = response.squadB_host;

            if(squadA !== undefined){
                if(squadA_logo === undefined){
                    squadA_logo = "/images/real_madrid.png"
                    $('#squadA_logo > img').attr({src:squadA_logo});
                }else{
                    $('#squadA_logo > img').attr({src:squadA_logo});
                }
            }
            else{
                $('#squadB_logo > img').hide();
            }

            if(squadB !== undefined) {
                if (squadB_logo === undefined) {
                    squadB_logo = "/images/barcelona.png"
                    $('#squadB_logo > img').attr({src:squadB_logo});
                }else{
                    $('#squadB_logo > img').attr({src:squadB_logo});
                }
            }
            else{
                $('#squadB_logo > img').hide();
            }

            if(st.substring(11,13) < 12){
                st += ' AM';
            }else{
                st += ' PM';
            }

            if(et.substring(0,2) < 12){
                et += ' AM';
            }else{
                et += ' PM';
            }

            if(deadline === '0'){
                deadline = "모집중"
            }else if(deadline === '1'){
                deadline = "경기 준비중"
            }else if(deadline === '2'){
                deadline = "경기 종료"
            }



            $('#title').text(title);
            $('#schedule > span').text("Time : " + st + " ~ " + et);
            $('#author > span').text("Author : " + author);
            $('#deadline > span').text("Status : " + deadline);
            $('#squadA_name > h1').text(squadA);
            $('#squadB_name > h1').text(squadB);
            // $('#squadA_logo > img').attr({src:squadA_logo});
            // $('#squadB_logo > img').attr({src:squadB_logo});


            console.log(log)
            console.log(email)
            console.log(deadline)
            if(log === email){
                $('#match_btn').attr({value:"매치 삭제"});
                $('#match_btn').attr({onclick:"deleteMatch()"});

            }else if(deadline === '모집중'){
                $('.match_btn_container').attr({id:""})
                $('#match_btn').attr({value:"신청 하기"});
                $('#match_btn').attr({onclick:"mySquadlistModal()"});

            }else if(deadline === '경기 준비중' && squadB_host === log){
                $('#match_btn').attr({value:"참가 취소"});
                $('#match_btn').attr({onclick:"leaveMatch()"});
            }
        }
    });
});
function deleteMatch(){
    $.ajax({
        url:"/squad/match/" + no + "/delete",
        method: "delete",
        success: (response) => {
            alert("경기가 삭제되었습니다.")
            window.location.replace("/squad/matchList");
        },
        error : function(errorThrown) {
            console.log("실패");
            console.log(errorThrown.statusText);
        }
    });
}
function leaveMatch(){
    $.ajax({
        url:"/squad/match/" + no + "/leave",
        method: "delete",
        success: (response) => {
            alert("참가신청이 취소되었습니다.")
            window.location.replace("/squad/matchList");
        },
        error : function(errorThrown) {
            console.log("실패");
            console.log(errorThrown.statusText);
        }
    });
}
function mySquadlistModal(){
    let log = $('#log_temp').val();
    let obj = {"email" : log};
    $.ajax({
        url:"/squad/match/mysquad",
        method: "post",
        data: JSON.stringify(obj),
        contentType: 'application/json',
        dataType:'json',
        success: (response) => {
            $('#squad_list').empty();
            for(let i = 0; i < response.length; i++){
                let data = response[i]
                $('#lines').append(
                    '<ul class="list_content" onclick="applyMatch(this)">'+
                    '<li name="squad_name" value="' + data + '">' + data + '</li>' +
                    '<li></li>'+
                    '</ul>'
                )
            }
            //window.location.replace("/squad/matchRead?no=" + no);
        },
        error : function(errorThrown) {
            console.log("실패");
            console.log(errorThrown.statusText);
        }
    });
}
function applyMatch(ul){
    // const select_squad = .val();
    // console.log(select_squad)
    // console.log(ul);
//     let log = $('#log_temp').val();
//     let obj = {"log" : log};
//     $.ajax({
//         url:"/squad/match/" + no + "/apply",
//         method: "post",
//         data: JSON.stringify(obj),
//         contentType: 'application/json',
//         dataType:'json',
//         success: (response) => {
//             $('#squad_list').empty();
//             for(let i = 0; i < response.length; i++){
//                 let data = response[i]
//                 $('#lines').append(
//                     '<ul class="list_content">'+
//                     '<li>' + data + '</li>' +
//                     '<li></li>'+
//                     '</ul>'
//                 )
//             }
//             //window.location.replace("/squad/matchRead?no=" + no);
//
//         },
//         error : function(errorThrown) {
//             console.log("실패");
//             console.log(errorThrown.statusText);
//         }
//     });
}
let match_btn = document.getElementById('match_btn');
let modalCloseButton = document.getElementById('modalCloseButton');
let modal = document.getElementById('applyContainer')
console.log("match_btn : " + match_btn)
console.log("modalCloseButton : " + modalCloseButton)
match_btn.addEventListener('click', () => {
    modal.classList.remove('hidden');
});
modalCloseButton.addEventListener('click', () => {
    modal.classList.add('hidden');
});

