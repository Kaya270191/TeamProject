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


function editMember(){
    var id = $('#id').text();
    var pw = $('#pw').val();
    var name = $('#name').val();
    var phone = $('#phone').val();
    var email = $('#email').val();
    var birthday = $('#birthday').val();
    var address = $('#address').val();

    var memberForm={
        'id':id,
        'pw':pw,
        'name':name,
        'phone':phone,
        'email':email,
        'birthday':birthday,
        'address':address
    }


    $.ajax({
        url:'/profile/edit/'+id,
        headers: {
                "Content-Type" : "application/json"
            },
        type:'PATCH',
        datatype:"JSON",
        data:JSON.stringify(memberForm),
        contentType: "application/json; charset=UTF-8",
        success:function(data){
            if(data==1){
                window.location.replace("/profile");
            }else{
                alert("정보수정 실패");
            }
        },
        error:function(request,status,error){
                             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                            }
    });

}