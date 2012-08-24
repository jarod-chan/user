package cn.fyg.module.group.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.module.group.Group;
import cn.fyg.module.group.GroupService;
import cn.fyg.module.group.StringList;
import cn.fyg.module.group.impl.domain.GroupEntity;
import cn.fyg.module.group.impl.domain.GroupRepository;
import cn.fyg.module.group.impl.domain.MembershipEntity;
import cn.fyg.module.group.impl.domain.MembershipRepository;

@Service
public class GroupServiceImpl implements GroupService {
	
	public static final String SEPARATE="."; 
	
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	MembershipRepository membershipRepository;
	
	@Override
	public Group createGroup() {
		return new GroupEntity();
	}

	@Override
	@Transactional
	public Group saveGroup(Group group) {
		GroupEntity groupEntity=(GroupEntity)group;
		GroupEntity parentEntity=(GroupEntity)groupEntity.getParent();
		String code=groupEntity.getKey();
		if(parentEntity!=null){
			code=parentEntity.getCode()+SEPARATE+code;
		}
		groupEntity.setCode(code);
		
		if(!groupRepository.isExist(group.getKey())){				
			return groupRepository.persistent(groupEntity);
		}
		return groupRepository.update(groupEntity);
	}

	@Override
	@Transactional
	public Group findGroup(String key) {
		return groupRepository.find(key);
	}

	@Override
	@Transactional
	public void createMembership(String userKey, String groupKey) {
		if(membershipRepository.findByKey(userKey, groupKey)==null){
			String groupCode = groupRepository.find(groupKey).getCode();
			MembershipEntity membershipEntity=new MembershipEntity();
			membershipEntity.setGroupKey(groupKey);
			membershipEntity.setCode(groupCode);
			membershipEntity.setUserKey(userKey);
			membershipRepository.persistent(membershipEntity);
		}
	}

	@Override
	@Transactional
	public void deleteMembership(String userKey, String groupKey) {
		MembershipEntity membershipEntity = membershipRepository.findByKey(userKey, groupKey);
		if(membershipEntity!=null){
			membershipRepository.remove(membershipEntity);
		}		
	}

	@Override
	public StringList groupUserKey(String groupKey) {
		List<MembershipEntity> membershipEntitys=membershipRepository.findByGroupKey(groupKey);
		return new StringList(getUserKeyList(membershipEntitys));
	}

	@Override
	public StringList userGroupKey(String userKey) {
		List<MembershipEntity> membershipEntitys=membershipRepository.findByUserKey(userKey);
		List<String> result = new ArrayList<String>();
		for (MembershipEntity membershipEntity : membershipEntitys) {
			result.add(membershipEntity.getGroupKey());
		}
		return new StringList(result);
	}

	@Override
	public StringList parentUserKey(String groupKey,String userKey) {
		return parentUserKey(groupKey,userKey,1);
	}

	@Override
	public StringList parentUserKey(String groupKey,String userKey,int parentLevel) {
		GroupEntity groupEntity = groupRepository.find(groupKey);
		String rootCode=groupEntity.getCode();
		MembershipEntity membershipEntity=membershipRepository.findMaxCodeLikeGroupKeyAndUserKey(rootCode+SEPARATE, userKey);
		String code=(membershipEntity==null?"":membershipEntity.getCode());
		if(!isLegalCode(rootCode,code,parentLevel)){
			throw new RuntimeException(String.format("code compute error.rootcode:[%s] code[%s]",rootCode,code));
		}
		
		String targetCode = computeTargetCode(parentLevel, code);
		List<MembershipEntity> membershipEntitys=membershipRepository.findByCode(targetCode);
		return new StringList(getUserKeyList(membershipEntitys));
		
	}

	//返回所有用户编码
	private List<String> getUserKeyList(List<MembershipEntity> membershipEntitys) {
		List<String> result = new ArrayList<String>();
		for (MembershipEntity membershipEntity : membershipEntitys) {
			result.add(membershipEntity.getUserKey());
		}
		return result;
	}

	//计算上级编码
	private String computeTargetCode(int parentLevel, String code) {
		String targetCode=code;
		for(int i=0;i<parentLevel;i++){
			targetCode=StringUtils.substringBeforeLast(targetCode, SEPARATE);
		}
		return targetCode;
	}
	
	//判断计算参数是否合法
	private boolean isLegalCode(String rootCode, String code,int parentLevel) {
		if(StringUtils.isNotBlank(rootCode)&&StringUtils.isNotBlank(code)){
			String sub=StringUtils.substringAfter(code, rootCode+SEPARATE);
			if(StringUtils.split(sub, SEPARATE).length>=parentLevel){
				return true;
			}
		}
		return false;
	}

	@Override
	public List<StringList> reportLine(String groupKey, String userKey) {
		GroupEntity groupEntity = groupRepository.find(groupKey);
		String rootCode=groupEntity.getCode();
		MembershipEntity membershipEntity=membershipRepository.findMaxCodeLikeGroupKeyAndUserKey(rootCode+SEPARATE, userKey);
		String code=(membershipEntity==null?"":membershipEntity.getCode());
		if(!isLegalCode(rootCode,code,0)){
			throw new RuntimeException(String.format("code compute error.rootcode:[%s] code[%s]",rootCode,code));
		}
		
		//获得所有编码
		List<String> codeList=new ArrayList<String>();
		String targetCode=code;
		codeList.add(targetCode);
		do{
			targetCode=StringUtils.substringBeforeLast(targetCode, SEPARATE);
			codeList.add(targetCode);
		}while(!rootCode.equals(targetCode));
		
		System.out.println(codeList);
		
		List<MembershipEntity> membershipEntitys = membershipRepository.findByCodeList(codeList);
		return formatResult(membershipEntitys);
	}

	//封装结果集的返回形式
	private List<StringList> formatResult(
			List<MembershipEntity> membershipEntitys) {
		List<StringList> result=new ArrayList<StringList>();
		if(membershipEntitys.size()>0){
			String flagCode="";
			List<String> userKeyList=null;
			for (MembershipEntity membershipEntity : membershipEntitys) {
				if(flagCode!=membershipEntity.getCode()){
					flagCode=membershipEntity.getCode();
					userKeyList=new ArrayList<String>();
					result.add(new StringList(userKeyList));
				}
				userKeyList.add(membershipEntity.getUserKey());
			}
		}
		return result;
	}

}
