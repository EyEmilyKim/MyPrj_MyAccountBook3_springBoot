package com.EyEmilyKim.entity;

import java.sql.Timestamp;

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
public class Transaction {
	private Integer user_id;
	private Integer my_seqno;
	private String tran_id;
	private Timestamp tran_date;
	private String inex;
	private String cate_code;
	private String item;
	private Integer amount;
	private String mncrd;
	private String meth_code;
	private Timestamp reg_date;
	
}
