package demo;

import java.util.List;




public interface QueryExecutor<T> {
	
	 List<T> execute(List<QueryItem> queryItems); 

}
