package cn.fyg.user.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import cn.fyg.module.user.User;
import cn.fyg.module.user.UserException;
import cn.fyg.module.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-persistenceProvider.xml")
public class UserServiceTest {
	
	@Resource
	UserService userService;
	
	
	@Test
	public void testSave(){
		User user1=userService.createUser();
		user1.setKey("user1");
		user1.setRealname("陈1");
		user1.setPassword("pwd111");
		user1.setEmail("user1@gmail.com");
		user1.setCellphone("13811112222");
		userService.saveUser(user1);
		
		User user2=userService.createUser();
		user2.setKey("user2");
		user2.setRealname("周2");
		user2.setPassword("pwd222");
		user2.setEmail("user2@gmail.com");
		user2.setCellphone("13812341234");
		userService.saveUser(user2);
		
		assertNotNull(userService.findUser("user1"));
		assertNotNull(userService.findUser("user2"));
		
		
		userService.saveUser(user2);
	}
	
	@Test
	public void login(){
		String key="user1";
		String password="pwd111";
		String  keyReturn=userService.login(key, password);
		assertNotNull(keyReturn);
	}
	
	@Test 
	public void logFail(){
		String key="user1";
		String password="pwd2";
		try{
			userService.login(key, password);
		}catch(UserException e){
			System.out.println(e.getMessage());
			assertNotNull(e);
		}	
	}
	
	@Test 
	public void testSaveFail(){
		User user1=userService.createUser();
		user1.setKey("user1");
		user1.setRealname("陈");
		user1.setPassword("pwd");
		user1.setEmail("user1gmail.com");
		user1.setCellphone("3811112222");
		try {
			userService.saveUser(user1);
		} catch (UserException e) {
			System.out.println(e.getMessage());
			assertNotNull(e);
		}
		
	}
	
	@Test
	public void testQuery(){
		List<User> users=userService.createQuery()
				.userKey().like("use%").equal("user").desc()
				.cellphone().equal("123").asc()
				.list();
		assertTrue(users.isEmpty());
		List<User> user2=userService.createQuery().userKey().equal("user1")
				.list();
		assertNotNull(user2.get(0));
	}
	
	@Test
	public void testQuery2(){
		List<User> users=userService.createQuery().userKey().like("u%").max(2).list();
		assertEquals(2, users.size());
	}
	
	@Test
	public void testEnable(){
		String key="user1";
		User user = userService.findUser(key);
		assertFalse(user.isEnabled());
		userService.enableUser(key);
		user = userService.findUser(key);
		assertTrue(user.isEnabled());
	}
	
	@Test
	public void testPassword(){
		String key="user1";
		User user = userService.findUser(key);
		user.setPassword("pwd222");
		userService.saveUser(user);
		
		String password="pwd222";
		String retKey=userService.login(key, password);
		assertNotNull(retKey);
	}
	
}
