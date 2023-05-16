package com.EyEmilyKim.entity;

public class Category {

	private Integer seqno;
	private String inex;
	private String cate_code;
	private String cate_name;
	private String id;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	public Category(Integer seqno, String inex, String cate_code, String cate_name, String id) {
		super();
		this.seqno = seqno;
		this.inex = inex;
		this.cate_code = cate_code;
		this.cate_name = cate_name;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Category [seqno=" + seqno + ", inex=" + inex + ", cate_code=" + cate_code + ", cate_name=" + cate_name
				+ ", id=" + id + "]";
	}
	
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}
	public String getInex() {
		return inex;
	}
	public void setInex(String inex) {
		this.inex = inex;
	}
	public String getCate_code() {
		return cate_code;
	}
	public void setCate_code(String cate_code) {
		this.cate_code = cate_code;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
