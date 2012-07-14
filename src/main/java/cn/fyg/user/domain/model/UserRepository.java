package cn.fyg.user.domain.model;


public interface UserRepository {

	User find(Long id);
	
	User save(User user);

}
