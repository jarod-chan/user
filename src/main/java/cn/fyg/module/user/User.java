package cn.fyg.module.user;


public interface User{

	String getKey();

	void setKey(String key);

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