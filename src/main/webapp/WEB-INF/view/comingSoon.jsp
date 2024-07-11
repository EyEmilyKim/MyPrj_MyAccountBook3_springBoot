<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<section>
		<h1 id="comingSoon">기능 준비중... 조금만 기다려 주세요♡</h1>
		<h2 id="guide_back">5초 후 이전 페이지로 이동합니다.</h2>
		<div class="loader"></div>
		<div><img src="/images/lamb.png" width="180px" /></div>
	</section>
</main>

<script>
	console.log("Hi ComingSoon");
	setTimeout(() => {
	    history.back();
	}, 5000);
</script>