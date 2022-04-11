function pwCheck(){
    var id = $('#id').val();
    var pw = $('#pw').val();
    console.log(id);
    console.log(pw);
    $.ajax({
        url:'/profile/checkPw',
        type:'post', //POST 방식으로 전달
        datatype : "JSON",
        data:{"id":id,"pw":pw},
        success:function(data){
            if(data==1){
                console.log("인증성공")
//                비밀번호가 일치하면 페이지릉 이동시킴
                window.location.replace("/profile/edit");
            }else if(data==0){
                console.log("인증실패")
                alert("비밀번호가 일치하지 않습니다.")
            }
        },  error:function(request,status,error){
                      alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                   }
    });
};

function signOutCheck(){
    var id = $('#id').val();

    $.ajax({
        url:'/profile/signOut/'+id,
        type:'post',
        datatype:"JSON",
        data:{"id":id},
        success:function(data){
            if(data==1){
                window.location.replace("/thankUbye");

            }else{
                alert("회원탈퇴 실패");

            }
        },
        error:function(request,status,error){
                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
    });
}

function signPwCheck(){
    var sign_out_text=$('#sign_out_text').val();

    if(sign_out_text!="회원탈퇴"){
        alert("'회원탈퇴'를 정확하게 입력해 주십시오.")
        return
    }
    var id = $('#id').val();
    var pw = $('#sign_out_pw').val();
    $.ajax({
        url:'/profile/outCheck/'+id,
        type:'POST', //POST 방식으로 전달
        datatype : "JSON",
        data:{"id":id,"pw":pw},
        success:function(data){
            if(data==1){
                console.log("멤버, 비밀번호 확인")
                signOutCheck()
            }else if(data==0){
                console.log("인증실패")
                alert("비밀번호가 일치하지 않습니다.")
            }
        },  error:function(request,status,error){
                      alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
             }
    });
};