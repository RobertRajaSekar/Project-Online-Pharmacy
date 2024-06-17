package com.jsp.Pharmacy.Application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Pharmacy.Application.entity.Address;
import com.jsp.Pharmacy.Application.entity.MedicalStore;
import com.jsp.Pharmacy.Application.repo.MedicalStoreRepo;

@Repository
public class MedicalStoreDao {

	@Autowired
	private MedicalStoreRepo repo;
	
	public MedicalStore saveMedicalStore(MedicalStore store) {
		return repo.save(store);	
	}
	
	public MedicalStore updateMedicalStore(int storeId,MedicalStore store){ 
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
//			id is present then i can update the data
//			before calling update method i need to set the other relationship entity
			store.setAddress(optional.get().getAddress());
			store.setAdmin(optional.get().getAdmin());
			return repo.save(store);
		}
	  return null;
	}
	
	public MedicalStore findMedicalStore(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public MedicalStore deleteMedicalStore(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			MedicalStore medicalStore=optional.get();
			Address address=medicalStore.getAddress();
			address.setMedicalStore(null);
			repo.deleteById(storeId);
			return optional.get();
		}
		return null;
	}
}
