package cn.fyg.module.user.domain;

import cn.fyg.module.user.domain.impl.UserEntity;
import cn.fyg.module.user.query.Query;


public interface UserQuery extends Query<UserQuery,UserEntity>  {
	
	UserQuery username();

	UserQuery realname();

	UserQuery email();

	UserQuery cellphone();
	
}
