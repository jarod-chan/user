package cn.fyg.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.user.domain.model.User;
import cn.fyg.user.domain.model.UserRepository;
import cn.fyg.user.domain.model.UserValidator;
import cn.fyg.user.service.IUser;
import cn.fyg.user.service.UserException;
import cn.fyg.user.service.UserQuery;
import cn.fyg.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	UserRepository userRepository;


	@Override
	public Long login(String key, String password) {
		List<User> users=userRepository.findByKey(key);
		if(users.isEmpty()){
			throw new UserException(String.format("can't find user by key:%s",key));
		}else if(users.size()>1){
			throw new UserException(String.format("find many user by key:%s",key));
		}
		User user=users.get(0);
		if(!user.getPassword().equals(password)){
			throw new UserException(String.format("password not match key:%s password:%s", key,password));
		}
		return user.getId();
		
	}



	@Override
	@Transactional
	public User saveUser(IUser iuser) {
		User user=(User)iuser;
		UserValidator.validate(user);
		if(userRepository.multiUser(user)){
			throw new UserException("存在重复账户");
		}
		return userRepository.save(user);
	}

	@Override
	public IUser findById(Long id) {
		return userRepository.find(id);
	}

	@Override
	public UserQuery createQuery() {
		return new UserQueryImpl(userRepository);
	}

}
