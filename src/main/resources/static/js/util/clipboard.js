/* 클립보드에 텍스트 복사하는 함수 */

export function copyToClipboard(text, msg){

	// 클립보드 API 사용 가능
    if (navigator.clipboard) {
		navigator.clipboard.writeText(text)
			.then(()=>{
				alert(msg);
			}).catch((err) => {
				alert('클립보드 복사 중 오류 발생');
	            console.error('클립보드 복사 중 오류 발생:', err);
			});
		return;
    }

    // 클립보드 API 사용 불가
	const msg_noClipboardApi = '클립보드를 사용할 수 없는 환경입니다.'
    alert(msg_noClipboardApi);
    
}
