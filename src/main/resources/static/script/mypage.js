$(window).on('load', function () {
    //회원정보 호출 필요
    let sum = 0;


    //오버롤 호출
    $.ajax({
        url: "/mypage/overallList",
        type: "get"
    }).done(function (response) {
        response.forEach(over => {
            const age = over.age;
            const height = over.height;
            const weight = over.weight;
            const physical = Math.round((height + weight) / (age / 10) + 30);
            const speed = over.speed;
            const speedstat = Math.round(100 / speed * 9);
            const pPercent = physical * 100 / 150;
            const sPercent = speedstat * 100 / 150;
            sum = Math.round(speedstat + physical);
            $('.stats_wrap').append(`<div id="stat" class="physical">
<div class="physical_status">
${physical}
</div>
<div class="graph_wrap">
<div id="data_result" class="physical_data" data="${physical}">1
</div>
</div>
</div>
                    <div id="stat" class="speed">
                    <div class="speed_status">${speedstat}</div>
                    <div class="graph_wrap">
                    <div id="data_result" class="speed_data" data="${speedstat}">2
                    </div>
                    </div>
                    </div>`);
            $('.physical_data').css("width", pPercent + "%");
            $('.speed_data').css("width", sPercent + "%");
        });

        // 레이팅 호출
        $.ajax({
            url: "/mypage/rating",
            type: "get"
        }).done(function (response) {
            response.forEach(rate => {
                const rating = rate.rating;
                const ratingstat = rating * 10 + 50;
                const rPercent = ratingstat * 100 / 150;
                const overall = Math.round((sum + ratingstat) / 3);
                $('.overall_param').append(`<div id="param"><h1>${overall}</h1></div>`);
                $('.stats_wrap').append(`<div id="stat" class="rating">
<div class="rating_status">
${ratingstat}
</div>
<div class="graph_wrap">
<div id="data_result" class="rating_data" data="${ratingstat}">3
</div>
</div></div>`);
                $('.rating_data').css("width", rPercent + "%");
                $('.style_wrap').append(`<div id="style">${rate.style}</div>`);

            })

        });
    });




});