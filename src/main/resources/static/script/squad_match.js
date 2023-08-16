function readMatch(ul){
    const match_no = $(ul).find('li[name="match_no"]').val();
    console.log(match_no);

    if(parseInt(match_no) >= 1){
        location.href = `matchRead?no=${match_no}`;
    }
}

$(document).ready(function(){
    $.ajax({
        "url":"match/list",
        "method":"GET",
        success : function(dataList){
            let dataContainer = $('#lines');

            for(let i = 0; i < dataList.length; i++){
                let data = dataList[i];
                let date = data.startAt.substring(0,10);
                let startAt = data.startAt.substring(11,16);
                let endAt = data.endAt;
                let deadline = data.deadline;
                console.log(deadline)

                if(date === endAt.substring(0,10)){
                    endAt = data.endAt.substring(11,16);
                }else{
                    endAt = data.endAt.substring(0,16);
                }

                if(deadline === '0'){
                    deadline = "모집중"
                }else if(deadline === '1'){
                    deadline = "경기 준비중"
                }else if(deadline === '2'){
                    deadline = "경기 종료"
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

                dataContainer.append(
                    '<ul class="match_content" onclick="readMatch(this)">' +
                        '<li name="match_no" value="'+ data.no + '">' + date + '<br>' + startAt + ' ~ ' + endAt + '</li>' +
                        '<li>' + data.title + '</li>' +
                        '<li>' + data.making + '</li>' +
                        '<li>' + deadline + '</li>' +
                    '</ul>'
                )
            }
        }
    })
});

function checkValue(){
    const title = document.getElementById("title").value;
    const author = document.getElementById("author").value.split(":")[1];
    const contents = document.getElementById("contents").value;
    // const st = document.getElementById("startAt").value;
    // const et = document.getElementById("endAt").value;
    const st = document.getElementById("startAt").value.replace('T',' ');
    const et = document.getElementById("endAt").value.replace('T',' ');
    const squadA = document.getElementById("squadA").value;
    const squadB = document.getElementById("squadB").value;

    console.log("title : ",title);
    console.log("author : ",author);
    console.log("contents : ",contents);
    console.log("st : ",st);
    console.log("et : ",et);
    console.log("squadA : ",squadA);
    console.log("squadB : ",squadB);

    let chk = true;

    if(author === ''){
        alert("로그인 후 이용해주세요.");
        chk = false;
        window.location.replace("/login");
    }

    if(title === ''){
        chk = false;
    }

    if(contents === ''){
        chk = false;
    }

    if(chk === true){
            // $.ajax({
            //     url: "match/making",
            //     type: "post",
            //     data: {
            //         "title":"테스트 매치",
            //         "contents":"테스트 매치 내용",
            //         "author":"kevin@gmail.com",
            //         "startAt": "2023-08-16 16:33",
            //         "endAt":"2023-08-16 18:33",
            //         "squadA": "스쿼드2"
            //     }
            // }).done(function (response) {
            //     console.log("success");
            //     history.back();
            // }).fail(function (error) {
            //     console.log(JSON.stringify(error));
            // });
        let obj = {
            "title":title,
            "contents":contents,
            "author":author,
            "startAt": st,
            "endAt":et,
            "squadA": squadA,
            "squadB": squadB
        }

        $.ajax({
            url: 'match/making',
            type: 'POST',
            data: JSON.stringify(obj),
            contentType: 'application/json',
            dataType:'json',
            success : function (data){
                console.log('success');
                window.location.replace("/squad/matchList");
            },
            error : function(errorThrown) {
                console.log("실패");
                console.log(errorThrown.statusText);
            }
        })
    }
}




