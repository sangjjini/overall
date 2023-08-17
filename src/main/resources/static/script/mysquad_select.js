$(document).ready(function(){
    const author = document.getElementById("author").value.split(":")[1];
    let obj = {"email":author}
    $.ajax({
        url:"/squad/match/mysquad",
        method: "post",
        data: JSON.stringify(obj),
        contentType: 'application/json',
        dataType:'json',
        success : (response) => {
            let select = $('#squads');
            select.append(
                '<option value="" selected>자신이 속한 팀 선택</option>'
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
});