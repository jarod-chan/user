package cn.fyg.user.infrastructure.query.impl;

import java.util.List;

import cn.fyg.user.infrastructure.query.QueryExecutor;



public interface UserRepository extends QueryExecutor<User> {

	User find(Long id);
	
	User save(User user);

	List<User> findByKey(String key);

	boolean multiUser(User user);

}
