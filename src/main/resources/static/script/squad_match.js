document.addEventListener("DOMContentLoaded", function() {
    const squadInput = document.querySelector(".squad-input");
    const resultElement = document.getElementById("results");

    if (squadInput && resultElement) {
        const inputElements = squadInput.querySelectorAll("input[type='text']");

        inputElements.forEach(input => {
            input.addEventListener("input", function() {
                const squadAValue = document.getElementById("squadA").value;
                const squadBValue = document.getElementById("squadB").value;

                //resultElement.textContent = `A팀: ${squadAValue}, B팀: ${squadBValue}`;
                $.ajax({
                    url: "match/getMatch",
                    method: "post",
                    data:{
                        "squadA":squadAValue,
                        "squadB":squadBValue
                    }
                }).done(function(response){
                    console.log("typeof : ", typeof response);
                    console.log(response.squadA);
                    console.log(response.squadB);
                });
            });
        });
    }
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