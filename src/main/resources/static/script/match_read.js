$(document).ready(function(){
    const url = new URL(window.location.href);
    const urlParam = url.searchParams;
    let no = urlParam.get('no');

    console.log("no : ",no);

    $.ajax({
        url:"match/"+no,
        method:"GET",
        success : function(response){
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

            console.log(title);
            console.log(author);
            console.log(st);
            console.log(et.substring(0,2));
            console.log(squadA);
            console.log(squadB);
            console.log(squadA_logo);
            console.log(squadB_logo);
            console.log(deadline);

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
            $('#squadA_logo > img').attr({src:squadA_logo});
            $('#squadB_logo > img').attr({src:squadB_logo});

        }
    });
});

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