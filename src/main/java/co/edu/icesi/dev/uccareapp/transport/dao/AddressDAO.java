package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;




@Repository
@Scope("singleton")
public class AddressDAO implements IAddressDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public AddressDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
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
	public Address findById(Integer code) {
		return entityManager.find(Address.class, code);		//codigo? ID?
	}

	@Override
	public List<Address> findAll() {
		String jpql = "Select a from Address a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
	//Permita que los direcciones puedan 
	//buscarse por la ciudad 
	
	public Address getAddresByCity(String city) {
		String jpql = "SELECT a FROM Address a WHERE a.city =:city";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("city", city);
		Address address = (Address) query.getSingleResult();
		return address;
	}
	
	public List<Address> getAddresByCity2(String city) {
		String jpql = "SELECT a FROM Address a WHERE a.city = '" + city + "'";
		return entityManager.createQuery(jpql,Address.class).getResultList();
	}
	
	//Permita que los direcciones puedan 
	//buscarse por el id del estado-provincia
	public Address getAddressByStateprovince(Integer id) {
		String jpql = "SELECT a FROM Address a WHERE a.stateprovince.stateprovinceid =:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Address address = (Address) query.getSingleResult();
		return address;
	}
	
	public List<Address> getAddressByStateprovince2(Integer id) {
		String jpql = "SELECT a FROM Address a WHERE a.stateprovince.stateprovinceid = '" +id +"'";
		return entityManager.createQuery(jpql,Address.class).getResultList();
	}
}
