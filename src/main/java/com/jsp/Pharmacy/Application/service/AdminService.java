package com.jsp.Pharmacy.Application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Pharmacy.Application.dao.AdminDao;
import com.jsp.Pharmacy.Application.dto.AdminDto;
import com.jsp.Pharmacy.Application.entity.Admin;
import com.jsp.Pharmacy.Application.exception.AdminIdNotFoundException;
import com.jsp.Pharmacy.Application.util.ResponseStructure;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao dao;
	
	@Autowired
	private AdminDto dto;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin){
		Admin dbAdmin=dao.saveAdmin(admin);
		AdminDto dto=this.modelMapper.map(dbAdmin, AdminDto.class);
		
		
		ResponseStructure<AdminDto> structure=new ResponseStructure<>();
		structure.setMessage("Admin data saved sucessfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(int adminId,Admin admin){
		Admin dbAdmin=dao.updateAdmin(adminId, admin);
		if(dbAdmin!=null) {
//			that admin is there and data updated successfully
			AdminDto dto=this.modelMapper.map(dbAdmin, AdminDto.class);
			ResponseStructure<AdminDto> structure=new ResponseStructure<>();
			structure.setMessage("Admin data updated sucessfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		else {
//			that id is not present and data not updated
//			raise the exception
//			how to raise the exception
			throw new AdminIdNotFoundException("Sorry Faild to update the data");
		}
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(int adminId){
		Admin dbAdmin=dao.findAdmin(adminId);
		if(dbAdmin!=null) {
//			that admin is there and data fetched successfully
			ResponseStructure<AdminDto> structure=new ResponseStructure<>();
			structure.setMessage("Admin data fetched sucessfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}
		else {
//			that id is not present and data not updated
//			raise the exception
//			how to raise the exception
			throw new AdminIdNotFoundException("Sorry Faild to fetch the data");
			
		}
	}
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(int adminId){
		Admin dbAdmin=dao.deleteAdmin(adminId);
		if(dbAdmin!=null) {
//			that admin is there and data deleted successfully
			ResponseStructure<AdminDto> structure=new ResponseStructure<>();
			structure.setMessage("Admin data deleted sucessfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}
		else {
//			that id is not present and data not updated
//			raise the exception
//			how to raise the exception
			throw new AdminIdNotFoundException("Sorry Faild to delete the data");
		}
	}

}
