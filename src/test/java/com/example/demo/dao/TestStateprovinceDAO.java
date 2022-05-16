package com.example.demo.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.CountryRegionDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.repositories.SalesTerritoryRepository;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class TestStateprovinceDAO {

	@Autowired
	private StateProvinceDAO spDAO;

	private Stateprovince sp;

	@Autowired
	private CountryRegionDAO crDAO;

	private Countryregion cr;

	private SalesTerritoryRepository stRepo;	
	
	private Salesterritory st;
	
	@Autowired
	public TestStateprovinceDAO(SalesTerritoryRepository stRepo) {
		this.stRepo = stRepo;
	}

	@BeforeEach
	void initDao() { 
		sp = new Stateprovince();
		sp.setName("Valle del Cauca");
		sp.setStateprovincecode("12345");
	}

	void initCountryregion() {
		cr = new Countryregion();
		cr.setName("Colombia");
		cr.setCountryregioncode("C12");

		crDAO.save(cr);

		sp.setCountryregion(cr);
		spDAO.save(sp);
	}
	
	void initSalesterritory() {
		st = new Salesterritory();
    	st.setName("Zona del pacifico");
    	
    	stRepo.save(st);
    	
    	sp.setTerritoryid(st.getTerritoryid());
    	spDAO.save(sp);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findSPByIdTest() {
		spDAO.save(sp);
		//System.out.println(address.getAddressid()+"");

		assertEquals(spDAO.findById(sp.getStateprovinceid()),sp);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveSPTest(){
		spDAO.save(sp);

		assertTrue(spDAO.findById(sp.getStateprovinceid()).equals(sp));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateSPTest() {
		spDAO.save(sp);
		sp.setName("Antioquia");
		sp.setStateprovincecode("98765");

		spDAO.update(sp);

		Stateprovince edited = spDAO.findById(sp.getStateprovinceid());


		assertAll(
				() -> assertEquals(edited.getName(),"Antioquia"),
				() -> assertEquals(edited.getStateprovincecode(),"98765")
				);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteSPTest() {
		spDAO.save(sp);

		spDAO.delete(sp);

		assertNull(spDAO.findById(sp.getStateprovinceid()));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findAllAddressesTest() {
		spDAO.save(sp);

		assertThat(spDAO.findAll().size(), equalTo(2));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getSPByName() {
		spDAO.save(sp);

		sp.setName("Valle del Cauca");
		sp.setStateprovincecode("22333");

		spDAO.save(sp);

		List<Stateprovince> results = spDAO.getStateprovinceByName2("Valle del Cauca");
		assertThat(results.size(), equalTo(2));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getSPByCountryregion() {
		initCountryregion();

		sp = new Stateprovince();
		sp.setName("Antioquia");
		sp.setStateprovincecode("98765");
		
		sp.setCountryregion(cr);
		spDAO.save(sp);
		
		List<Stateprovince> results = spDAO.getStateprovinceByCountryregion2(cr.getCountryregionid());
		assertThat(results.size(), equalTo(2));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getSPByTerritoryid() {
		initSalesterritory();
		
		sp = new Stateprovince();
		sp.setName("Antioquia");
		sp.setStateprovincecode("98765");
		sp.setTerritoryid(st.getTerritoryid());
		
		spDAO.save(sp);
		
		List<Stateprovince> results = spDAO.getStateprovinceByTerritoryId2(st.getTerritoryid());
		assertThat(results.size(), equalTo(2));
	}

}
