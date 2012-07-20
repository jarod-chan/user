package cn.fyg.user.infrastructure.query.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.user.infrastructure.query.Query;
import cn.fyg.user.infrastructure.query.QueryExecutor;


public abstract class AbstractQuery<T,U> implements Query<T,U> {
	
	protected List<QueryItem> queryItems=new ArrayList<QueryItem>();
	
	protected String attribute;
	
	protected QueryExecutor<U> queryExecutor;
	
	public AbstractQuery(QueryExecutor<U> queryExecutor) {
		this.queryExecutor = queryExecutor;
	}

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
		@SuppressWarnings("unchecked")
		T t= (T)this;
		return t;
	}

	@Override
	public T equal(Object obj) {
		queryItems.add(new QueryItem(QueryEnum.EQUAL,this.attribute,obj));
		@SuppressWarnings("unchecked")
		T t= (T)this;
		return t;
	}

	@Override
	public T asc() {
		queryItems.add(new QueryItem(QueryEnum.ASC,this.attribute));
		@SuppressWarnings("unchecked")
		T t= (T)this;
		return t;
	}
	
	@Override
	public List<U> list() {
		return queryExecutor.execute(queryItems);
	}
	
}
