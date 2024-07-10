/* for view/tran/listAll.jsp */

document.addEventListener('DOMContentLoaded', () => {
	
	// 검색 form
	const fmSRCH = document.forms['fmSRCH'];
	const search_reset = document.getElementById("search_reset");
	// N줄보기 form
	const fmRC = document.forms['fmRC'];
	const slct_rc = document.getElementById("slct_rc");
	
	/* ------------ 검색 form ------------ */
	
	if(search_reset){
		search_reset.addEventListener('click', () => {
			window.location = "/tran/listAll";
		});
	}
	
	fmSRCH.addEventListener('submit', (event) => {
		const fmV = {
			D_FROM : fmSRCH.D_FROM.value,
			D_TO : fmSRCH.D_TO.value,
			ITEM : fmSRCH.ITEM.value,
		}
		console.log(fmV)		
		if (fmV.D_FROM == "" && fmV.D_TO == "" && fmV.ITEM == "") {
			event.preventDefault();
			return false;			
		}
		return true;
	});
	
	/* ------------ N줄보기 form ------------ */
	
	slct_rc.addEventListener('change', () => {
		fmRC.submit();
	})
	
});
