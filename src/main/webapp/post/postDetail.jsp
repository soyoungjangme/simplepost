<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:if test="${empty sessionScope.userNo}">
	<script type="text/javascript">
		alert('로그인이 필요합니다.');
        window.location.href = './postList.post';
	</script>
</c:if>

<c:if test="${not empty sessionScope.userNo}">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/postDetail.css">
    <link rel="stylesheet" href="../css/header.css">
    <title>게시물상세</title>
</head>
<body>
	<%@ include file="../include/header.jsp" %>

    <div class="detail-container">
        <div class="post-detail-box">
            <div class="post-reg-info">
                <div class="writer-name">
                    <p>작성자: ${postDetail.userDTO.userNick}</p>
                </div>
                <div class="reg-date">
                    <p>${formattedDate}</p>
                </div>
            </div>
            <div class="post-info-box">
                <div class="post-info">
                    <div class="post-title-btn">
                        <div class="post-title-info">
                            <p>제목: </p>
                            <input type="text" name="post_title" value="${postDetail.postTitle}" disabled/>
                        </div>
                        <div class="btn-box">
                        	<c:if test="${sessionScope.userNo == postDetail.postWriterNo}">
                            	<button type="button" class="post-delete-btn" >삭제</button>
	                            <button type="button" class="post-modify-btn" onclick="location.href='./postRegist.post?postNo=${postDetail.postNo}'">수정</button>
                        	</c:if>
                        </div>
                    </div>
                    <div class="post-content-info">
                        <textarea name="post_content" disabled>${postDetail.postContent}</textarea>
                    </div>
                    <div class="page-into">
                        <div class="page-list"><a href="./postList.post">목록</a></div>
                    </div>
                </div>
            </div>
            
            <div class="post-comment-container">
                <div class="post-comment-box">
                    <p>댓글쓰기</p>
                    <!-- <form action="postCommentForm.post" method="post">
                        <div class="post-comment-write">
                            <textarea name="comment_text" placeholder="댓글을 입력해주세요." required></textarea>
                            <button type="submit" class="regist-form">등록</button>
                        </div>
                    </form>  -->
                    
                    <div class="post-comment-write">
                        <textarea name="comment_text" placeholder="댓글을 입력해주세요." required></textarea>
                        <button type="button" class="regist-form">등록</button>
                    </div>
                    
                     
                    <div class="post-comment-list">
                        <div class="comment-list">
                            <div class="comment-writer">
                                <p>닉네임</p>
                                <p>2025-01-07</p>
                            </div>
                            <div class="comment">댓글댓글댓글댓글댓글댓글
                                댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글
                                댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글
                                댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글
                                댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글
                                댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글
                                댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글댓글
                            </div>
                            <button type="button">삭제</button>
                        </div>
                        <div class="comment-list">
                            <div class="comment-writer">
                                <p>닉네임2</p>
                                <p>2025-01-15</p>
                            </div>
                            <div class="comment">댓글댓글댓글2</div>
                            <button type="button">삭제</button>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
    <script src="../js/postDetail.js"></script>
</body>
</html>
</c:if>