package cn.fyg.module.user.query;


import java.util.List;



public interface QueryExecutor<U> {
	
	 List<U> executeList(List<QueryItem> queryItems); 

}
