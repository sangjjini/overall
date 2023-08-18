// let emailNo = $('#homin').val();
let emailNo = "SIUUU@naver.com";
$(window).on('load', function () {
    //회원정보 호출 필요
    let sum = 0;

    //오버롤 호출
    $.ajax({
        url: "mypage/overallList",
        type: "get"
    }).done(function (response) {
            const age = response.age;
            const height = response.height;
            const weight = response.weight;
            const pos = response.pos;
            const leftfoot = response.leftfoot;
            const rightfoot = response.rightfoot;
            const physical = Math.round((height + weight) / (age / 10) + 30);
            const speed = response.speed;
            const speedstat = Math.round(100 / speed * 9);
            const pPercent = physical * 100 / 150;
            const sPercent = speedstat * 100 / 150;
            sum = Math.round(speedstat + physical);
        $('.position').append(`${pos}`);
        $('.left_foot h3').append(`${leftfoot}`);
        $('.right_foot h3').append(`${rightfoot}`);
            $('.stats_wrap').append(`<div id="stat" class="physical">
<h1>피지컬</h1>
<div class="physical_status">
<h2>${physical}</h2>
</div>
<div class="graph_wrap">
<div id="data_result" class="physical_data" data="${physical}">1
</div>
</div>
</div>
                    <div id="stat" class="speed">
                    <h1>스피드</h1>
                    <div class="speed_status"><h2>${speedstat}</h2></div>
                    <div class="graph_wrap">
                    <div id="data_result" class="speed_data" data="${speedstat}">2
                    </div>
                    </div>
                    </div>`);
            $('.physical_data').css("width", pPercent + "%");
            $('.speed_data').css("width", sPercent + "%");
        });

        // 닉네임호출
    $.ajax({
        url:"mypage/overallList/nickname",
        type:"get"
    }).done(function(response){
        const nick = response.nickname;
        $('.nickname').append(`${nick}`);
    });

        // 레이팅 호출
        $.ajax({
            url: "mypage/overallList",
            type: "get"
        }).done(function (response) {
                const rating = 5;
                const ratingstat = rating * 10 + 50;
                const rPercent = ratingstat * 100 / 150;
                const overall = Math.round((sum + ratingstat) / 3);
                const style = response.style;
                $('.overall_param').append(`<div id="param"><h1>${overall}</h1></div>`);
                $('.stats_wrap').append(`<div id="stat" class="rating">
<h1>유저평가</h1>
<div class="rating_status">
<h2>${ratingstat}</h2>
</div>
<div class="graph_wrap">
<div id="data_result" class="rating_data" data="${ratingstat}">3
</div>
</div></div>`);
                $('.rating_data').css("width", rPercent + "%");
                // $('.style_wrap').append(`<div id="style">#${style}</div>`);


        });
  //오버롤 종료




});
