<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<main>
	<section>
		<h1 class="marginBot">로그인해주세요~♪</h1>
	
		<div>
			<form action="login" method="post" name="fm" class="login_form">
				<div><input type="text" size="30" name="LID" placeholder="아이디" class="input"></div>
				<div><input type="password" size="30" name="PWD" placeholder="비밀번호" class="input"></div>
				<div><button id="btn_login">로그인</button></div>
			</form>
		</div>

	</section>
</main>

<script type="module" src="${pageContext.request.contextPath}/js/home/login.js"></script>
