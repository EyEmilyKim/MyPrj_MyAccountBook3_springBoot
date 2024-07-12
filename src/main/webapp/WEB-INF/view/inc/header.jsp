<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	

<header>
	<h1 id="titleLogo">My 가계부 Ver3.</h1>
	
	<nav id="menu">
		<ul>
			<li><a href="/tran/add" id="tranAdd">가계부 쓰기</a></li>
			<li><a href="/tran/listAll" id="tranList">가계부 보기</a></li>
			<li><a href="/plan" id="plan">월별 계획</a></li>
			<li><a href="/set/category/list" id="set">설정</a></li>
		<c:if test="${empty sessionScope.USER_ID }">
			<li><a href="/login" id="login">로그인</a></li>
		</c:if>
		<c:if test="${! empty sessionScope.USER_ID }">
			<li><a href="/logout" id="logout">로그아웃</a></li>
		</c:if>
		</ul>
	</nav>
	
</header>

<script src="/js/inc/header.js"></script>
