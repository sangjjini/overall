let author;
$(window).on('load', function(){
    my_match();
    mySquad();
    all_match();
});

function show_make(){
    $('#show_make').show();
}

function close_make(){
    $('#show_make').hide();
    $('#squad_name').val("");
    $('.error_name').hide();
}

function matchMake(){
    const title = $('#match_title').val();
    const squadA = $('#squadA').val();
    const startAt = $('#startAt').val();
    const endAt = $('#endAt').val();
    const error = $('.error_title');

    if(title === ""){
        error.show();
        error.val("이름을 입력해주세요.");
    }else{
        $.ajax({
            url:"/squad/match/make?title="+title+"&squadA="+squadA+"&startAt="+startAt+"&endAt="+endAt,
            type:"post"
        }).done(function(response){
            const result = Object.values(response)[0];
            if(result === "fail"){
                console.log(result);
            }else {
                console.log("success");
                location.href = "/squad/match?no=" + result;
            }
        });
    }
}

function my_match(){
    $.ajax({
        url:"/squad/match/my",
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

function all_match(){
    $.ajax({
       url:"/squad/match/list",
       type:"get"
    }).done(function (response) {
        let no = 1;
        response.forEach(match=>{
            let dates = new Date();
            let currentYear = dates.getFullYear();
            let currentMonth = "0" + (dates.getMonth()+1);
            let currentDate = dates.getDate();
            let now = currentYear + "-" + currentMonth + "-" + currentDate;
            let date = match.startAt.substring(0,10);
            let startAt = match.startAt.substring(11,16);
            let endAt = match.endAt;

            console.log(now <= date);

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

            if(now <= date) {
                $('#lines').append(
                    `<div class="bar">   
                        <div class="bar_date" onclick="readMatch(this)">` + date + ` ` + startAt + ` ~ ` + endAt + `
                        <input type="hidden" id="bar_no" value="${match.no}"></div>
                        <div class="bar_title">${match.title}</div>
                        <div class="bar_content">${match.contents}</div>
                        <div class="bar_join">
                            <button onclick="apply(this.id)" id="${match.no}" class="join_btn">+</button>
    <!--                        <button onclick="modalAction()" id="${match.no}" class="join_btn">+</button>-->
                        </div>
                    </div>`
                );
                no++;
            }
        });
    })
}
function readMatch(div){
    const no = $(div).find('input').val()
    console.log(no);

    if(parseInt(no) >= 1){
        location.href = `/squad/match?no=` + no;
    }
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
            let select = $('#squadA');
            select.empty();
            select.append(
                '<option value="" selected>팀 선택</option>'
            );
            for(let i = 0; i < response.length; i++){
                let data = response[i];
                console.log(data)

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
function apply(no){
    $.ajax({
        url:"/squad/match/"+no+"/apply",
        type:"post"
    }).done(function(response){
       const result = Object.values(response)[0];
       if(result === "fail") {
           alert("이미 참가신청을 했거나 신청할 수 없는 매치입니다.")
       }else {
           alert("신청이 완료되었습니다.")
           window.location.href = "/squad/match?no=" + no;
       }
    });
}
function modalAction(){
    $('#applyContainer').show();
}

function modalClose(){
    $('#applyContainer').hide();
}
