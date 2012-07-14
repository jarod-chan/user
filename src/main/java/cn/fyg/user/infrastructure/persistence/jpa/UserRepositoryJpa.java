package cn.fyg.user.infrastructure.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.user.domain.model.User;
import cn.fyg.user.domain.model.UserRepository;

@Repository
public class UserRepositoryJpa implements UserRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User find(Long id) {
		return entityManager.find(User.class, id);
	}
	
	@Override
	public User save(User user) {
		if(user.getId()==null){
			entityManager.persist(user);
			return user;
		}
		return entityManager.merge(user);
	}

}
