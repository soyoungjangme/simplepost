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
        <form action="userLoginForm.user" method="post">
        	<div class="login-box">
	            <p>로그인</p>
	            <div class="login-info">
	                <input type="text" name="user_id" placeholder="아이디"/>
	                <input type="text" name="user_pw" placeholder="비밀번호"/>
	            </div>
	            <div class="btn-box">
	                <button type="submit">login</button>
	            </div>
	            <a href="./userSignUp.user">회원가입</a>
        	</div>
        </form>
    </div>
</body>
</html>