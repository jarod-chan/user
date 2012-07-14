package cn.fyg.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.user.domain.model.User;
import cn.fyg.user.domain.model.UserQuery;
import cn.fyg.user.domain.model.UserRepository;
import cn.fyg.user.service.UserLoginException;
import cn.fyg.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	UserRepository userRepository;
	@Resource
	UserQuery userQuery;

	@Override
	public Long login(String key, String password) {
		List<User> users=userQuery.findByKey(key);
		if(users.isEmpty()){
			throw new UserLoginException(String.format("can't find user by key:%s",key));
		}else if(users.size()>1){
			throw new UserLoginException(String.format("find many user by key:%s",key));
		}
		User user=users.get(0);
		if(!user.getPassword().equals(password)){
			throw new UserLoginException(String.format("password not match key:%s password:%s", key,password));
		}
		return user.getId();
		
	}



	@Override
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long id) {
		return userRepository.find(id);
	}

}
