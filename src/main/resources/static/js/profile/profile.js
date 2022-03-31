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