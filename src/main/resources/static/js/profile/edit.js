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
    $('#pwChange').css("display","inline-block");
    $('#pwOpenButton').css("display","none");
}

function pwChangeClose(){
    $('.pw1').val("");
    $('.pw2').val("");
    $('#pwChange').css("display","none");
    $('#pwOpenButton').css("display","inline-block");
    pwCheck();
}


function editMember(){
    var id = $('#id').text();
    var pw = $('#pw').val();
    var name = $('#name').val();
    var phone = $('#phone').val();
    var email = $('#email').val();
    var birthday = $('#birthday').val();
    var address = $('#address').val();

    if(pw.includes(" ")){
        alert("비밀번호는 공백이포함될 수 없습니다.")
        $("#pw").focus();
        return;
    }

    if(name.includes(" ")){
        alert("이름에 공백은 포함할수 없습니다.")
        $("#name").focus();
        return;
    }

   var regName = /^[a-zA-Zㄱ-힣]*$/;
   if(regName.test(name)!=true){
       alert("이름에 숫자와 특수문자는 포함될 수 없습니다.")
       $("#name").focus();
       return;
   }

    if(name.length<2||name.length>6){
        alert("이름은 2~8자리 까지 입력 가능합니다.")
        $("#phone").focus();
        return;
    }

    var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    if(regPhone.test(phone)!=true){
        alert("전화번호 형식이 옳지 않습니다.")
        $("#phone").focus();
        return;
    }

    var regEmail = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$/;
    if(regEmail.test(email)!=true){
        alert("이메일 형식에 맞지 않습니다.")
        $("#email").focus();
        return;
    }

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