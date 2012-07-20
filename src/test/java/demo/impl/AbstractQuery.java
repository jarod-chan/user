package demo.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.user.domain.model.User;
import demo.Query;
import demo.QueryEnum;
import demo.QueryExecutor;
import demo.QueryItem;
import demo.UserQuery;

public abstract class AbstractQuery<T,U> implements Query<T,U> {
	
	List<QueryItem> queryItems=new ArrayList<QueryItem>();
	
	String attribute;
	
	QueryExecutor<User> queryExecutor;

	
	@Override
	public T desc() {
		queryItems.add(new QueryItem(QueryEnum.DESC,this.attribute));
		@SuppressWarnings("unchecked")
		T t= (T)this;
		return t;
	}
	
	@Override
	public T like(Object obj) {
		queryItems.add(new QueryItem(QueryEnum.LIKE,this.attribute,obj));
		return (T)this;
	}

	@Override
	public T equal(Object obj) {
		queryItems.add(new QueryItem(QueryEnum.EQUAL,this.attribute,obj));
		return (T)this;
	}

	@Override
	public T asc() {
		queryItems.add(new QueryItem(QueryEnum.ASC,this.attribute));
		return (T)this;
	}
	
}
