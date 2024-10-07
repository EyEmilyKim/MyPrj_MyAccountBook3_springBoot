/* for view/inc/header.jsp */

import {showModal} from '../../util/modal.js';
import {messages} from '../../data/randomMessage.js';

document.addEventListener('DOMContentLoaded', () => {
	
	const currentPath = window.location.pathname;
	
	const iconLogo_user = document.getElementById("iconLogo_user");
	const titleLogo = document.getElementById("titleLogo");
	
	const toggle_menu = document.getElementById("toggle_menu");
	const menu = document.getElementById("menu");
	
	const tranCrt = document.getElementById("tranCrt"); 
	const tranList = document.getElementById("tranList"); 
	const plan = document.getElementById("plan"); 
	const set = document.getElementById("set");
	const login = document.getElementById("login"); 
	const logout = document.getElementById("logout"); 
	
	// 로그인한 상태의 아이콘 로고
	if(iconLogo_user){
		iconLogo_user.addEventListener('click', ()=> {
			const randomIdx = Math.floor(Math.random() * messages.length);
			const message = messages[randomIdx];
			showModal(message);
		})
	}
	
	// 타이틀 로고
	titleLogo.addEventListener('click', () =>{
		window.location = contextPath + "/";
	})
	
	// 현재 메뉴 하이라이트
	const menu_elements = [tranCrt, tranList, plan, set];
	const menu_paths = ['/tran/crt', '/tran/list', '/plan', '/set'];
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
	
	// 토글 메뉴 
	toggle_menu.addEventListener('click', () => {
		toggle_menu.classList.toggle('active');
		menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
	})
	
	// 브라우저 창 너비에 따른 메뉴 표시 제어
	window.addEventListener('resize', () => {
		// 화면 다시 넓어지면 menu 정상 표시
		if (window.innerWidth > 1080) {
			menu.style.display = 'block';
			toggle_menu.classList.remove('active');
		} 
		// 좁은 화면에서는 menu 비표시
		else {
			menu.style.display = 'none';
		}
	});
	
});
