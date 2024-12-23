<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/postList.css">
    <title>게시물 목록</title>
</head>
<body>
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
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>제목입니다.</td>
                            <td>2024-05-12</td>
                            <td>13</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>제목입니다.</td>
                            <td>2024-11-12</td>
                            <td>5</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</body>
</html>