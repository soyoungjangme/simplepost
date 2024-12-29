<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/userSignUp.css">
    <title>회원가입</title>
</head>
<body>
    <div class="signup-container">
        <div class="signup-box">
            <form action="userSignUpForm.user" method="post">
                <div class="user-signup-title">
                    <p>회원정보입력</p>
                </div>
                <div class="login-info-regist">
                    <div class="info-group">
                        <p>이름</p>
                        <input type="text" name="user_name" placeholder="이름을 입력해주세요."/>
                    </div>
        
                    <div class="info-group">
                        <p>닉네임</p>
                        <input type="text" name="user_nick" placeholder="닉네임을 입력해주세요."/>
                    </div>
        
                    <div class="info-id-group">
                        <p>아이디</p>
                        <div class="input-group">
                            <input type="text" id="user_id" name="user_id" placeholder="아이디를 입력해주세요."/>
                            <button type="button" onclick="checkId()">중복확인</button>
                        </div>
                        <div></div>
                        <div class="small-text">
                            <p id="idCheckMessage"></p>
                        </div>   
                    </div>
                    
                    <div class="info-group">
                        <p>비밀번호</p>
                        <input type="text" name="user_pw" placeholder="비밀번호를 입력해주세요."/>
                    </div>
                </div>
                <div class="btn-box">
                    <button type="button" class="go-login" onclick="location.href='./userLogin.user'">로그인하기</button>
                    <button type="submit" class="go-regist">가입하기</button>
                </div>
            </form>
        </div>
    </div>
    <script src="../js/userSignUp.js"></script>
</body>
</html>