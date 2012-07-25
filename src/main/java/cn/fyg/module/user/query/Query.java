package cn.fyg.module.user.query;

import java.util.List;


public interface Query<T,U> {

	T asc();

	T desc();
	
	T like(Object obj);
	
	T equal(Object obj);
	
	T first(int first);
	
	T max(int max);
	
	int first();
	
	int max();
	
	List<QueryItem> queryItems();
	
	List<U> list();
	
	

}