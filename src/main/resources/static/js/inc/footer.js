/* for view/inc/footer.jsp */

document.addEventListener('DOMContentLoaded', () => {
	
	// share 버튼 클릭시 url 복사
	const icon_share = document.getElementById("icon_share");
	icon_share.addEventListener('click', ()=>{
		const homeUrl = window.location.origin+"/index";
		navigator.clipboard.writeText(homeUrl).then(()=>{
                alert('URL주소가 클립보드에 복사되었습니다.');
            }).catch((error)=> {
               	alert('클립보드 복사 중 오류 발생');
                console.error('클립보드 복사 중 오류 발생:', error);
            });
	})
	
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
