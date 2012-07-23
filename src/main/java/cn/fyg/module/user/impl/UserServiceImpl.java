package cn.fyg.module.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.fyg.module.user.UserException;
import cn.fyg.module.user.UserService;
import cn.fyg.module.user.domain.User;
import cn.fyg.module.user.domain.UserQuery;
import cn.fyg.module.user.domain.impl.UserEntity;
import cn.fyg.module.user.domain.impl.UserRepository;
import cn.fyg.module.user.domain.impl.UserValidator;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	UserRepository userRepository;


	@Override
	public Long login(String key, String password) {
		List<UserEntity> users=userRepository.findByKey(key);
		if(users.isEmpty()){
			throw new UserException(String.format("can't find user by key:%s",key));
		}else if(users.size()>1){
			throw new UserException(String.format("find many user by key:%s",key));
		}
		UserEntity user=users.get(0);
		if(!user.getPassword().equals(password)){
			throw new UserException(String.format("password not match key:%s password:%s", key,password));
		}
		return user.getId();
		
	}



	@Override
	@Transactional
	public UserEntity saveUser(User user) {
		UserEntity userEntity=(UserEntity)user;
		UserValidator.validate(userEntity);
		if(userRepository.multiUser(userEntity)){
			throw new UserException("存在重复账户");
		}
		return userRepository.save(userEntity);
	}

	@Override
	public UserEntity findById(Long id) {
		return userRepository.find(id);
	}

	@Override
	public UserQuery createQuery() {
		return new UserQueryImpl(userRepository);
	}

}
