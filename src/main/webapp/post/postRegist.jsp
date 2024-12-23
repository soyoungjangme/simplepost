<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/postRegist.css">
    <title>게시물 등록</title>
</head>
<body>
    <div class="regist-container">
        <p>게시물 등록</p>
        <form action="postRegistForm.post" method="post">
            <div class="regist-post-box">
                <div class="post-title">
                    <p>제목</p>
                    <input type="text" name="post_title" placeholder="제목을 입력하세요." required />
                </div>
                <div class="post-content">
                    <textarea name="post_content" placeholder="내용을 입력하세요." required></textarea>
                </div>
                <div class="btn-box">
                    <a href="./postList.post">목록</a>
                    <button type="submit">등록</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>