package cn.fyg.module.user.impl.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.ValidateWithMethod;

import org.apache.commons.lang.StringUtils;

import cn.fyg.module.user.User;

@Entity
public class UserEntity implements User { 

	@Id
	@Length(min=2,max=10,message="用户名长度在{min}和{max}之间")
	private String key;//用户编码

	@NotNull(message="真实姓名不能为空")
	@Length(min=2,max=10,message="真实姓名长度在{min}和{max}之间")
	private String realname;//真实姓名
	
	@NotNull(message="邮箱地址不能为空")
	@Email(message="邮箱地址不正确")
	private String email;//邮箱地址
	
	@MatchPattern(pattern = {"^(13[0-9]|15[^4]|18[6|8|9])\\d{8}$"},message="手机号码验证错误")
	private String cellphone;//手机号码
	
	private String password;//用户密码

	private Boolean enabled;//是否有效
	
	private String uuid;//用于代替id，不作为主键
	
	@PrePersist
	public void prePersist(){
        this.uuid=UUID.randomUUID().toString();
    }

	@Transient
	@ValidateWithMethod(methodName = "checkTempPasswor", parameterType = String.class,message="密码长度必须在6和12之间")
	private String tempPassword;//临时保存用户密码，为编码加密预留接口
	
	
	public boolean checkTempPasswor(String tempPassword){
		boolean result=false;
		if(this.uuid==null){
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
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
