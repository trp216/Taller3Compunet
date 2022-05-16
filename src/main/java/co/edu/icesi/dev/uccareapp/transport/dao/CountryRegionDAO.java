package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;

@Repository
@Transactional
@Scope("singleton")
public class CountryRegionDAO implements ICountryRegionDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Countryregion entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(Countryregion entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(Countryregion entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public Countryregion findById(Integer codigo) {
		return entityManager.find(Countryregion.class, codigo);		//codigo? ID?
	}

	@Override
	public List<Countryregion> findAll() {
		String jpql = "Select a from Countryregion a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
