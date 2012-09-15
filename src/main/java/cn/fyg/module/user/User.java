package cn.fyg.module.user;


public interface User{
	
	public Long getId();

	String getUserKey();

	void setUserKey(String userKey);

	String getRealname();

	void setRealname(String realname);

	String getEmail();

	void setEmail(String email);

	String getCellphone();

	void setCellphone(String cellphone);

	String getPassword();

	void setPassword(String password);

	boolean isEnabled();
}