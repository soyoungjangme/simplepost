
function postDelete() {
  var postNo = $("#post_delete").val();
	
  $.ajax({
    type: "DELETE", //전송방식 지정
    url: "/post/postDetail.post", //전송 url
    data: {postNo: postNo}, //요청 시 전송할 데이터
	success: function(response){
		alert(`${postNo}번 삭제 성공`);
		window.location.href="./postList.post";
		
	}, error: function(error){
		alert(`${postNo}번 삭제 실패`)
	}
  });
}