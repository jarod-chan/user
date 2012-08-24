package cn.fyg.module.group;

import java.util.List;

public interface GroupService {
	
	Group createGroup();
	
	Group saveGroup(Group group);
	
	Group findGroup(String key);
	
	void createMembership(String userKey, String groupKey);

	void deleteMembership(String userKey, String groupKey);
	
	StringList groupUserKey(String groupKey);
	
	StringList userGroupKey(String userKey);
	
	StringList parentUserKey(String groupKey,String userKey);
	
	StringList parentUserKey(String groupKey,String userKey,int parentLevel);
	
	List<StringList> reportLine(String groupKey,String userKey);
}
