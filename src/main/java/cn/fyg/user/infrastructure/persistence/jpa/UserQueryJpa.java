package cn.fyg.user.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.user.domain.model.User;
import cn.fyg.user.domain.model.UserQuery;
import cn.fyg.user.domain.model.User_;

@Repository
public class UserQueryJpa implements UserQuery {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> findByKey(String key) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		Predicate criteria=builder.equal(root.get(User_.username), key);
		criteria=builder.or(criteria,builder.equal(root.get(User_.email), key));
		criteria=builder.or(criteria,builder.equal(root.get(User_.cellphone), key));
		query.where(criteria);
		query.orderBy(builder.asc(root.get(User_.id)));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public boolean multiUser(User user) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		Predicate criteria=builder.equal(root.get(User_.username), user.getUsername());
		criteria=builder.or(criteria,builder.equal(root.get(User_.email), user.getEmail()));
		criteria=builder.or(criteria,builder.equal(root.get(User_.cellphone), user.getCellphone()));
		if(user.getId()!=null){
			criteria=builder.and(criteria,builder.notEqual(root.get(User_.id), user.getId()));
		}
		query.where(criteria);
		List<User> list = entityManager.createQuery(query).getResultList();
		return !list.isEmpty();
	}

}
