<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<section>
		<h1 id="mainTitle">기능 준비중... 조금만 기다려 주세요♡</h1>
		<h2 id="guide_main_1">5초 후 이전 페이지로 이동합니다.</h2>
		<div class="loader"></div>
		<div><img src="${pageContext.request.contextPath}/images/lamb.png" width="180px" /></div>
	</section>
</main>

<script>
	console.log("Hi ComingSoon");
	let href;
	if (document.referrer) {
		href = document.referrer;
	} else {
		href = "/";
	}
	console.log('href', href);
	setTimeout(() => {
	    location.href = href;
	}, 5000);
</script>