
    {
        //댓글 생성 버튼을 변수화(id가 comment-create-btn인 대상 )
        const commentCreateBtn =document.querySelector("#comment-create-btn")

        //버튼 클릭 이벤트를 감지
        commentCreateBtn.addEventListener("click", function (){
        console.log("버튼이 클릭되었습니다.")
        //새 댓글 객체 생성
        const comment = {
        nickname: document.querySelector("#new-comment-nickname").value,
        body: document.querySelector("#new-comment-body").value,
        board_id: document.querySelector("#new-comment-board-id").value
    };
        //댓글 객체 출력
        console.log(comment);

        //fetch() - Talend API 요청을 JavaScript로 보내준다
        const url = "/api/boards/" + comment.board_id  + "/comments";
        fetch(url, {
        method: "post",     //POST요청
        body: JSON.stringify(comment), //comment JS 객체를 JSON으로 변경하여 보냄
        headers: {
        "Content-Type": "application/json"
    }
    }).then(response => {
        //http 응답 코드에 따른 메세지 출력
        const msg = (response.ok)? " 댓글이 등록되었습니다." : "댓글 등록 실패";
        alert(msg);
        //현재 페이지 새로고침
        window.location.reload();
    })
    });
    }

