package cn.fyg.module.group.impl.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.fyg.module.group.Group;

@Entity
public class GroupEntity implements Group {
	
	@Id
	private String Key;
	
	private String name;
	
	@ManyToOne(targetEntity=GroupEntity.class)
	@JoinColumn(name = "parent_id")
	private Group parent;
	
	private String code;

	@Override
	public String getKey() {
		return Key;
	}

	@Override
	public void setKey(String key) {
		Key = key;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Group getParent() {
		return parent;
	}

	@Override
	public void setParent(Group parent) {
		this.parent = parent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
