package cn.fyg.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.module.group.Group;
import cn.fyg.module.group.GroupService;
import cn.fyg.module.group.StringList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-persistenceProvider.xml")
public class GruopServiceTest {
	
	@Autowired
	GroupService groupService;
	
	@Test
	public void  saveGroup(){
		Group fc=groupService.createGroup();
		fc.setKey("fc");
		fc.setName("房产");
		fc.setParent(null);
		
		groupService.saveGroup(fc);
		
		Group gm=groupService.createGroup();
		gm.setKey("gm");
		gm.setName("分管副总");
		gm.setParent(fc);
		
		groupService.saveGroup(gm);
		
		Group office=groupService.createGroup();
		office.setKey("office");
		office.setName("办公室");
		office.setParent(gm);
		
		groupService.saveGroup(office);
		
		Group staff=groupService.createGroup();
		staff.setKey("staff");
		staff.setName("办公室职员");
		staff.setParent(office);
		groupService.saveGroup(staff);
	}
	
	@Test
	public void testFind(){
		Group staff = groupService.findGroup("staff");
		assertNotNull(staff);
		assertNotNull(staff.getParent());
		assertNotNull(staff.getParent().getParent());
		assertNotNull(staff.getParent().getParent().getParent());
		System.out.println(staff.getName());
		System.out.println(staff.getParent().getName());
		System.out.println(staff.getParent().getParent().getName());
		System.out.println(staff.getParent().getParent().getParent().getName());
	}
	
	@Test
	public void testUser(){
		String staff="staff";
		groupService.createMembership("chen", staff);
		groupService.createMembership("mao", staff);
		groupService.createMembership("zhou", staff);
		groupService.createMembership("quan", staff);
		
		groupService.deleteMembership("chen", staff);
		
		StringList userKeys = groupService.groupUserKey(staff);
		assertEquals(3, userKeys.count());
		System.out.println(userKeys);
		
		String office="office";
		groupService.createMembership("mou", office);
		
		String gm="gm";
		groupService.createMembership("mou", gm);
		
		String fc="fc";
		groupService.createMembership("chen", fc);
		
		String parent=groupService.parentUserKey(fc, "mao").single();
		assertEquals("mou", parent);
		
		parent=groupService.parentUserKey(fc, "mao",2).single();
		assertEquals("mou", parent);
		
		parent=groupService.parentUserKey(fc, "mao",3).single();
		assertEquals("chen", parent);
		
		List<StringList> reportLine = groupService.reportLine("fc", "mao");
		assertEquals("mao",reportLine.get(0).single());
		assertEquals("mou",reportLine.get(1).single());
		assertEquals("mou",reportLine.get(2).single());
		assertEquals("chen",reportLine.get(3).single());
		
		reportLine = groupService.reportLine("gm", "mao");
		assertEquals(3,reportLine.size());
		assertEquals("mao",reportLine.get(0).single());
		assertEquals("mou",reportLine.get(1).single());
		assertEquals("mou",reportLine.get(2).single());
	
		
		
	}
	
	@Test
	public void updateGroup(){
		Group group = groupService.createGroup();
		group.setKey("jz");
		group.setName("建筑");
		group.setParent(null);
		groupService.saveGroup(group);
		
		Group gm=groupService.findGroup("gm");
		gm.setParent(group);
		groupService.saveGroup(gm);
	}
	
	@Test
	public void teatAfterUpdate(){
		Group gm=groupService.findGroup("gm");
		assertEquals("jz",gm.getParent().getKey());
		
		String jz="jz";
		String parent=groupService.parentUserKey(jz, "mao",2).single();
		assertEquals("mou",parent);
		
		groupService.createMembership("t1", "jz");
		groupService.createMembership("t2", "jz");
		
		
		List<StringList> reportLine = groupService.reportLine(jz, "mao");
		assertEquals(4,reportLine.size());
		assertEquals(Arrays.asList("t1","t2"),reportLine.get(3).list());
		
		
	}
	
	@Test 
	public void testSepecial(){
		String jz="jz";
		try{
			groupService.reportLine(jz, "mao1");
		}catch(Exception e){
			assertNotNull(e);
		}
		
	}


}
