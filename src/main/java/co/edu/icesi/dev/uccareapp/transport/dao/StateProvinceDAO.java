package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

@Repository
@Scope("singleton")
public class StateProvinceDAO implements IStateProvinceDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Stateprovince entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(Stateprovince entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(Stateprovince entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public Stateprovince findById(Integer codigo) {
		return entityManager.find(Stateprovince.class, codigo);		//codigo? ID?
	}

	@Override
	public List<Stateprovince> findAll() {
		String jpql = "Select a from Stateprovince a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
