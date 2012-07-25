package cn.fyg.module.user.impl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import cn.fyg.module.user.User;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.ValidateWithMethod;

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
	
	private String password;//用户密码

	private Boolean enabled;
	
	@Transient
	@ValidateWithMethod(methodName = "checkTempPasswor", parameterType = String.class,message="密码长度必须在6和12之间")
	private String tempPassword;
	
	
	public boolean checkTempPasswor(String tempPassword){
		boolean result=false;
		if(this.id==null){
			result=checkBeforeCreate(tempPassword);
		}else{
			result=checkBeforeUpdate(tempPassword);
		}
		return result;
	}

	private boolean checkBeforeCreate(String tempPassword) {
		if(tempPassword==null){
			return false;
		}
		tempPassword=tempPassword.trim();
		if(StringUtils.isBlank(tempPassword)){
			return false;
		}
		if(tempPassword.length()<6||tempPassword.length()>12){
			return false;
		}
		return true;
	}
	
	private boolean checkBeforeUpdate(String tempPassword) {
		if(tempPassword==null){
			return true;
		}
		tempPassword=tempPassword.trim();
		if(StringUtils.isBlank(tempPassword)){
			return false;
		}
		if(tempPassword.length()<6||tempPassword.length()>12){
			return false;
		}
		return true;
	}
	
	public void encryptionPassword(){
		if(this.tempPassword!=null){
			this.password=this.tempPassword;
		}
	}
	
	public boolean checkPassword(String comparePassword){
		if(this.password==null){
			return false;
		}
		if(comparePassword==null){
			return false;
		}
		return this.password.equals(comparePassword);
	}
	

	public String getId() {
		if(id==null) return null;
		return id.toString();
	}

	public void setId(String id) {
		if(id==null){
			this.id=null;
			return;
		}
		this.id = Long.valueOf(id);
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
		this.tempPassword = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled.booleanValue();
	}
	
	
	
}
