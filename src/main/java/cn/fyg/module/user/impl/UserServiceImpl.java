package cn.fyg.module.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.fyg.module.user.User;
import cn.fyg.module.user.UserException;
import cn.fyg.module.user.UserQuery;
import cn.fyg.module.user.UserService;
import cn.fyg.module.user.impl.domain.UserEntity;
import cn.fyg.module.user.impl.domain.UserEntityRepository;
import cn.fyg.module.user.impl.domain.UserEntityValidator;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	UserEntityRepository userEntityRepository;
	
	@Override
	public User createUser(){ 
		return new UserEntity();
	}


	@Override
	public String login(String key, String password) {
		List<UserEntity> users=userEntityRepository.findByKey(key);
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
		UserEntityValidator.validate(userEntity);
		if(userEntityRepository.multiUser(userEntity)){
			throw new UserException("存在重复账户");
		}
		return userEntityRepository.save(userEntity);
	}

	@Override
	public UserEntity findById(String id) {
		if(id==null){
			throw new NullPointerException("id is null");
		}
		return userEntityRepository.find(Long.valueOf(id));
	}

	@Override
	public UserQuery createQuery() {
		return new UserQueryImpl(userEntityRepository);
	}



	

}
