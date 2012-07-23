package cn.fyg.module.user.domain.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import cn.fyg.module.user.domain.User;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotNull;

@Entity
public class UserEntity implements User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	@NotNull(message="用户名不能为空")
	@Length(min=2,max=10,message="用户名长度在{min}和{max}之间")
	private String username;//用户名
	
	@NotNull(message="真实姓名不能为空")
	@Length(min=2,max=10,message="真实姓名长度在{min}和{max}之间")
	private String realname;//真实姓名
	
	@NotNull(message="邮箱地址不能为空")
	@Email(message="邮箱地址不正确")
	private String email;//邮箱地址
	
	@MatchPattern(pattern = {"^(13[0-9]|15[^4]|18[6|8|9])\\d{8}$"},message="手机号码验证错误")
	private String cellphone;//手机号码
	
	@NotNull(message="密码不能为空")
	@Length(min=6,max=12,message="密码长度在{min}和{max}之间")
	private String password;//用户密码


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
