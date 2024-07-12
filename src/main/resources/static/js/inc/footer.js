/* for view/inc/footer.js */

document.addEventListener('DOMContentLoaded', () => {
	
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
