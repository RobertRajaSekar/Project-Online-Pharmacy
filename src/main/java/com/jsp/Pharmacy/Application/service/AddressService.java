package com.jsp.Pharmacy.Application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Pharmacy.Application.dao.AddressDao;
import com.jsp.Pharmacy.Application.dto.AddressDto;
import com.jsp.Pharmacy.Application.entity.Address;
import com.jsp.Pharmacy.Application.exception.AddressIdNotFoundException;
import com.jsp.Pharmacy.Application.util.ResponseStructure;

@Service
public class AddressService {
	
	@Autowired
	private AddressDao dao;
	
	@Autowired
	private AddressDto dto;
	
	@Autowired
	private ModelMapper mapper;
	
	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(Address address){
		Address dbAddress=dao.saveAddress(address);
		AddressDto addressDto=this.mapper.map(dbAddress, AddressDto.class);
		ResponseStructure<AddressDto> structure=new ResponseStructure<>();
		structure.setMessage("Address Saved Successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(addressDto);
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(int addressId,Address address){
		Address dbAddress=dao.updateAdress(addressId, address);
		if(dbAddress!=null) {
		AddressDto addressDto=this.mapper.map(dbAddress, AddressDto.class);	
//		id is present
		ResponseStructure<AddressDto> structure=new ResponseStructure<>();
		structure.setMessage("Address Updated Succesfully");
		structure.setHttpStatus(HttpStatus.OK.value());
		structure.setData(addressDto);
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.OK);
		}
		else {
			throw new AddressIdNotFoundException("Sorry Faild to Update the Data");
		}
	}
	public ResponseEntity<ResponseStructure<AddressDto>> findAddress(int addressId){
		Address dbAddress=dao.findAddress(addressId);
		if(dbAddress!=null) {
//			id is present
			AddressDto addressDto=this.mapper.map(dbAddress, AddressDto.class);
			ResponseStructure<AddressDto> structure=new ResponseStructure<>();
			structure.setMessage("Address Fetched Succesfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(addressDto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new AddressIdNotFoundException("Sorry Faild to Fetch the Data");
		}
	}
	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(int addressId){
		Address dbAddress=dao.deleteAddress(addressId);
		if(dbAddress!=null) {
//			id is present
			AddressDto addressDto=this.mapper.map(dbAddress, AddressDto.class);
			ResponseStructure<AddressDto> structure=new ResponseStructure<>();
			structure.setMessage("Address Deleted Succesfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(addressDto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new AddressIdNotFoundException("Sorry Faild to Delete the Data");
		}
	}
	
}
