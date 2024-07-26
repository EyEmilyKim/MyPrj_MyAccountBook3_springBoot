package com.EyEmilyKim.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
	private Integer user_id;
	private Integer seqno;
	private String inex;
	private String cate_code;
	private String cate_name;
	private String default_cate_code;
	
}
