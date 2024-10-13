<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>알림창</title>
</head>
<body>
	<script>
	
		// 처리 결과 안내 및 이동할 페이지 선택
		if("${TRAN_CRT}"){
			let redirectUrl = "${URL_AGAIN}";
			const confirmMsg = "\n이어서 가계부를 쓰시겠습니까? 취소 시 가계부 전체 목록으로 이동합니다."
			if("${OK}"){
				if(!confirm("${MSG}" + confirmMsg)) {
					redirectUrl = "${URL_NEXT}";
				}
			} else {
				alert("${MSG}");
			}
			location.href = redirectUrl;			
		}
		// 처리 결과 안내 후 지정된 페이지로 이동
		else {
			alert("${MSG}");
			location.href = "${URL}";
		}
		
	</script>
</body>
</html>