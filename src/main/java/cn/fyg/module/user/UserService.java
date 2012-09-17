package cn.fyg.module.user;


public interface UserService {
	
	
	/**
	 * 返回一个新建的User对象
	 * @return User
	 */
	User createUser();
	
	/**
	 * 保存用户
	 * @param user
	 * @return User
	 * @throws UserException 如果保存失败，则抛出UserException
	 */
	User saveUser(User user);
	
	/**
	 * 根据用户id，返回用户信息
	 * @param id
	 * @return User
	 */
	User findUser(String key);
	
	/**
	 * 启用一个用户
	 * @param id
	 */
	void enableUser(String key); 
	
	/**
	 * 禁用一个用户
	 * @param id
	 */
	void disableUser(String key);
	
	/**
	 * 校验用户登录名，用户密码
	 * @param key 用户名、邮箱、电话号码
	 * @param password 密码
	 * @return userkey 用户key
	 * @throws UserException 如果登录失败，则抛出UserException
	 */
	String login(String key,String password);
	
	/**
	 * 查询用户
	 * @return
	 */
	UserQuery createQuery();
	
}
