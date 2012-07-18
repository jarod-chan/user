package demo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import cn.fyg.user.domain.model.User;
import cn.fyg.user.domain.model.User_;
import demo.UserQuery;

public class UserQueryImpl implements UserQuery {
	
	List<QueryItem> queryItems=new ArrayList<QueryItem>();
	
	SingularAttribute<?, ?> attribute;
	

	@Override
	public UserQuery username() {
		this.attribute=User_.id;
		return this;
	}

	@Override
	public UserQuery realname() {
		this.attribute=User_.realname;
		return this;
	}

	@Override
	public UserQuery email() {
		this.attribute=User_.email;
		return this;
	}

	@Override
	public UserQuery cellphone() {
		this.attribute=User_.cellphone;
		return this;
	}

	@Override
	public UserQuery like(Object obj) {
		
		return this;
	}

	@Override
	public UserQuery equal(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserQuery asc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserQuery desc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
