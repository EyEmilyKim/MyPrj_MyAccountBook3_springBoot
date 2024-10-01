/* for view/inc/footer.jsp */

import {copyToClipboard} from '../util/clipboard.js';

document.addEventListener('DOMContentLoaded', () => {
	
	// share 버튼 클릭시 homeUrl 복사
	const icon_share = document.getElementById("icon_share");
	icon_share.addEventListener('click', ()=>{
		Promise.all([
			axios.get(contextPath + '/api/v1/server-path'),
			axios.get(contextPath + '/api/v1/context-path')
		])
		.then(([res1, res2]) => {
			console.log('res1', res1, 'res2', res2);
			const serverPath = res1.data;
			const contextPath = res2.data;
			const homeUrl = `${serverPath}${contextPath}`;
			
			// 클립보드로 homeUrl 복사
			const msg_ok = `URL주소가 클립보드에 복사되었습니다.\n\n${homeUrl}`;
			const msg_fail = `다음 URL을 복사하여 공유해주세요 :\n\n${homeUrl}`;
			copyToClipboard(homeUrl, msg_ok, msg_fail);
		})
		.catch((err) => {
			alert('API 조회 중 오류 발생');
			console.error('API 조회 중 오류 발생', err);
		});
	})
	
	// email 버튼 클릭시 문의 템플릿 획득 후 기본 이메일 앱 열기
	const icon_email = document.getElementById("icon_email");
	icon_email.addEventListener('click', ()=>{
		axios.get(contextPath + '/api/v1/email-to-developer')
			.then((res) => {
				console.log(res);
				const address = res.data.address;
				const subject = res.data.subject;
                
                // 기본 이메일 앱으로 문의 템플릿 열기
                const mailtoLink = `mailto:${address}?subject=${encodeURIComponent(subject)}`;
                window.location.href = mailtoLink;
                
                // 이메일 수신자 클립보드에 복사, 안내 메시지
                const msg_ok = '개발자의 이메일 주소가 클립보드에 복사되었습니다.\n\n'
                	+ '만약 잠시 후 당신의 이메일 앱이 자동으로 열리지 않는다면, '
                	+ '복사된 주소 앞으로 직접 이메일을 작성하여 보내주시기 바랍니다.';
            	const msg_fail = '만약 잠시 후 당신의 이메일 앱이 자동으로 열리지 않는다면, '
            		+ '아래의 주소 앞으로 직접 이메일을 작성하여 보내주시기 바랍니다 :\n\n' + address;
				copyToClipboard(address, msg_ok, msg_fail);               
			})
			.catch((err) => {
				alert('문의 템플릿 조회 중 오류 발생');
				console.error('문의 템플릿 조회 중 오류 발생:', err);
			});
	});
	
	// 실시간 시간 출력
	const clock = document.getElementById("clock");
	setRealTime(); // 페이지 로드되면 시간 표시위해 한번 호출.
	setInterval(setRealTime, 1000);// 1초마다 업데이트. (함수이름,시간간격) 시간간격 1000=1초
	
	function setRealTime(){
		let days = ["일","월","화","수","목","금","토"];
		let today = new Date();
		let year = today.getFullYear();
		let month = today.getMonth() + 1;
		if(month < 10) month = "0"+month;
		let date = today.getDate();
		if(date < 10) date = "0"+date;
		let index = today.getDay();
		let day = days[index];
		let hour = today.getHours();
		let min = today.getMinutes();
		if(min < 10) min = "0"+min;
		let sec = today.getSeconds();
		if(sec < 10) sec = "0"+sec;
		let time = year+"-"+month+"-"+date+" "+day+" "+hour+":"+min+":"+sec;
		
		clock.innerHTML = time;
	}
	
})
