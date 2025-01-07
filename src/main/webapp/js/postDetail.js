document.addEventListener('DOMContentLoaded', () => {
    const params = new URLSearchParams(window.location.search);
    const postNo = params.get('postNo') || ''; // postNo 기본값 빈 문자열
    
    console.log("postNo:", postNo); // 확인

    // 게시물 삭제
    const postDeleteBtn = document.querySelector('.post-delete-btn');
	if(postDeleteBtn){
		postDeleteBtn.addEventListener('click', () => {
	        console.log("삭제버튼 클릭됨");

	        if(confirm("삭제하시겠습니까?")) {
	            fetch(`postDelete.post?postNo=${postNo}`, {
	                method: "DELETE"
	            })
	            .then(response => response.json())
	            .then(data => {
	                if(data.success) {
	                    alert("삭제성공");
	                    location.href = "postList.post";
	                } else {
	                    alert("삭제실패");
	                }
	            })
	            .catch(error => console.error("게시물 삭제 중 error: ", error));
	        }
	    });	
	}
    

    // 댓글 등록
    const registForm = document.querySelector('.regist-form');
    registForm.addEventListener('click', () => {
		//e.preventDefault();
		
		console.log("btn클릭");
        const comment = document.querySelector('textarea[name=comment_text]').value;

        const postCommentDTO = {
            postNo: postNo,
            commentText: comment
        };

        console.log("확인 ", postCommentDTO);

        fetch(`postCommentForm.post?postNo=${postNo}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // 이 헤더가 필요합니다
            },
            body: JSON.stringify(postCommentDTO)
        })
        .then(response => {
            if (response.ok) {
                window.location.href = `postDetail.post?postNo=${postNo}`;
            } else {
                throw new Error('댓글 등록 실패');
            }
        })
        .catch(error => {
            console.error('댓글등록 중 에러발생 ', error);
            alert('댓글 등록 중 문제가 발생했습니다. 다시 시도해주세요.');
        });
    });
});

