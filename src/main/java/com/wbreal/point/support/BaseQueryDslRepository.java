package com.wbreal.point.support;

import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public abstract class BaseQueryDslRepository extends QuerydslRepositorySupport {

	public BaseQueryDslRepository(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Querydsl getQuerydsl() {
		return Objects.requireNonNull(super.getQuerydsl());
	}

	@Override
	public EntityManager getEntityManager() {
		return Objects.requireNonNull(super.getEntityManager());
	}

	@PersistenceContext(unitName = "point")
	public void setEntityManager(@NotNull EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
