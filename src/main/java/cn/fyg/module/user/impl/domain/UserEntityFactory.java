package cn.fyg.module.user.impl.domain;

public class UserEntityFactory {
	
	public static UserEntity createUserEntity(){
		UserEntity userEntity=new UserEntity();
		userEntity.setEnabled(Boolean.FALSE);
		return userEntity;
	}

}
