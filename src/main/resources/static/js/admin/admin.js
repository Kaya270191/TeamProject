function outMember(){
    var id = $('#id').text();
    console.log(id);

    $.ajax({
            url:'/admin/memberOut/'+id,
            type:'post',
            datatype:"JSON",
            data:{"id":id},
            success:function(data){
                if(data==1){
                    window.location.reload();
                }else{
                    alert("회원탈퇴 실패");
                }
            },
            error:function(request,status,error){
                     alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    }
        });
}