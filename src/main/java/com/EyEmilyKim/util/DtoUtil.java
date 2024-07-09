package com.EyEmilyKim.util;

import java.lang.reflect.Field;

public class DtoUtil {

	public static void printDto(Object dto) {
		Field[] fields = dto.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				System.out.println(field.getName() + " : " + field.get(dto));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
}
