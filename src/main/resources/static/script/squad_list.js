$(window).on('load', function (){
    my_squad();
    all_squad();
});

function show_make(){
    $('#show_make').show();
}

function close_make(){
    $('#show_make').hide();
    $('#squad_name').val("");
    $('.error_name').hide();
}

function squadMake(){
    const name = $('#squad_name').val();
    const error = $('.error_name');
    if(name === ""){
        error.show();
        error.val("이름을 입력해주세요.");
    } else{
        $.ajax({
            url: "/squad/make?name="+name,
            type: "post",
        }).done(function (response){
            const result = Object.values(response)[0];
            if(result === "fail"){
                error.show();
                error.val("중복된 스쿼드 이름입니다.");
            }else{
                location.href="/squad?no="+result;
            }
        });
    }
}

function my_squad(){
    $.ajax({
        url: "/squad/my",
        type: "get"
    }).done(function (response){
        response.forEach(squad => {
            $('#my_squad').append(
                `<a href="/squad?no=${squad.no}">- ${squad.name}</a>`
            );
        })
    })
}

function all_squad(){
    $.ajax({
        url: "/squad/all",
        type: "get"
    }).done(function (response){
        let num = 1;
        response.forEach(squad => {
            $('#squad_list').append(
                `<div class="bar">
                    <div class="bar_name">${squad.name}</div>
                    <div class="bar_over">${squad.stats}</div>
                    <div class="bar_content">${squad.contents}</div>
                    <div class="bar_join">
                        <button onclick="join(this.id)" id="${squad.no}" class="join_btn">+</button>
                    </div>
                </div>`
            );
            num++;
        })
    })
}

function join(no){
    $.ajax({
        url: "/joining/" + no + "/apply",
        type: "post",
    }).done(function (response){
        const result = Object.values(response)[0];
        if(result === "already") {
            alert("가입/신청이 완료된 스쿼드입니다.");
        }else{
            alert("신청이 완료되었습니다.");
        }
    });
}