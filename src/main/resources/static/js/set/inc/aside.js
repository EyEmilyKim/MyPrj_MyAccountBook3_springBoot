/* for view/set/inc/aside.jsp */

document.addEventListener('DOMContentLoaded', () => {
	
	const currentPath = window.location.pathname;
	
	const setCate = document.getElementById("setCate");
	const setMeth = document.getElementById("setMeth"); 
	
	// 현재 메뉴 표시
	const menu_elements = [setCate, setMeth];
	const menu_paths = ['/set/category', '/set/method'];
	menu_elements.forEach((i, idx) => {
		if(currentPath.startsWith(menu_paths[idx]))
			i.classList.add('selected');
	})
	
})