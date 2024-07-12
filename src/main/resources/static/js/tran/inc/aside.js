/* for view/tran/inc/aside.jsp */

document.addEventListener('DOMContentLoaded', () => {
	
	const currentPath = window.location.pathname;
	
	const tranListAll = document.getElementById("tranListAll");
	const tranListIn = document.getElementById("tranListIn"); 
	const tranListEx = document.getElementById("tranListEx");
	
	// 현재 메뉴 표시
	const menu_elements = [tranListAll, tranListIn, tranListEx];
	const menu_paths = ['/tran/listAll', '/tran/listIn', '/tran/listEx'];
	menu_elements.forEach((i, idx) => {
		if(currentPath.startsWith(menu_paths[idx]))
			i.classList.add('selected');
	})
	
})
