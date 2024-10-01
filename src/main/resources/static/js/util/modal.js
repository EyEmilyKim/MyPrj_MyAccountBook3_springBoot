/* for view/inc/modal.jsp */

// DOM 요소 참조
const modalOverlay = document.getElementById('modalOverlay');
const modalContainer = document.getElementById('modalContainer');
const closeModalBtn = document.getElementById('closeModalBtn');
const modalContent = document.getElementById('modalContent');

// 모달 표시 함수
export function showModal(message) {
    modalContent.innerHTML = message.replace(/\n/g, '<br>'); // 전달받은 메시지 출력
    modalContainer.classList.add('show');
    modalOverlay.classList.add('show');
}

// 모달 닫기 함수
function closeModal() {
	modalContent.textContent = "";
    modalContainer.classList.remove('show');
    modalOverlay.classList.remove('show');
}

// 이벤트 리스너 추가 (모달 닫기)
document.addEventListener('DOMContentLoaded', () => {
	if (modalOverlay) {
	    closeModalBtn.addEventListener('click', closeModal);
	    modalOverlay.addEventListener('click', closeModal);
    }
});
