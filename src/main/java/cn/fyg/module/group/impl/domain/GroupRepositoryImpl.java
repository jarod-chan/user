package cn.fyg.module.group.impl.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;


@Repository
public class GroupRepositoryImpl implements GroupRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean isExist(String key) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GroupEntity> query = builder.createQuery(GroupEntity.class);
		Root<GroupEntity> from = query.from(GroupEntity.class);
		Predicate criteria=builder.equal(from.get(GroupEntity_.Key), key);
		query.where(criteria);
		List<GroupEntity> resultList = entityManager.createQuery(query).getResultList();
		return !resultList.isEmpty();
	}

	@Override
	public GroupEntity find(String key) {
		 return entityManager.find(GroupEntity.class, key);
	}

	@Override
	public GroupEntity persistent(GroupEntity groupEntity) {
		entityManager.persist(groupEntity);
		return groupEntity;
	}

	@Override
	public GroupEntity update(GroupEntity groupEntity) {
		
		String oldCode=entityManager.find(GroupEntity.class, groupEntity.getKey()).getCode();
		String newCode=groupEntity.getCode();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GroupEntity> query = builder.createQuery(GroupEntity.class);
		Root<GroupEntity> from = query.from(GroupEntity.class);
		Predicate criteria=builder.like(from.get(GroupEntity_.code), oldCode+".%");
		query.where(criteria);
		List<GroupEntity> groups = entityManager.createQuery(query).getResultList();
		for (GroupEntity group : groups) {
			group.setCode(group.getCode().replace(oldCode, newCode));
		}	
		return entityManager.merge(groupEntity);
	}

}
