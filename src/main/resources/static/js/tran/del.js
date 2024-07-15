/* for view/tran/list.jsp */

document.addEventListener('DOMContentLoaded', () => {
	
	const prevUrl = document.referrer;
	const fm = document.forms['fm'];
	
	// 이전 경로 설정 
	fm.PREV_URL.value = prevUrl;	
	
	// form 취소
	fm.addEventListener('reset', () => {
		if (confirm("취소하고 목록으로 돌아가시겠습니까?")) history.back();
	})
	
	// form 제출
    fm.addEventListener('submit', (event) => {
        if (!confirm('정말 삭제하시겠습니까?')) {
	        event.preventDefault(); 
			return false;
		}
		return true;
    });
    
})
