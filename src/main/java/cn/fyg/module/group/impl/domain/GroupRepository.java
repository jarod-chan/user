package cn.fyg.module.group.impl.domain;

public interface GroupRepository  {
	
	boolean isConflict(GroupEntity groupEntity);
	
	GroupEntity find(String key);
	
	GroupEntity persistent(GroupEntity groupEntity);

	GroupEntity update(GroupEntity groupEntity);
}
