let emailNo = $('#homin').val();
// let emailNo = "SIUUU@naver.com";
$(window).on('load', function () {
    //회원정보 호출 필요
    let sum = 0;

    //오버롤 호출
    $.ajax({
        url: "mypage/overallList",
        type: "get"
    }).done(function (response) {
            if(typeof response.pos !=="undefined") {
            const age = response.age;
            const height = response.height;
            const weight = response.weight;
            const pos = response.pos;
            const leftfoot = response.leftfoot;
            const rightfoot = response.rightfoot;
            const speed = response.speed;
            const rating = response.rating;
            const physical = Math.round((height + weight) / (age / 10) + 30);
            const speedstat = Math.round(100 / speed * 9);
            const pPercent = physical * 100 / 150;
            const sPercent = speedstat * 100 / 150;
            const rPercent = rating * 100 / 150;
            const overall = Math.round((speedstat + physical + rating) / 3);
        $('.position').append(`${pos}`);
        $('.left_foot h3').append(`${leftfoot}`);
        $('.right_foot h3').append(`${rightfoot}`);
        $('.style_wrap').append(`${response.playstyle}`);
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
                    
                    
</div>
                    </div>
<div id="stat" class="rating">
    <h1>유저점수</h1>
    <div class="rating_status">
        <h2>${rating}</h2>
    </div>
    <div class="graph_wrap">
        <div id="data_result" class="rating_data" data="${rating}">3
    </div>
</div>`);
            $('.physical_data').css("width", pPercent + "%");
            $('.speed_data').css("width", sPercent + "%");
            $('.rating_data').css("width", rPercent + "%");

            } else {
                $('.overall').css("display", "none");
                $('.mypage_contents').append(`<div class="alert_msg"><h1>좌측 오버롤 설정 메뉴를 통해 오버롤을 설정해 주세요</h1></div>`);
            }
        });

        // 닉네임호출
    $.ajax({
        url:"mypage/overallList/nickname",
        type:"get"
    }).done(function(response){
        const nick = response.nickname;
        const overall = response.stats;
        $('.nickname').append(`${nick}`);
        $('.mypage_contents .overall_param').append(`<div id="param"><h1>${overall}</h1></div>`);
    });

  //오버롤 종료




});
