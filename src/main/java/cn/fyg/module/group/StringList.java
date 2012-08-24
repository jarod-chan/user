package cn.fyg.module.group;

import java.util.ArrayList;
import java.util.List;

public class StringList {
	
	private List<String> stringlist;
	
	public StringList(List<String> stringlist) {
		super();
		this.stringlist = stringlist;
	}

	public String single(){
		if(stringlist==null||stringlist.isEmpty()){
			return "";
		}
		return stringlist.get(0);
	}
	
	public List<String> list(){
		if(stringlist==null){
			return new ArrayList<String>();
		}
		return stringlist;
	}
	
	public int count(){
		return this.stringlist.size();
	}

}
