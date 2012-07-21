package cn.fyg.user.service;


public interface IUser{

	Long getId();

	void setId(Long id);

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

}