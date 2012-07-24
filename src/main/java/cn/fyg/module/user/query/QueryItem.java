package cn.fyg.module.user.query;




public class QueryItem {

	private QueryEnum queryEnum;
	private String attribute;
	private Object value;
	
	public QueryItem(QueryEnum queryEnum, String attribute,Object value) {
		this.queryEnum = queryEnum;
		this.attribute = attribute;
		this.value=value;
	}

	public QueryEnum queryEnum() {
		return queryEnum;
	}

	public String attribute() {
		return attribute;
	}

	public Object value() {
		return value;
	}
	
}
