<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!-- header.jsp는 단지 헤더만 포함 -->
<div class="header-container">
    <div class="header-box">
        <div class="top-title">
            <p>simplepost</p>
        </div>
        <div class="post-top">

            <c:if test="${not empty sessionScope.userNo}">
            	<div class="user-status">
            		<div>${user.userName}</div>
	                <a href="">내 정보</a>
            	</div>
            	<div class="logout">
        			<a href="../user/userLogin.user">로그아웃</a>
            	</div>
            </c:if>
            <c:if test="${empty sessionScope.userNo}">
            	<div class="user-status">
            		<a href="../user/userSignUp.user">회원가입</a>
            	</div>
            	<div class="logout">
        			<a href="../user/userLogin.user">로그인</a>
            	</div>
            </c:if>
            
        </div>
    </div>
</div>
