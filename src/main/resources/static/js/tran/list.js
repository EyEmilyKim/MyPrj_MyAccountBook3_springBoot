/* for view/tran/listAll.jsp */

document.addEventListener('DOMContentLoaded', () => {
	
	const search_reset = document.getElementById("search_reset");
	const fmSRCH = document.forms['fmSRCH'];
	
	if(search_reset){
		search_reset.addEventListener('click', () => {
			alert('search_reset clicked');
			window.location = "/tran/listAll";
		});
	}
	
	fmSRCH.addEventListener('submit', (event) => {
		if(! checkSubmit()){
			event.preventDefault();
			return false;
		}
		return true;
	});
	
	/* ------------ form 제출 ------------ */
	
	function checkSubmit() {
		const fmV = {
			D_FROM : fmSRCH.D_FROM.value,
			D_TO : fmSRCH.D_TO.value,
			ITEM : fmSRCH.ITEM.value,
		}
		console.log(fmV)		
		if (fmV.D_FROM == "" && fmV.D_TO == "" && fmV.ITEM == "") return false;
		return true;
	}
	
});
