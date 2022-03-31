function pwCheck(){
    var pw1=$('.pw1').val();
    var pw2=$('.pw2').val();
    if(pw1.length==0||pw2.length==0){
        $('.password_check_ok').css("display","none")
        $('.password_check_no').css("display","none")
        return;
    }
    if(pw1!=pw2){
        pw_check=false;
        $('.password_check_ok').css("display","none")
        $('.password_check_no').css("display","inline-block")
    }else{
        pw_check=true;
        $('.password_check_ok').css("display","inline-block")
        $('.password_check_no').css("display","none")
    }
};

function pwChangeOpen(){
    $('#pwChange').css("display","inline-block")
    $('#pwOpenButton').css("display","none")
}

function pwChangeClose(){
    $('#pwChange').css("display","none")
    $('#pwOpenButton').css("display","inline-block")
}