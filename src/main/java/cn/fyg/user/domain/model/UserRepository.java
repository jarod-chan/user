package cn.fyg.user.domain.model;

import java.util.List;

import demo.QueryExecutor;


public interface UserRepository extends QueryExecutor<User> {

	User find(Long id);
	
	User save(User user);

	List<User> findByKey(String key);

	boolean multiUser(User user);

}
