package cn.fyg.user.domain.model;

import java.util.List;


public interface UserQuery {
	
	List<User> findByKey(String key);
	
	boolean multiUser(User user);

}
