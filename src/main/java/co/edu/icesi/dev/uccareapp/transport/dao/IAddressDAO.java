package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;


public interface IAddressDAO {
	
	public void save(Address entity);
	public void update(Address entity);
	public void delete(Address entity);
	public Address findById(Integer codigo);
	public List<Address> findAll();


}
