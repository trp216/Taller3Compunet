package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;

@Repository
@Transactional
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
		String jpql = "Select str from Salestaxrate str";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
	//Permita que las tasas impositivas de ventas puedan 
	//buscarse por id del estadoprovincia
	
	public Salestaxrate getSalestaxrateByStateprovince(Integer id) {
		String jpql = "SELECT str FROM Salestaxrate str WHERE str.stateprovince.stateprovinceid =:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Salestaxrate salestaxrate = (Salestaxrate) query.getSingleResult();
		return salestaxrate;
	}
	
	public List<Salestaxrate> getSalestaxrateByStateprovince2(Integer id) {
		String jpql = "SELECT str FROM Salestaxrate str WHERE str.stateprovince.stateprovinceid = '"+id+"'";
		return entityManager.createQuery(jpql,Salestaxrate.class).getResultList();
	}
	
	
	//Permita que las tasas impositivas de ventas puedan 
	//buscarse por  el nombre 
	
	public Salestaxrate getSalestaxrateByName(String name) {
		String jpql = "SELECT str FROM Salestaxrate str WHERE str.name =:name";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("name", name);
		Salestaxrate salestaxrate = (Salestaxrate) query.getSingleResult();
		return salestaxrate;
	}
	
	public List<Salestaxrate> getSalestaxrateByName2(String name) {
		String jpql = "SELECT str FROM Salestaxrate str WHERE str.name = '"+ name + "'";
		return entityManager.createQuery(jpql,Salestaxrate.class).getResultList();
	}
}

