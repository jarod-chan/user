package cn.fyg.module.user.impl;

import cn.fyg.module.user.User;
import cn.fyg.module.user.UserQuery;
import cn.fyg.module.user.impl.domain.UserEntity_;
import cn.fyg.module.user.query.QueryExecutor;
import cn.fyg.module.user.query.impl.AbstractQuery;

public class UserQueryImpl extends AbstractQuery<UserQuery,User> implements UserQuery {
	

	public UserQueryImpl(QueryExecutor<UserQuery,User> queryExecutor){
		super(queryExecutor);
	}

	@Override
	public UserQuery userKey() {
		this.attribute=UserEntity_.key.getName();
		return this;
	}

	@Override
	public UserQuery realname() {
		this.attribute=UserEntity_.realname.getName();
		return this;
	}

	@Override
	public UserQuery email() {
		this.attribute=UserEntity_.email.getName();
		return this;
	}

	@Override
	public UserQuery cellphone() {
		this.attribute=UserEntity_.cellphone.getName();
		return this;
	}

}
