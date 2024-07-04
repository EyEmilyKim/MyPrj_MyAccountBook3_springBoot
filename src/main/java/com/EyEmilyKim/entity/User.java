package com.EyEmilyKim.entity;

import java.util.Date;

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
public class User {
	private Integer user_id;
	private String login_id;
	private String pwd;
	private String nickname;
	private String email;
	private Date birthday;
	private Date reg_date;
}
