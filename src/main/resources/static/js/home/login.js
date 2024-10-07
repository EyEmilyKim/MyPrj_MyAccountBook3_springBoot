/* for view/login.jsp */

document.addEventListener('DOMContentLoaded', () => {
	
	const fm = document.forms['fm']; 
	const btn_login = document.getElementById('btn_login');
	
	// 로그인 버튼
	btn_login.addEventListener('click', (event) => {
		event.preventDefault();
		
		if (check()) fm.submit();
		else return;
	}) 
	
	/* 유효성 검사 함수 */
	function check(){
		if (document.fm.LID.value =='') {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (document.fm.PWD.value =='') {
			alert("암호를 입력하세요.");
			return false;
		}
		else {
			return true;
		}
	}

})
