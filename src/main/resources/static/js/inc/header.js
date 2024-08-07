/* for view/inc/header.js */

document.addEventListener('DOMContentLoaded', () => {
	
	const currentPath = window.location.pathname;
	
	const titleLogo = document.getElementById("titleLogo");
	const tranAdd = document.getElementById("tranAdd"); 
	const tranList = document.getElementById("tranList"); 
	const plan = document.getElementById("plan"); 
	const set = document.getElementById("set");
	const login = document.getElementById("login"); 
	const logout = document.getElementById("logout"); 
	
	// 타이틀 로고
	titleLogo.addEventListener('click', () =>{
		window.location = "/index";
	})
	
	// 현재 메뉴 표시
	const menu_elements = [tranAdd, tranList, plan, set];
	const menu_paths = ['/tran/add', '/tran/list', '/plan', '/set'];
	if (login) {
		menu_elements.push(login);
		menu_paths.push('/login');
	} else if (logout) {
		menu_elements.push(logout);
		menu_paths.push('/logout');
	}
	menu_elements.forEach((i, idx) => {
		if(currentPath.startsWith(menu_paths[idx]))
			i.classList.add('selected');
	})
	
	// 로그아웃 컨펌
	if (logout) {
		logout.addEventListener('click', (event) => {
			if(! confirm("정말 로그아웃하시겠습니까?")) 
				event.preventDefault();
		})
	}
	
});
