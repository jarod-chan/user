package cn.fyg.module.user.query;


import java.util.List;



public interface QueryExecutor<T,U> {

	List<? extends U> executList(T tQuery); 
}
