package cn.fyg.module.user.query;

import java.util.List;

public interface Query<T,U> {

	T asc();

	T desc();
	
	T like(Object obj);
	
	T equal(Object obj);
	
	List<U> list();

}