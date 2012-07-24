package cn.fyg.module.user.impl.domain;

import java.util.List;

import cn.fyg.module.user.query.QueryExecutor;



public interface UserEntityRepository extends QueryExecutor<UserEntity> {

	UserEntity find(Long id);
	
	UserEntity save(UserEntity user);

	List<UserEntity> findByKey(String key);

	boolean multiUser(UserEntity user);

}
