package cn.fyg.user.service.impl;

import cn.fyg.user.infrastructure.query.QueryExecutor;
import cn.fyg.user.infrastructure.query.impl.User;
import cn.fyg.user.infrastructure.query.impl.User_;
import cn.fyg.user.service.UserQuery;
import demo.impl.AbstractQuery;

public class UserQueryImpl extends AbstractQuery<UserQuery,User> implements UserQuery {
	

	public UserQueryImpl(QueryExecutor<User> queryExecutor){
		super(queryExecutor);
	}

	@Override
	public UserQuery username() {
		this.attribute=User_.username.getName();
		return this;
	}

	@Override
	public UserQuery realname() {
		this.attribute=User_.realname.getName();
		return this;
	}

	@Override
	public UserQuery email() {
		this.attribute=User_.email.getName();
		return this;
	}

	@Override
	public UserQuery cellphone() {
		this.attribute=User_.cellphone.getName();
		return this;
	}

}
