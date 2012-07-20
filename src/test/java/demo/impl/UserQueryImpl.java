package demo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import cn.fyg.user.domain.model.User;
import cn.fyg.user.domain.model.User_;
import demo.Query;
import demo.QueryEnum;
import demo.QueryExecutor;
import demo.QueryItem;
import demo.UserQuery;

public class UserQueryImpl extends AbstractQuery<UserQuery,User> implements UserQuery {
	

	
	public UserQueryImpl(QueryExecutor<User> queryExecutor){
		this.queryExecutor=queryExecutor;
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


	

	@Override
	public List<User> list() {
		return queryExecutor.execute(queryItems);
	}

}
