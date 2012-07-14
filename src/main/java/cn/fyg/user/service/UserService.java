package cn.fyg.user.service;

import cn.fyg.user.domain.model.User;

public interface UserService {
	
	Long login(String key,String password);
	
	User findById(Long id);
	
	User saveUser(User user);
	
}
