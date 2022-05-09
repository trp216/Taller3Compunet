package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;

@Repository
@Scope("singleton")
public class SalesTaxRateDAO implements ISalesTaxRateDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Salestaxrate entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(Salestaxrate entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(Salestaxrate entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public Salestaxrate findById(Integer codigo) {
		return entityManager.find(Salestaxrate.class, codigo);		//codigo? ID?
	}

	@Override
	public List<Salestaxrate> findAll() {
		String jpql = "Select a from Salestaxrate a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}

