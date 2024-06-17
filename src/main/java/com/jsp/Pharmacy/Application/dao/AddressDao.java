package com.jsp.Pharmacy.Application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Pharmacy.Application.entity.Address;
import com.jsp.Pharmacy.Application.repo.AddressRepo;

@Repository
public class AddressDao {
	
	@Autowired
	private AddressRepo repo;
	
	public Address saveAddress(Address address) {
		return repo.save(address);
	}
	
	public Address updateAdress(int addressId,Address address) {
		Optional<Address> optional=repo.findById(addressId);
		if(optional.isPresent()) {
			address.setAddressId(addressId);
			return repo.save(address);
		}
		return null;
	}
	
	public Address findAddress(int addressId) {
		Optional<Address> optional=repo.findById(addressId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Address deleteAddress(int addressId) {
		Optional<Address> optional=repo.findById(addressId);
		if(optional.isPresent()) {
//			id is present so i can delete the data
			Address address=optional.get();
			repo.delete(address);
			return address;
		}
		return null;
	}
}
