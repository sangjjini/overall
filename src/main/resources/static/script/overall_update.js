let emailNo = $('#homin').val();
 // let emailNo = "SIUUU@naver.com";


// 셀렉트버튼
$(document).ready(function() {
    var selectTarget = $('select');

    selectTarget.change(function(){
        var select_name = $(this).children('option:selected').text();
        $(this).siblings('label').text(select_name);
    });
});


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
        $('#playstyle').val(response.playstyle).prop("selected", true);
        if(response.playstyle!==null){
        $('.playstyle_label').html(response.playstyle);
        }
        $('#pos').val(response.pos).prop("selected", true);
        if(response.pos!==null){
        $('.pos_label').html(response.pos);
        }
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
        playstyle : $('#playstyle').val(),
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

