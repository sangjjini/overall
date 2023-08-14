
$(window).on('load', function() {
    //회원정보 호출 필요
    let sum = 0;


    //오버롤 호출
    $.ajax({
            url: "/mypage/overallList",
            type: "get"
        }).done(function(response){
            response.forEach(over =>{
                const age = over.age;
                const height = over.height;
                const weight = over.weight;
                const physical = Math.round((height + weight) / (age / 10) + 30);
                const speed = over.speed;
                const speedstat = Math.round(100 / speed * 9);
                sum = Math.round(speedstat + physical);
                $('.stats_wrap').append(`<div id="stat" class="physical">${physical}</div>
                    <div id="stat" class="speed">${speedstat}</div>`);
            })

        });
    
    // 레이팅 호출
    $.ajax({
        url: "/mypage/rating",
        type: "get"
    }).done(function(response){
        response.forEach(rate =>{
            const rating = rate.rating;
            const ratingstat = rating * 10 + 50;
            const overall =Math.round((sum + ratingstat) / 3);
            $('.overall_param').append(`<div id="param"><h1>${overall}</h1></div>`)
            $('.stats_wrap').append(`<div id="stat" class="rating">${ratingstat}</div>`);
            $('.style_wrap').append(`<div id="style">${rate.style}</div>`)
        })

    });
});