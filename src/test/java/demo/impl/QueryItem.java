package demo.impl;

import javax.persistence.metamodel.SingularAttribute;

public class QueryItem {

	private QueryEnum queryEnum;
	private SingularAttribute<?, ?> attribute;
	private Object value;
	
	public QueryItem(QueryEnum queryEnum, SingularAttribute<?, ?> attribute,Object value) {
		this.queryEnum = queryEnum;
		this.attribute = attribute;
		this.value=value;
	}

	public QueryEnum queryEnum() {
		return queryEnum;
	}

	public SingularAttribute<?, ?> attribute() {
		return attribute;
	}

	public Object value() {
		return value;
	}
	
}
