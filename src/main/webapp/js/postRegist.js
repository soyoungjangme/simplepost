document.addEventListener('DOMContentLoaded', () => {
    const params = new URLSearchParams(window.location.search);
	const postNo = params.get('postNo') || ''; 
	
	if(postNo){
		fetch(`postDetail.post?postNo=${postNo}`,{
			headers: { 'Accept': 'application/json' }
		})
		.then(response => response.json())
		.then(data => {
			//데이터 채우기
			document.querySelector('input[name=post_title]').value = data.postTitle || '';
			document.querySelector('textarea').value = data.postContent || '';

		})
		.catch(error => console.log("게시물 수정 기존 내용 호출 중 error ", error));
	}
	
	
	// 수정 form
	const form = document.querySelector('form');
	form.addEventListener('submit', (e) => {
		e.preventDefault();
		
		const title = document.querySelector('input[name=post_title]').value;
		const content = document.querySelector('textarea[name=post_content]').value;
		
		const postDTO = {
			postNo : postNo,
			postTitle : title,
			postContent : content
		}
		
		if(postNo){
			fetch(`postUpdate.post?postNo=${postNo}`, {
				method: 'PUT',
				body: JSON.stringify(postDTO)
			})
			.then(response => {
				if(response.ok){
					alert("수정이 완료되었습니다.");
					window.location.href=`postDetail.post?postNo=${postNo}`;
				} else{
					throw new Error('수정 실패');
				}
			})
			.catch(error => console.log("게시글 수정등록 중 error ", error))
			
		} else {
			// 새로 등록 요청 (POST)
            fetch('postRegistForm.post', {
                method: 'POST',
                body: JSON.stringify({ postTitle: title, postContent: content })
            })
            .then(response => {
                if (response.ok) {
                    alert('게시물이 등록되었습니다.');
                    window.location.href = './postList.post';
                } else {
                    throw new Error('등록 실패');
                }
            })
            .catch(error => console.log("게시물 등록 실패: ", error));
        }
		
		
	})
	
	
})