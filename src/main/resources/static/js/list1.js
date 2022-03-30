<!--모달 이벤트 처리-->

{
        //모달 요소 선택
        const commentEditModal = document.querySelector("#comment-edit-modal")

        //모달 이벤트 감지
        commentEditModal.addEventListener("show.bs.modal", function (event){
        console.log("모달이 열렸습니다");
        //트리거 버튼 선택
        const triggerBtn = event.relatedTarget;

        //데이터 가져오기
        const id = triggerBtn.getAttribute("data-bs-id");
        const nickname = triggerBtn.getAttribute("data-bs-nickname");
        const body = triggerBtn.getAttribute("data-bs-body");
        const boardId = triggerBtn.getAttribute("data-bs-board-id");


        //데이터 반영
        document.querySelector("#edit-comment-nickname").value = nickname;
        document.querySelector("#edit-comment-body").value = body;
        document.querySelector("#edit-comment-id").value = id;
        document.querySelector("#edit-comment-board-id").value = boardId;
    })
    }

{
        //수정 완료 버튼
        const commentUpdateBtn = document.querySelector("#comment-update-btn");

        //클릭 이벤트 감지 및 처리
        commentUpdateBtn.addEventListener("click", function (){
        //수정 댓글 객체 생성
        const comment = {
        id: document.querySelector("#edit-comment-id").value,
        nickName: document.querySelector("#edit-comment-nickname").value,
        body: document.querySelector("#edit-comment-body").value,
        board_id: document.querySelector("#edit-comment-board-id").value
    }
        console.log(comment);
        console.log("댓글수정ㅇㅇ")

        //수정 REST API 호출 - fetch()
        const url = "/api/comments/" + comment.id;
        fetch(url, {
        method: "PATCH",   //PATCH
        body: JSON.stringify(comment), //수정된 댓글 객체를 JSON으로 전달
        headers: {
        "Content-Type" : "application/json"
    }
    }).then(response => {
        //http 응답 코드에 따른 메시지 출력
        const msg = (response.ok) ? "댓글이 수정되었습니다" : "댓글 수정 실패";
        console.log("댓글수정ㅇㅇ왜안돼")
        alert(msg);
        //현재 페이지를 새로고침
        window.location.reload();
    }).catch((error) => console.log("error:", error));
    });
    }
