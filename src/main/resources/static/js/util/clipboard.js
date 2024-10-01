/* 클립보드에 텍스트 복사하는 함수 */

import {showModal} from './modal.js';

export function copyToClipboard(text, msg_ok, msg_fail){

	// 클립보드 API 사용 가능
    if (navigator.clipboard) {
		navigator.clipboard.writeText(text)
			.then(()=>{
				alert(msg_ok);
			}).catch((err) => {
				alert('클립보드 복사 중 오류 발생');
	            console.error('클립보드 복사 중 오류 발생:', err);
			});
		return;
    }

    // 클립보드 API 사용 불가 -> 모달로 직접 복사 유도
	const msg_noClipboardApi = '클립보드를 사용할 수 없는 환경입니다.\n'
    showModal(msg_noClipboardApi + msg_fail);
    
}
