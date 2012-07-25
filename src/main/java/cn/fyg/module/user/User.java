package cn.fyg.module.user;


public interface User{

	String getId();

	void setId(String id);

	String getUsername();

	void setUsername(String username);

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