package cn.fyg.module.group.impl.domain;

public interface GroupRepository  {
	
	boolean isExist(String key);
	
	GroupEntity find(String key);
	
	GroupEntity persistent(GroupEntity groupEntity);

	GroupEntity update(GroupEntity groupEntity);
}
