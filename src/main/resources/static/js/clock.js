/**
 * 
 */
function workingClock(){
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
	document.getElementById("clock").innerHTML = time;
}
function startClock(){
	workingClock();
	setInterval(workingClock,1000);//(함수이름,시간간격). 시간간격 1000=1초
}