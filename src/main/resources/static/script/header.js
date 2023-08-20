let session = "SIUUU@naver.com";
$(window).on('load', function(){
    if(session!=null){
        $('#header .mypage_wrap').addClass('logined');
        $('#header .login_wrap').addClass('logined');
    } else {
        $('#header .mypage_wrap').removeClass('logined');
        $('#header .login_wrap').removeClass('logined');
    }
})