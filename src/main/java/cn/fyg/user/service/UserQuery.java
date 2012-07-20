package cn.fyg.user.service;

import cn.fyg.user.domain.model.User;
import cn.fyg.user.infrastructure.query.Query;


public interface UserQuery extends Query<UserQuery,User>  {
	
	UserQuery username();

	UserQuery realname();

	UserQuery email();

	UserQuery cellphone();
	
}
