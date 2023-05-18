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
public class Method {
	private Integer seqno;
	private String mncrd;
	private String meth_name;
	private String meth_code;
	private String id;
	
}
