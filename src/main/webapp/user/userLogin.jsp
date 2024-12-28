<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/userLogin.css">
    <title>로그인</title>
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <p>로그인</p>
            <div class="login-info">
                <input type="text" placeholder="아이디"/>
                <input type="text" placeholder="비밀번호"/>
            </div>
            <div class="btn-box">
                <button type="button">login</button>
            </div>
            <a href="./userSignUp.jsp">회원가입</a>
        </div>
    </div>
</body>
</html>