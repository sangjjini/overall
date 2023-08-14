
$(window).on('load', function() {
    //회원정보 호출 필요

    $.ajax({
            url: "mypage/list",
            type: "get"
        }).done(function(response){
            response.forEach(over =>{
                $('.contents_menu').append(`<div id="overall">${over.overall}</div>
                    <div id="speed">${over.speed}</div>`);
            })

        });
});