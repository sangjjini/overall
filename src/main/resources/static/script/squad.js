let squadNo;

$(window).on('load', function () {
    // 스쿼드 번호 호출 필요
    squadNo = 2;
    $.ajax({
        url: "squad/" + squadNo,
        type: "get"
    }).done(function (response) {
        $('#name').val(response.name);
        $('#contents').val(response.contents);
    });

    $.ajax({
        url: "squad/" + squadNo + "/invited",
        type: "get"
    }).done(function (response) {
        $('#invited').empty();
        response.forEach(members => {
            $('#invited').append(
                `<div>
                    <p>${members.name}</p>
                    <button onclick="refuse()">탈퇴</button>
                </div>`
            );
        });
    });
});

function inviting() {
    $.ajax({
        url: "squad/" + squadNo + "/inviting",
        type: "get"
    }).done(function (response) {
        $('#inviting').empty();
        response.forEach(members => {
            $('#inviting').append(
                `<div>
                    <p>${members.name}</p>
                    <button onclick="refuse()">거절</button>
                </div>`
            );
        });
    });
}

function invite() {
    let email = $('#email').val();
    $.ajax({
        url: "squad/" + squadNo + "/invite?email=" + email,
        type: "post",
        dataType: "json"
    }).done(function (response) {
        let result = response.invite;
        if (result.equals("success")) {
            alert("초대 완료");
        }
    });
}

function leave() {
    $.ajax({
        url: "squad/" + squadNo + "/leave",
        type: "delete",
        dataType: "json"
    }).done(function (response) {
        let result = response.leave;
        if (result.equals("success")) {
            alert("탈퇴 완료");
        }
    });
}

function refuse() {
    $.ajax({
        url: "squad/" + squadNo + "/refuse",
        type: "delete"
    }).done(function (response) {
        if ((response.refuse).equals("success")) {
            alert("강퇴 완료");
        }
    });
}