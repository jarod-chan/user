package cn.fyg.module.user;

import cn.fyg.module.user.domain.UserQuery;
import cn.fyg.module.user.domain.User;

public interface UserService {
	
	/**
	 * 校验用户登录名，用户密码
	 * @param key 用户名、邮箱、电话号码
	 * @param password 密码
	 * @return userid 用户id
	 * @throws UserException 如果登录失败，则抛出UserException
	 */
	Long login(String key,String password);
	

	/**
	 * 根据用户id，返回用户信息
	 * @param id
	 * @return User
	 */
	User findById(Long id);
	
	
	/**
	 * 保存用户
	 * @param user
	 * @return User
	 * @throws UserException 如果保存失败，则抛出UserException
	 */
	User saveUser(User iuser);
	
	/**
	 * 查询用户
	 * @return
	 */
	UserQuery createQuery();
	
}
