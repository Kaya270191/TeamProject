let id_check=true;
let pw_check=false;
let change=true;

function sub_check(){
    const button=document.getElementById("signup-button");
    if(pw_check&&id_check){
        console.log("버튼 활성화");
        button.disabled=false;
        $(".signup-button").css("display","inline-block")
    }else{
        console.log("버튼 비활성화");
        button.disabled=true;
        $(".signup-button").css("display","none");
    }
}

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
    sub_check();
};

function idCheck(){
    var id = $('.id').val();
    $.ajax({
        url:'/signup/checkID',
        type:'post', //POST 방식으로 전달
        datatype : "JSON",
        data:{"id":id},
        success:function(data){
            if(data==1){
                id_check=true;
                $('.id_success').css("display","inline-block")
                $('.id_failed').css("display","none")
                $('.id_length').css("display","none")
            }else if(data==0){
                id_check=false;
                $('.id_success').css("display","none")
                $('.id_failed').css("display","inline-block")
                $('.id_length').css("display","none")
            }else if(data==2){
                id_check=false;
                $('.id_success').css("display","none")
                $('.id_failed').css("display","none")
                $('.id_length').css("display","inline-block")
            }else if(data==3){
                id_check=false;
                $('.id_success').css("display","none")
                $('.id_failed').css("display","none")
                $('.id_length').css("display","none")
            }
            sub_check();
        }
    });
};
