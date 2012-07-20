package cn.fyg.user.infrastructure.persistence.jpa;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.user.domain.model.User;
import cn.fyg.user.domain.model.UserRepository;
import cn.fyg.user.domain.model.User_;
import cn.fyg.user.infrastructure.query.impl.QueryItem;

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

	@Override
	public List<User> execute(List<QueryItem> queryItems) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		List<Predicate> criterias=new ArrayList<Predicate>();
		List<Order> orders=new ArrayList<Order>();
		for (QueryItem queryItem : queryItems) {
			switch(queryItem.queryEnum()){
				case EQUAL:
					criterias.add(builder.equal(root.get(queryItem.attribute()), queryItem.value()));
					break;
				case LIKE:
					criterias.add(builder.like(root.<String>get(queryItem.attribute()), queryItem.value().toString()));
					break;
				case ASC:
					orders.add(builder.asc(root.get(queryItem.attribute())));
					break;
				case DESC:
					orders.add(builder.desc(root.get(queryItem.attribute())));	
					break;
			}
		}
		if(!criterias.isEmpty()){
			if(criterias.size()==1){
				query.where(criterias.get(0));
			}else{
				query.where(builder.and(criterias.toArray(new Predicate[0])));
			}
		}
		if(!orders.isEmpty()){
			query.orderBy(orders);
		}
		return entityManager.createQuery(query).getResultList();
	}



}
