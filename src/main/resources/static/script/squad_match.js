// document.addEventListener("DOMContentLoaded", function() {
//     const squadInput = document.querySelector(".squad-input");
//     const resultElement = document.getElementById("results");
//
//     if (squadInput && resultElement) {
//         const inputElements = squadInput.querySelectorAll("input[type='text']");
//
//         inputElements.forEach(input => {
//             input.addEventListener("input", function() {
//                 const squadAValue = document.getElementById("squadA").value;
//                 const squadBValue = document.getElementById("squadB").value;
//
//                 //resultElement.textContent = `A팀: ${squadAValue}, B팀: ${squadBValue}`;
//                 $.ajax({
//                     url: "match/getMatch",
//                     method: "post",
//                     data:{
//                         "squadA":squadAValue,
//                         "squadB":squadBValue
//                     }
//                 }).done(function(response){
//                     console.log("typeof : ", typeof response);
//                     console.log(response.squadA);
//                     console.log(response.squadB);
//                 });
//             });
//         });
//     }
// });
function readMatch(ul){
    const match_no = $(ul).find('li[name="match_no"]').val();
    console.log(match_no);

    if(parseInt(match_no) >= 1){
        location.href = `matchRead?${match_no}`;
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
                let startAt = data.startAt;
                let endAt = data.endAt;
                dataContainer.append(
                    '<ul class="match_content" onclick="readMatch(this)">' +
                        '<li name="match_no" value="'+ data.no + '">' + data.no + '</li>' +
                        '<li>' + data.title + '</li>' +
                        '<li>' + data.making + '</li>' +
                        '<li>' + startAt.substring(0,16) + ' ~ ' + endAt.substring(0,16) + '</li>' +
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
    const st = document.getElementById("startAt").value.replace('T',' ');
    const et = document.getElementById("endAt").value.replace('T',' ');
    const squadA = document.getElementById("squadA").value;
    const squadB = document.getElementById("squadB").value;


    console.log("author : ", author);
    console.log("st : ", st);
    console.log("et : ", et);

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
        $.ajax({
            url:"match/making",
            type:"post",
            data:{
                "title":title,
                "contents":contents,
                "startAt":st,
                "endAt":et,
                "squadA":squadA,
                "squadB":squadB,
            }
        }).done(function(response){
            console.log("success");
            history.back();
        }).fail(function (error){
            console.log(JSON.stringify(error));
        });
    }
}



