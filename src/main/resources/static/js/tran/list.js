/* for view/tran/list.jsp */

document.addEventListener('DOMContentLoaded', () => {
	
	const fmSRCH = document.forms['fmSRCH'];
	const search_reset = document.getElementById("search_reset");
	
	const pagingSpans = document.querySelectorAll('span.paging');
	const slct_rc = document.getElementById("slct_rc");	
	
	/* ------------ 검색 form ------------ */
	
	if (search_reset) {
		search_reset.addEventListener('click', () => {
			const inex = fmSRCH.INEX.value;
			const rc = fmSRCH.RC.value;
			let url = `${contextPath}/tran/list`;
			switch(inex) {
				case "ALL" : url += "All"; break;
				case "IN" : url += "In"; break;
				case "EX" : url += "Ex"; break;
			}
			url += "?RC=" + rc;
			window.location = url;
		});
	}
	
	fmSRCH.addEventListener('submit', (event) => {
		const fmV = {
			D_FROM : fmSRCH.D_FROM.value,
			D_TO : fmSRCH.D_TO.value,
			ITEM : fmSRCH.ITEM.value,
			CATE_NAME : fmSRCH.CATE_NAME.value,
		};
		if(fmSRCH.INEX.value === "EX") 
			fmV["METH_NAME"] = fmSRCH.METH_NAME.value; 
		// console.log(fmV)
		
		if (isAllBlank(fmV)) {
			alert('검색 조건이 설정되지 않았습니다.');
			event.preventDefault();
		}
	});
	
	function isAllBlank(object) {
		let flag = true;
		for (let key in object) {
			if (object[key] !== "") { 
				flag = false; 
				break;
			}
		}
		return flag;
	}
	
	/* ------------ 페이지 번호 ------------ */
	
	pagingSpans.forEach(span => {
		span.addEventListener('click', handlePage);
	})
	function handlePage(event) {
		const page = event.target.getAttribute('data-page');
		fmSRCH.PG.value = page;
		fmSRCH.submit();
	}
	
	/* ------------ N줄 보기 ------------ */
	
	slct_rc.addEventListener('change', () => {
		fmSRCH.PG.value = '';
		fmSRCH.RC.value = slct_rc.value;
		fmSRCH.submit();
	})
	
});
