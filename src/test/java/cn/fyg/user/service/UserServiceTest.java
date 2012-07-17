package cn.fyg.user.service;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.user.domain.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-persistenceProvider.xml")
public class UserServiceTest {
	
	@Resource
	UserService userService;
	
	
	@Test
	public void testSave(){
		User user1=new User();
		user1.setUsername("user1");
		user1.setRealname("陈1");
		user1.setPassword("pwd111");
		user1.setEmail("user1@gmail.com");
		user1.setCellphone("13811112222");
		userService.saveUser(user1);
		
		User user2=new User();
		user2.setUsername("user2");
		user2.setRealname("周2");
		user2.setPassword("pwd222");
		user2.setEmail("user2@gmail.com");
		user2.setCellphone("13812341234");
		user2=userService.saveUser(user2);
		
		assertNotNull(userService.findById(1L));
		assertNotNull(userService.findById(2L));
		
		
		userService.saveUser(user2);
	}
	
	@Test
	public void login(){
		String key="user1";
		String password="pwd111";
		Long  id=userService.login(key, password);
		assertNotNull(id);
	}
	
	@Test 
	public void logFail(){
		String key="user1";
		String password="pwd2";
		try{
			userService.login(key, password);
		}catch(UserException e){
			assertNotNull(e);
		}
		
	}
	
	@Test 
	public void testSaveFail(){
		User user1=new User();
		user1.setUsername("user1");
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
	

}
