<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<main>
	<section>
		<h1 class="marginBot">로그인해주세요~♪</h1>
	
		<div>
			<form action="login" method="post" name="fm" onSubmit="return check()">
				<div><input type="text" size="30" name="LID" placeholder="아이디"></div>
				<div><input type="password" size="30" name="PWD" placeholder="비밀번호"></div>
				<div><input type="submit" value="로그인"></div>
			</form>
		</div>

	</section>
</main>

<script type="text/javascript">
function check(){
	if(document.fm.ID.value ==''){
		alert("아이디를 입력하세요."); return false;
	}
	if(document.fm.PWD.value ==''){
		alert("암호를 입력하세요."); return false;
	}
}
</script>