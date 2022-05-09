package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;




@Repository
@Scope("singleton")
public class AddressDAO implements IAddressDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Address entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(Address entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(Address entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public Address findById(Integer codigo) {
		return entityManager.find(Address.class, codigo);		//codigo? ID?
	}

	@Override
	public List<Address> findAll() {
		String jpql = "Select a from Address a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
