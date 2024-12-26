<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/postDetail.css">
    <title>게시물상세</title>
</head>
<body>
    <div class="detail-container">
	        <div class="post-detail-box">
	            <div class="post-reg-info">
	                <div class="writer-name">
	                    <p>작성자: 장소영</p>
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
	                            <button type="button" class="post-delete-btn" name="post_delete" id="post_delete" value="${postDetail.postNo}">삭제</button>
	                            <button type="button" class="post-modify-btn">수정</button>
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
	        </div>
    </div>
    <script src="../js/postDetail.js"></script>
</body>
</html>