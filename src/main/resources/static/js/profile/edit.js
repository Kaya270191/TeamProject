function pwCheck(){
    var id = $('#id').val();
    $.ajax({
        url:'/profile/checkPw',
        type:'post', //POST 방식으로 전달
        datatype : "JSON",
        data:{"id":id,"pw":pw},
        success:function(data){
            if(data==1){
                console.log("일단 로직은 작동함")
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
        }
    });
};