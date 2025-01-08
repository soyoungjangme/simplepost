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
	
	// 댓글 삭제
	const deleteCommentBtn = document.querySelectorAll('.delete-comment-btn');
	
	deleteCommentBtn.forEach((button) => {
		button.addEventListener('click', () => {
			const commentNo = button.getAttribute("comment-no");
			const userNo = button.getAttribute("user-no");

            if (confirm("댓글을 삭제하시겠습니까?")) {
                // AJAX 요청을 통해 서버에 commentNo 전달
                fetch('postCommentDelete.post', {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ commentNo, userNo }),
                })
				.then(response => {
		            if (response.ok) {
		                window.location.href = `postDetail.post?postNo=${postNo}`;
		            } else {
		                throw new Error('댓글 삭제 실패');
		            }
		        })
                .catch((error) => {
                    console.error("Error: ", error);
                    alert("서버 오류가 발생했습니다.");
                });
            }
		});
	});
	
	
});

