package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;


public interface IStateProvinceDAO {

	public void save(Stateprovince entity);
	public void update(Stateprovince entity);
	public void delete(Stateprovince entity);
	public Stateprovince findById(Integer codigo);
	public List<Stateprovince> findAll();

}
