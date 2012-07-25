package cn.fyg.module.user.query.impl;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.module.user.query.Query;
import cn.fyg.module.user.query.QueryEnum;
import cn.fyg.module.user.query.QueryExecutor;
import cn.fyg.module.user.query.QueryItem;


public abstract class AbstractQuery<T,U> implements Query<T,U> {
	
	protected List<QueryItem> queryItems=new ArrayList<QueryItem>();
	
	protected String attribute;
	
	protected QueryExecutor<T,U> queryExecutor;
	
	protected int firstValue=-1;
	
	protected int maxValue=-1;
	
	public AbstractQuery(QueryExecutor<T, U> queryExecutor) {
		this.queryExecutor = queryExecutor;
	}

	@Override
	public T desc() {
		queryItems.add(new QueryItem(QueryEnum.DESC,this.attribute,null));
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
		queryItems.add(new QueryItem(QueryEnum.ASC,this.attribute,null));
		@SuppressWarnings("unchecked")
		T t= (T)this;
		return t;
	}
	
	@Override
	public T first(int first){
		if(first>=0){			
			this.firstValue=first;
		}
		@SuppressWarnings("unchecked")
		T t= (T)this;
		return t;
	}
	
	@Override
	public T max(int max){
		if(max>0){
			this.maxValue=max;
		}
		@SuppressWarnings("unchecked")
		T t= (T)this;
		return t;
	}
	
	@Override
	public int first() {
		return this.firstValue;
	}
	
	@Override
	public int max() {
		return this.maxValue;
	}
	
	@Override
	public List<QueryItem> queryItems() {
		return this.queryItems;
	}
	
	@Override
	public List<U> list() {
		 @SuppressWarnings("unchecked")
		 List<? extends U> list = queryExecutor.executList((T)this);
		 if(!list.isEmpty()){
			 List<U> retList=new ArrayList<U>(list.size());
			 for (U u : list) {
				 retList.add(u);
			}
			return retList;
		 }
		 return new ArrayList<U>();
	}
	
}
