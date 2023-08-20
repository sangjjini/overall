const url = new URL(window.location.href);
const urlParam = url.searchParams;
let no = urlParam.get('no');

function listBack(){
    window.location.href = "/squad/matchList"
}


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
                $('#match_btn').attr({onclick:"mySquadListModal()"});

                // let match_btn = document.getElementById('match_btn');
                // let modalCloseButton = document.getElementById('modalCloseButton');
                // let modal = document.getElementById('applyContainer')
                // console.log("match_btn : " + match_btn)
                // console.log("modalCloseButton : " + modalCloseButton)
                // match_btn.addEventListener('click', () => {
                //     modal.classList.remove('hidden');
                // });
                // modalCloseButton.addEventListener('click', () => {
                //     modal.classList.add('hidden');
                // });

            }else if(deadline === '경기 준비중' && squadB_host === log){
                console.log(squadB_host)
                console.log(log)
                $('#match_btn').attr({value:"참가 취소"});
                $('#match_btn').attr({onclick:"leaveMatch()"});
            }else{
                $('#match_btn').attr({value:"신청 마감"});
                $('#match_btn').attr({disabled:"disabled"});
                // const target = document.getElementById('match_btn');
                // target.disabled = true;
            }
        }
    });
});
function deleteMatch(){
    $.ajax({
        url:"/squad/match/" + no + "/delete",
        method: "delete",
        success: (response) => {
            console.log(response.delete);
            if (response.delete === "success") {
                alert("경기가 삭제되었습니다.")
                window.location.replace("/squad/matchList");
            }else{
                alert("삭제에 실패하였습니다.")
            }
        },
        error : function(errorThrown) {
            console.log("실패");
            console.log(errorThrown.statusText);
        }
    });
}
function leaveMatch(){
    let name = $('#squadB_name > h1').text();
    $.ajax({
        url:"/squad/match/" + no + "/leave",
        method: "post",
        data:JSON.stringify({"name":name}),
        contentType: 'application/json',
        dataType:'json',
        success: (response) => {
            alert("참가신청이 취소되었습니다.")
            window.location.replace("/squad/matchList");
        },
        error:function(request,status,error){
            console.log("실패")
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}
function mySquadListModal(){
    let log = $('#log_temp').val();
    let obj = {"email" : log};
    $.ajax({
        url:"/squad/match/mysquad",
        method: "post",
        data: JSON.stringify(obj),
        contentType: 'application/json',
        dataType:'json',
        success : (response) => {
            let select = $('#squads');
            select.empty();
            select.append(
                '<option value="" selected>참가팀 선택</option>'
            );
            for(let i = 0; i < response.length; i++){
                let data = response[i];
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

function applyMatch(){
    let squad_name = $('#squads').val();
    let host = $('#squadA_name > h1').text();
    console.log(squad_name);
    console.log(host);

    if(squad_name === host){
        alert("이미 참가중인 팀입니다.")
        return false;
    }else if(squad_name === ''){
        alert("팀을 선택해주세요.")
        return false;
    }

    // console.log(ul);
//     let log = $('#log_temp').val();
    let obj = {"applySquad" : squad_name,"host":host};
    $.ajax({
        url:"/squad/match/" + no + "/apply",
        method: "post",
        data: JSON.stringify(obj),
        contentType: 'application/json',
        dataType:'json',
        success: (response) => {
            console.log(response.apply);
            alert("참가 신청이 완료되었습니다.");
            window.location.replace("/squad/matchRead?no=" + no);
        },
        error : function(errorThrown) {
            alert("신청 실패");
            console.log(errorThrown.statusText);
        }
    });
}


