package cn.fyg.module.user.query;


import java.util.List;

import cn.fyg.module.user.query.impl.QueryItem;


public interface QueryExecutor<T> {
	
	 List<T> execute(List<QueryItem> queryItems); 

}
