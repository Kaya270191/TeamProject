<!--댓글 삭제-->
// <script>
    {
        //삭제 버튼 선택
        const commentDeleteBtns = document.querySelectorAll(".comment-delete-btn")

        //삭제 버튼 이벤트를 처리
        commentDeleteBtns.forEach(btn => {
        //각 버튼의 이벤트 처리를 등록
        btn.addEventListener("click", (event) => {
        console.log("삭제 버튼이 클릭되었습니다")
        //이벤트 발생 요소를 선택
        const commentDeleteBtn = event.target;

        //삭제 댓글 id 가져오기
        const commentId = commentDeleteBtn.getAttribute("data-comment-id")
        console.log(`삭제 버튼 클릭: ${commentId}번 댓글`);

        //삭제 API 호출 및 처리
        const url = `/api/comments/${commentId}`; //
        fetch(url, {
        method: "DELETE"
    }).then(response => {
        //댓글 삭제 실패 처리
        if(!response.ok){
        alert("댓글 삭제 실패");
        return;
    }
        //  삭제 성공 시, 댓글을 화면에서 지움
        const target = document.querySelector(`#comments-${commentId}`);
        target.remove();
    })
    });
    });
    }
// </script>
