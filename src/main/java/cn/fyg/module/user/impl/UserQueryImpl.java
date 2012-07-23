package cn.fyg.module.user.impl;

import cn.fyg.module.user.domain.UserQuery;
import cn.fyg.module.user.domain.impl.UserEntity;
import cn.fyg.module.user.domain.impl.UserEntity_;
import cn.fyg.module.user.query.QueryExecutor;
import cn.fyg.module.user.query.impl.AbstractQuery;

public class UserQueryImpl extends AbstractQuery<UserQuery,UserEntity> implements UserQuery {
	

	public UserQueryImpl(QueryExecutor<UserEntity> queryExecutor){
		super(queryExecutor);
	}

	@Override
	public UserQuery username() {
		this.attribute=UserEntity_.username.getName();
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
