//게시물 삭제
function postDelete(postNo) {
	if(confirm("삭제하시겠습니까?")) {
		fetch(`postDelete.post?postNo=${postNo}`,{
			method: "DELETE"
		})
		.then(response => response.json())
		.then(data => {
			if(data.success) {
				alert("삭제성공");
				location.href="postList.post";
			} else {
				alert("삭제실패");
			}
		})
		.catch(error => console.log("게시물 삭제 중 error: ", error));
	}
	
}






