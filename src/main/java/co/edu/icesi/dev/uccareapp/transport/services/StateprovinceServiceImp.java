package co.edu.icesi.dev.uccareapp.transport.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.CountryRegionDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTerritoryDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.repositories.CountryregionRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.SalesTerritoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.StateprovinceRepository;

@Service
public class StateprovinceServiceImp implements StateprovinceService{
	
//	private StateprovinceRepository repo;
//	
//	private SalesTerritoryRepository stRepo;
//	private CountryregionRepository crRepo;
//	
//	
//	@Autowired
//	public StateprovinceServiceImp(StateprovinceRepository repo, SalesTerritoryRepository stRepo, CountryregionRepository crRepo) {
//		this.repo = repo;
//		this.stRepo = stRepo;
//		this.crRepo = crRepo;
//	}

	@Autowired
	private StateProvinceDAO spDAO;
	
	@Autowired
	private SalesTerritoryDAO stDAO;
	
	@Autowired
	private CountryRegionDAO crDAO;
	
	@Autowired
	public StateprovinceServiceImp(StateProvinceDAO spDAO, SalesTerritoryDAO stDAO, CountryRegionDAO crDAO) {
		super();
		this.spDAO = spDAO;
		this.stDAO = stDAO;
		this.crDAO = crDAO;
	}



	@Override
	public Stateprovince saveStateprovince(Stateprovince stateProvince, Integer salesterritoryid, Integer countryregionid) throws FailedValidationsException, ElementNotFoundException {
		Stateprovince result = null;
		
		
		
		if(stateProvince.getStateprovincecode()!=null && stateProvince.getStateprovincecode().length()!=5 ) {
			throw new FailedValidationsException("El codigo del estado-provincia debe tener 5 digitos");
		}
		if(stateProvince.getIsonlystateprovinceflag()!=null && !stateProvince.getIsonlystateprovinceflag().equals("Y/N")){
			throw new FailedValidationsException("El flag de estado debe ser Y/N");
		}
		if( stateProvince.getName()!=null && stateProvince.getName().length()<5) {
			throw new FailedValidationsException("El nombre del estado-provincia debe tener al menos 5 caracteres");
		}
		else {
			Salesterritory opt1 = stDAO.findById(salesterritoryid);
			if(opt1!=null) {
				Countryregion opt2 = crDAO.findById(countryregionid);
				if(opt2!=null) {
					stateProvince.setCountryregion(opt2);
					result = spDAO.save(stateProvince);
				}
				else {
					throw new ElementNotFoundException("El pais-region no existe");
				}
			
			}
			else {
				throw new ElementNotFoundException("El territorio de ventas no existe");
			}
			
		}
		
		return result;
	}





	@Override
	@Transactional
	public Stateprovince editStateprovince(Stateprovince stateProvince, Integer salesterritoryid, Integer countryregionid) throws FailedValidationsException, ElementNotFoundException {
		Stateprovince result = null;
		if(stateProvince.getStateprovinceid()!=null) {
			Stateprovince old = spDAO.findById(stateProvince.getStateprovinceid());
			if(old!=null) {
				result = saveStateprovince(stateProvince,salesterritoryid,countryregionid);
			}
		}
		
		
		return result;
	}

	public Stateprovince findById(Integer id) {
		return spDAO.findById(id);
	}
	
	public Iterable<Stateprovince> findAll() {
		return spDAO.findAll();
	}
	
	@Override
	@Transactional
	public void save(Stateprovince sp) {

		spDAO.save(sp);
	}

	@Transactional
	public Stateprovince edit(Stateprovince sp, Integer countryregionid) {
		Stateprovince actual = null;
		
		if(sp.getStateprovinceid() != null) {
			Stateprovince optional = spDAO.findById(sp.getStateprovinceid());
			if(optional!=null) {
				sp.setCountryregion(crDAO.findById(countryregionid));
//				save(sp);
				actual = spDAO.update(sp);
			}
		}
		
		return actual;
		
	}
}
