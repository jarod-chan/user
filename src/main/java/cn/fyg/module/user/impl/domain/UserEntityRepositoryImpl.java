package cn.fyg.module.user.impl.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.module.user.User;
import cn.fyg.module.user.UserQuery;
import cn.fyg.module.user.query.QueryItem;

@Repository
public class UserEntityRepositoryImpl implements UserEntityRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserEntity find(String key) {
		return entityManager.find(UserEntity.class, key);
	}
	
	@Override
	public UserEntity save(UserEntity user) {
		if(user.getUuid()==null){
			entityManager.persist(user);
			return user;
		}
		return entityManager.merge(user);
	}
	
	@Override
	public List<UserEntity> findByKey(String key) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
		Root<UserEntity> root = query.from(UserEntity.class);
		Predicate criteria=builder.equal(root.get(UserEntity_.key), key);
		criteria=builder.or(criteria,builder.equal(root.get(UserEntity_.email), key));
		criteria=builder.or(criteria,builder.equal(root.get(UserEntity_.cellphone), key));
		query.where(criteria);
		query.orderBy(builder.asc(root.get(UserEntity_.key)));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public boolean isConflict(UserEntity user) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
		Root<UserEntity> root = query.from(UserEntity.class);
		Predicate criteria=builder.equal(root.get(UserEntity_.key), user.getKey());
		criteria=builder.or(criteria,builder.equal(root.get(UserEntity_.email), user.getEmail()));
		criteria=builder.or(criteria,builder.equal(root.get(UserEntity_.cellphone), user.getCellphone()));
		if(user.getUuid()!=null){
			criteria=builder.and(criteria,builder.notEqual(root.get(UserEntity_.uuid), user.getUuid()));
		}
		query.where(criteria);
		List<UserEntity> list = entityManager.createQuery(query).getResultList();
		return !list.isEmpty();
	}

	@Override
	public List<? extends User> executList(UserQuery tQuery) {
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
		Root<UserEntity> root = query.from(UserEntity.class);
		List<Predicate> criterias=new ArrayList<Predicate>();
		List<Order> orders=new ArrayList<Order>();
		for (QueryItem queryItem : tQuery.queryItems()) {
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
				default:
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
		TypedQuery<UserEntity> typedQuery = entityManager.createQuery(query);
		if(tQuery.first()>0){
			typedQuery=typedQuery.setFirstResult(tQuery.first());
		}
		if(tQuery.max()>0){
			typedQuery=typedQuery.setMaxResults(tQuery.max());
		}
		return typedQuery.getResultList();
	}





}
