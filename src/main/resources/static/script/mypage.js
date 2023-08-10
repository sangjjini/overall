
$(window).on('load', function() {
    //회원정보 호출 필요

    $.ajax({
            url: "mypage/list",
            type: "get"
        }).done(function(response){
            response.forEach(over =>{
                $('.contents').append(`<div id="overall">${over.overall}</div>`);
            })

        });
});