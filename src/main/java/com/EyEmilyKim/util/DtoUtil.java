package com.EyEmilyKim.util;

import java.lang.reflect.Field;

public class DtoUtil {

	public static void printFieldValues(Object dto) {
		
		Class<?> clazz = dto.getClass();
		
		while(clazz != null) { // 상위 클래스 존재하는 동안 반복
			
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					System.out.println(field.getName() + " : " + field.get(dto));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
			clazz = clazz.getSuperclass(); // 상위 클래스로 이동
		}
		
	}
	
}
