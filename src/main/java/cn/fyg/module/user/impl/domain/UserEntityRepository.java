package cn.fyg.module.user.impl.domain;

import java.util.List;

import cn.fyg.module.user.User;
import cn.fyg.module.user.UserQuery;
import cn.fyg.module.user.query.QueryExecutor;



public interface UserEntityRepository extends QueryExecutor<UserQuery,User> {

	UserEntity find(String key);
	
	UserEntity save(UserEntity user);

	List<UserEntity> findByKey(String key);

	boolean isConflict(UserEntity user);

}
