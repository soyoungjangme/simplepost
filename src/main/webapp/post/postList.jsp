<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/header.css">
    <link rel="stylesheet" href="../css/postList.css">
    <title>게시물 목록</title>
</head>
<body>

	<!-- 헤더 -->
	<%@ include file="../include/header.jsp" %>

    <div class="list-container">
        <p>게시물 목록</p>
        <div class="list-post-box">
            <div class="btn-write">
                <a href="./postRegist.post">글작성</a>
            </div>
            <div class="list-box">
                <table>
                    <thead>
                        <tr>
                            <th>no.</th>
                            <th>제목</th>
                            <th>등록일</th>
                            <th>작성자</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="post" items="${postList}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td><a href="./postDetail.post?postNo=${post.postNo}">${post.postTitle}</a></td>
                                <td>${formattedDates[status.index]}</td>
                                <td>${post.userDTO.userNick}</td>
                                <td>${post.postHit}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
