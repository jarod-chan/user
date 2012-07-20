package cn.fyg.user.infrastructure.query;


import java.util.List;

import cn.fyg.user.infrastructure.query.impl.QueryItem;





public interface QueryExecutor<T> {
	
	 List<T> execute(List<QueryItem> queryItems); 

}
