/* for view/tran/list.jsp */

document.addEventListener('DOMContentLoaded', () => {
	
	// 검색 form
	const fmSRCH = document.forms['fmSRCH'];
	const search_reset = document.getElementById("search_reset");
	// N줄보기 form
	const fmRC = document.forms['fmRC'];
	const slct_rc = document.getElementById("slct_rc");
	
	/* ------------ 검색 form ------------ */
	
	if (search_reset) {
		search_reset.addEventListener('click', () => {
			const inex = fmSRCH.INEX.value;
			let url = "/tran/list"
			switch(inex) {
				case "ALL" : url += "All"; break;
				case "IN" : url += "In"; break;
				case "EX" : url += "Ex"; break;
			}
			window.location = url;
		});
	}
	
	fmSRCH.addEventListener('submit', (event) => {
		const fmV = {
			D_FROM : fmSRCH.D_FROM.value,
			D_TO : fmSRCH.D_TO.value,
			ITEM : fmSRCH.ITEM.value,
			CATE_NAME : fmSRCH.CATE_NAME.value,
			METH_NAME : fmSRCH.METH_NAME.value,
		}
		console.log(fmV)		
		if (fmV.D_FROM == "" && fmV.D_TO == "" && fmV.ITEM == "" && fmV.CATE_NAME == "" && fmV.METH_NAME == "") {
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
