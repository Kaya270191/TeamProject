let id_check=false;
let pw_check=false;


function sub_check(){
    const button=document.getElementById("login-button");
    if(pw_check&&id_check){
        $(".signup-button").css("display","inline-block")
    }else{
        console.log("버튼 비활성화");
        $(".signup-button").css("display","none");
    }
};

function idCheck(){
    id_check=true;
    sub_check();
};

function pwCheck(){
    pw_check=true;
    sub_check();
};