package com.jsp.Pharmacy.Application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Pharmacy.Application.dao.AddressDao;
import com.jsp.Pharmacy.Application.dao.AdminDao;
import com.jsp.Pharmacy.Application.dao.MedicalStoreDao;
import com.jsp.Pharmacy.Application.dto.AddressDto;
import com.jsp.Pharmacy.Application.dto.AdminDto;
import com.jsp.Pharmacy.Application.dto.MedicalStoreDto;
import com.jsp.Pharmacy.Application.entity.Address;
import com.jsp.Pharmacy.Application.entity.Admin;
import com.jsp.Pharmacy.Application.entity.MedicalStore;
import com.jsp.Pharmacy.Application.exception.AddressIdNotFoundException;
import com.jsp.Pharmacy.Application.exception.AdminIdNotFoundException;
import com.jsp.Pharmacy.Application.exception.MedicalStoreIdNotFoundException;
import com.jsp.Pharmacy.Application.util.ResponseStructure;

@Service
public class MedicalStoreService {

	@Autowired
	private MedicalStoreDao dao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private MedicalStoreDto dto;
	
	@Autowired
	private AddressDto addressDto;
	
	@Autowired
	private AdminDto adminDto;
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalStore(int adminId,int addressId,MedicalStore store){
		Admin dbAdmin=adminDao.findAdmin(adminId);
		if(dbAdmin!=null) {
//			admin is present and he is a valid admin
//			then i am going to check address
			
			Address dbAddress=addressDao.findAddress(addressId);
			if(dbAddress!=null) {
//				address is present then you can add store
				store.setAdmin(dbAdmin);
				store.setAddress(dbAddress);
				dbAddress.setMedicalStore(store);
				MedicalStore dbMedicalStore=dao.saveMedicalStore(store);
				
				dto.setManagerName(dbMedicalStore.getManagerName());
				dto.setName(dbMedicalStore.getName());
				dto.setPhone(dbMedicalStore.getPhone());
				dto.setStoreId(dbMedicalStore.getStoreId());
				
				Address address=dbMedicalStore.getAddress();
				addressDto.setAddressId(address.getAddressId());
				addressDto.setCity(address.getCity());
				addressDto.setPincode(address.getPincode());
				addressDto.setState(address.getState());
				addressDto.setStreetName(address.getStreetName());
				
				dto.setAddressDto(addressDto);
				
				Admin admin=dbMedicalStore.getAdmin();
				adminDto.setAdminEmail(admin.getAdminEmail());
				adminDto.setAdminId(admin.getAdminId());
				adminDto.setAdminName(admin.getAdminName());
				
				dto.setAdminDto(adminDto);
				
				ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
				structure.setMessage("Medical Store Saved Succesfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.CREATED);
				
			}else {
//				admin is the valid admin but address not found
				throw new AddressIdNotFoundException("Sorry Failed to Open the MedicalStore");
			}
		}else {
//			admin is not present and then he is not a valid admin
			throw new AdminIdNotFoundException("Sorry Failed to Open the MedicalStore");
		}
	}
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId,MedicalStore store){
		MedicalStore dbMedicalStore=dao.updateMedicalStore(storeId, store);
		if(dbMedicalStore!=null) {
//			updated successfully
			
			dto.setManagerName(dbMedicalStore.getManagerName());
			dto.setName(dbMedicalStore.getName());
			dto.setPhone(dbMedicalStore.getPhone());
			dto.setStoreId(dbMedicalStore.getStoreId());
			
			Address address=dbMedicalStore.getAddress();
			addressDto.setAddressId(address.getAddressId());
			addressDto.setCity(address.getCity());
			addressDto.setPincode(address.getPincode());
			addressDto.setState(address.getState());
			addressDto.setStreetName(address.getStreetName());
			
			dto.setAddressDto(addressDto);
			
			Admin admin=dbMedicalStore.getAdmin();
			adminDto.setAdminEmail(admin.getAdminEmail());
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setAdminName(admin.getAdminName());
			
			dto.setAdminDto(adminDto);
			
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("Medical Store Updated Succesfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.OK);
		}
		
		else {
			throw new MedicalStoreIdNotFoundException("Sorry Filed to Update The Medcal Store");
		}
	}
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStore(int storeId){
		MedicalStore dbMedicalStore=dao.findMedicalStore(storeId);
		if(dbMedicalStore!=null) {
//			Fetched Successfully
			dto.setManagerName(dbMedicalStore.getManagerName());
			dto.setName(dbMedicalStore.getName());
			dto.setPhone(dbMedicalStore.getPhone());
			dto.setStoreId(dbMedicalStore.getStoreId());
			
			Address address=dbMedicalStore.getAddress();
			addressDto.setAddressId(address.getAddressId());
			addressDto.setCity(address.getCity());
			addressDto.setPincode(address.getPincode());
			addressDto.setState(address.getState());
			addressDto.setStreetName(address.getStreetName());
			
			dto.setAddressDto(addressDto);
			
			Admin admin=dbMedicalStore.getAdmin();
			adminDto.setAdminEmail(admin.getAdminEmail());
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setAdminName(admin.getAdminName());
			
			dto.setAdminDto(adminDto);
			
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("Medical Store Fetched Succesfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new MedicalStoreIdNotFoundException("Sorry Failed Fetch the Medical Store");
		}
	}
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStore(int storeId){
		MedicalStore dbMedicalStore=dao.deleteMedicalStore(storeId);
		if(dbMedicalStore!=null) {
//			Deleted Successfully
			
			dto.setManagerName(dbMedicalStore.getManagerName());
			dto.setName(dbMedicalStore.getName());
			dto.setPhone(dbMedicalStore.getPhone());
			dto.setStoreId(dbMedicalStore.getStoreId());
			
			Address address=dbMedicalStore.getAddress();
			addressDto.setAddressId(address.getAddressId());
			addressDto.setCity(address.getCity());
			addressDto.setPincode(address.getPincode());
			addressDto.setState(address.getState());
			addressDto.setStreetName(address.getStreetName());
			
			dto.setAddressDto(addressDto);
			
			Admin admin=dbMedicalStore.getAdmin();
			adminDto.setAdminEmail(admin.getAdminEmail());
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setAdminName(admin.getAdminName());
			
			dto.setAdminDto(adminDto);
			
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("Medical Store Deleted Succesfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new MedicalStoreIdNotFoundException("Sorry Failed to Delete The Medical Store");
		}
	}
}
