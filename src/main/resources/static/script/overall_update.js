let emailNo = $('#homin').val();
 // let emailNo = "SIUUU@naver.com";

$(window).on('load', function (){
    $.ajax({
        url:"overallUpdate/"+emailNo,
        type: "get"
    }).done(function(response){
        if(response.email!=="none"){
        $('#speed').val(response.speed);
        $('#age').val(response.age);
        $('#height').val(response.height);
        $('#weight').val(response.weight);
        $('#leftfoot').val(response.leftfoot);
        $('#rightfoot').val(response.rightfoot);
        $('#pos').val(response.pos);
        }
    });
});

function overallupdate() {
    const data = {
        speed : $('#speed').val(),
        age : $('#age').val(),
        height : $('#height').val(),
        weight : $('#weight').val(),
        leftfoot : $('#leftfoot').val(),
        rightfoot : $('#rightfoot').val(),
        pos : $('#pos').val()
    }
    $.ajax({
        url: "overallUpdate/update",
        type: "post",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(function(response){

        alert("설정완료");
    })
}

