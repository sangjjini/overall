let author;
const log = $('#log').val();
$('#match_title').on('change', e => {
    if($('#match_title').val() !== "") {
        $('.error_title').hide();
    }
});

$('#squadA').on('change', e => {
    if($('#squadA').val() !== "") {
        $('.error_squadA').hide();
    }
});

$('#startAt').on('change', e => {
    if($('#startAt').val() !== "") {
        $('.error_startAt').hide();
    }
});

$('#endAt').on('change', e => {
    if($('#endAt').val() !== "") {
        $('.error_endAt').hide();
    }
});

$(document).ready(function(){
    $('.filter_input').on('keyup', function(key){
        if(key.keyCode === 13){
            search();
        }
    })
});

if(log === ''){
    $(".division_line").hide();
    $(".list_title").hide();
}
function test(){
    const option = $('input[name=filter_sel]:checked').val();
    console.log(option);
}
$(window).on('load', function(){
    // $('#applyContainer').hide();
    my_match();
    my_partIn_match();
    all_match();
});

function search(){
    let keyword = $('#search').val();
    // console.log(keyword);
    all_match();
}
function show_make(){
    if(log !== '') {
        mySquad();
        $('#show_make').show();
    }else{
        alert("로그인 후 이용해주세요.")
        location.href = "/login";
    }
}

function close_make(){
    $('#show_make').hide();
    $('#squad_name').val("");
    $('.error_name').hide();
}

function matchMake(){
    if(log === ''){
        alert("로그인 후 이용해주세요.")
        window.location.href="/login";
    }else {
        let title = $('#match_title').val();
        let squadA = $('#squadA').val().split("(")[0];
        let startAt = $('#startAt').val();
        let endAt = $('#endAt').val();
        let error_title = $('.error_title');
        let error_squad = $('.error_squadA');
        let error_startAt = $('.error_startAt');
        let error_endAt = $('.error_endAt');

        let chk = true;
        if (title === "") {
            error_title.show();
            error_title.val("이름을 입력해주세요.");
            chk = false;
        }else if(squadA === ""){
            error_squad.show();
            error_squad.val("팀을 선택해주세요.");
            chk = false;
        }else if(startAt === ""){
            error_startAt.show();
            error_startAt.val("시작시간을 입력해주세요.")
            chk = false;
        }else if(endAt === ""){
            error_endAt.show();
            error_endAt.val("종료시간을 입력해주세요.")
            chk = false;
        }

        if(chk){
            $.ajax({
                url: "/squad/match/make?title=" + title + "&squadA=" + squadA + "&startAt=" + startAt + "&endAt=" + endAt,
                type: "post"
            }).done(function (response) {
                const result = Object.values(response)[0];
                if (result === "fail") {
                    console.log(result);
                } else {
                    console.log("success");
                    location.href = "/squad/match?no=" + result;
                }
            });
        }
    }
}

function my_match(){
    $.ajax({
        url:"/squad/match/myMakingList",
        type:"get"
    }).done(function(response) {
       response.forEach(match => {
           $('#my_match').append(
                `<a href="/squad/match?no=${match.no}">- ${match.title}</a>`
           );
           $('#author').val(match.author);
       })
    });
}
function my_partIn_match(){
    $.ajax({
        url:"/squad/match/partInList",
        type:"get"
    }).done(function(response){
        for(let i = 0; i < response.length; i++){
            let row = response[i];
            for(let j = 0; j < row.length; j++){
                console.log(row[j].title);
                $('#apply_list').append(
                    `<a href="/squad/match?no=${row[j].no}">- ${row[j].title}</a>`
                );
            }
        }
    });
}
function all_match(){
    const option = $('input[name=filter_sel]:checked').val();
    let keyword = $('#search').val();
    let url = "/squad/match/list?";

    if(option !== '' && keyword !== ''){
        url += "sort=" + option + "&keyword=" + keyword;
    }else if(keyword === ''){
        url += "sort=" + option;
    }else if(option === ''){
        url += "keyword=" + keyword;
    }else{
        url = "/squad/match/list";
    }

    let obj = {sort:option};
    $.ajax({
        url:url,
        type:"get"
    }).done(function (response) {
        let no = 1;
        let list = response.list
        let stats = response.stats;
        let statArr = new Array(stats.length);
        let i = 0;
        stats.forEach(stat => {
           statArr[i] = stats[i];
           i++;
        });

        $('#lines').empty();
        i = 0;
        list.forEach(match=>{
            let dates = new Date();
            let currentYear = dates.getFullYear();
            let currentMonth = "0" + (dates.getMonth()+1);
            let currentDate = dates.getDate();
            let now = currentYear + "-" + currentMonth + "-" + currentDate;
            let date = match.startAt.substring(0,10);
            let startAt = match.startAt.substring(11,16);
            let endAt = match.endAt;
            let deadline = match.deadline;

            if(date === endAt.substring(0,10)){
                endAt = match.endAt.substring(11,16);
            }else{
                endAt = match.endAt.substring(0,16);
            }

            if(new Date(date).getDay() === 0){
                date += "(일)"
            }else if(new Date(date).getDay() === 1){
                date += "(월)"
            }else if(new Date(date).getDay() === 2){
                date += "(화)"
            }else if(new Date(date).getDay() === 3){
                date += "(수)"
            }else if(new Date(date).getDay() === 4){
                date += "(목)"
            }else if(new Date(date).getDay() === 5){
                date += "(금)"
            }else if(new Date(date).getDay() === 6){
                date += "(토)"
            }

            // if(now <= date && (deadline === 'R')) {
            if(now <= date && (deadline === 'R' || deadline === 'F')) {
                let res = date + " " + startAt + " ~ " + endAt
                $('#lines').append(
                    `<div class="bar" onclick="readMatch(this)">
                        <input type="hidden" id="bar_no" value="${match.no}">
                        <div class="bar_date">${res}</div>
                        <div class="bar_squad">${match.squadA}(${statArr[i]})</div>
                        <div class="bar_title">${match.title}</div>
                        <div class="bar_content">${match.contents}</div>
                        <div class="bar_join">
<!--                            <button onclick="partIn(this.id)" id="${match.no}" class="join_btn">+</button>-->
                            <button onclick="event.stopPropagation(); modalAction(this.id)" id="${match.no}" class="join_btn">+</button>
                        </div>
                    </div>`
                );
            }
            i++;
        });
    })
}
// function input_sort(id){
//     $('#sort').val(id);
//     console.log(id)
// }
function readMatch(div){
    const no = $(div).find('input').val()
    //const no = $('#bar_no').val();
    window.location.href= '/squad/match?no=' + no;

}
function mySquad(){
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
            let ovr_list = response.overallList;
            let ovrArr = new Array(ovr_list.length);
            let cnt = response.squadCnt;
            let i = 0;
            ovr_list.forEach(stat => {
                ovrArr[i] = ovr_list[i];
                i++;
            });
            console.log("1. 스쿼드 수 : ", cnt);
            if(cnt > 0) {
                let select = $('#squadA');
                select.empty();
                select.append(
                    '<option value="" selected>팀 선택</option>'
                );
                for (let i = 0; i < list.length; i++) {
                    let name = list[i];
                    let ovr = ovrArr[i];
                    let data = name+`(${ovr})`;
                    console.log(data);
                    select.append(
                        '<option value="' + data + '">' + data + '</option>'
                    )
                }
            }else if(log !== '' && cnt === 0){
                alert('스쿼드 생성 또는 가입 후에 이용해주세요.')
                location.href = "/squad/list";
            }
        },
        error : function(errorThrown) {
            console.log("실패");
            console.log(errorThrown.statusText);
        }
    })
}
function partInMatch(){
    let no = $('#no_temp').val();
    let squadB = $('#squadB').val().split("(")[0];

    if(squadB !== '') {
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
                alert("이미 참가신청을 했거나 참가할 수 없는 매치입니다.")
            } else {
                alert("신청이 완료되었습니다.")
                window.location.href = "/squad/match?no=" + no;
            }
        });
    }else{
        alert('팀을 선택해주세요.')
    }
}
function modalAction(no){
    squads();
    if(log !== ''){
        $('#applyContainer').show();
        $('#no_temp').val(no);
    }else{
        alert('로그인 후 이용해주세요.')
        location.href="/login";
    }
}

function modalClose(){
    $('#applyContainer').hide();
    $('#no_temp').val("");
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
            let ovr_list = response.overallList;
            let ovrArr = new Array(ovr_list.length);
            let squadCnt = response.squadCnt;
            let i = 0;
            ovr_list.forEach(stat => {
                ovrArr[i] = ovr_list[i];
                i++;
            });
            console.log("2. 스쿼드 수 : ", squadCnt);
            if(squadCnt > 0) {
                let select = $('#squadB');
                select.empty();
                select.append(
                    '<option value="" selected>팀 선택</option>'
                );
                for (let i = 0; i < list.length; i++) {
                    let name = list[i];
                    let ovr = ovrArr[i];
                    let data = name+`(${ovr})`;
                    console.log("이게 출력이 : ", data)

                    select.append(
                        '<option value="' + data + '">' + data + '</option>'
                    )
                }
            }else if(log !== '' && squadCnt === 0){
                alert('스쿼드 생성 또는 가입 후에 이용해주세요.')
                location.href = "/squad/list";
            }
        },
        error : function(errorThrown) {
            console.log("실패");
            console.log(errorThrown.statusText);
        }
    })
}