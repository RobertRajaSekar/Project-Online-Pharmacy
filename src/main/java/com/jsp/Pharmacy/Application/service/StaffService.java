package com.jsp.Pharmacy.Application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Pharmacy.Application.dao.AdminDao;
import com.jsp.Pharmacy.Application.dao.MedicalStoreDao;
import com.jsp.Pharmacy.Application.dao.StaffDao;
import com.jsp.Pharmacy.Application.dto.AdminDto;
import com.jsp.Pharmacy.Application.dto.MedicalStoreDto;
import com.jsp.Pharmacy.Application.dto.StaffDto;
import com.jsp.Pharmacy.Application.entity.Admin;
import com.jsp.Pharmacy.Application.entity.MedicalStore;
import com.jsp.Pharmacy.Application.entity.Staff;
import com.jsp.Pharmacy.Application.exception.AdminIdNotFoundException;
import com.jsp.Pharmacy.Application.exception.MedicalStoreIdNotFoundException;
import com.jsp.Pharmacy.Application.exception.StaffIdNotFoundException;
import com.jsp.Pharmacy.Application.util.ResponseStructure;

@Service
public class StaffService {
	
	@Autowired
	private StaffDao dao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private MedicalStoreDao storeDao;
	@Autowired
	private StaffDao staffDao;
	@Autowired
	private StaffDto dto;
	@Autowired
	private ModelMapper mapper;
	
	public ResponseEntity<ResponseStructure<StaffDto>> saveStaff(int adminId, int storeId, Staff staff) {
		Admin admin=adminDao.findAdmin(adminId);
		if(admin!=null) {
//			admin is present
			staff.setAdmin(admin);
			MedicalStore store=storeDao.findMedicalStore(storeId);
			if(store!=null) {
//				store is present
				staff.setMedicalStore(store);
				
				Staff dbStaff=staffDao.saveStaff(staff);
				
				StaffDto dto=this.mapper.map(dbStaff, StaffDto.class);
				
				Admin dbAdmin=dbStaff.getAdmin();
				AdminDto adminDto=this.mapper.map(dbAdmin, AdminDto.class);
				MedicalStore dbMedicalStore=dbStaff.getMedicalStore();
				MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
				dto.setAdminDto(adminDto);
				dto.setMedicalStoreDto(medicalStoreDto);
				
				
				ResponseStructure<StaffDto> structure=new ResponseStructure<>();
				structure.setMessage("Staff saved successfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.CREATED);
			}else {
				throw new MedicalStoreIdNotFoundException("Sorry failed to add staff");
			}
		}else {
			throw new AdminIdNotFoundException("Sorry failed to save Staff");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(int staffId, Staff staff) {
		Staff dbStaff=dao.updateStaff(staffId,staff);
		if(dbStaff!=null) {
//			id is present then updated successfully
			StaffDto dto=this.mapper.map(dbStaff, StaffDto.class);
			
			Admin dbAdmin=dbStaff.getAdmin();
			AdminDto adminDto=this.mapper.map(dbAdmin, AdminDto.class);
			MedicalStore dbMedicalStore=dbStaff.getMedicalStore();
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
			dto.setAdminDto(adminDto);
			dto.setMedicalStoreDto(medicalStoreDto);
			
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff data updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);
			
		}else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to update the staff");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<StaffDto>> findStaff(int staffId) {
		Staff dbStaff=dao.findStaff(staffId);
		if(dbStaff!=null) {
//			id is present then fetched successfully
			StaffDto dto=this.mapper.map(dbStaff, StaffDto.class);
			

			Admin dbAdmin=dbStaff.getAdmin();
			AdminDto adminDto=this.mapper.map(dbAdmin, AdminDto.class);
			MedicalStore dbMedicalStore=dbStaff.getMedicalStore();
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
			dto.setAdminDto(adminDto);
			dto.setMedicalStoreDto(medicalStoreDto);
			
			
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff data fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FOUND);
			
		}else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to fetch the staff");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff(int staffId) {
		Staff dbStaff=dao.deleteStaff(staffId);
		if(dbStaff!=null) {
//			id is present then deleted successfully
			StaffDto dto=this.mapper.map(dbStaff, StaffDto.class);

			Admin dbAdmin=dbStaff.getAdmin();
			AdminDto adminDto=this.mapper.map(dbAdmin, AdminDto.class);
			MedicalStore dbMedicalStore=dbStaff.getMedicalStore();
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
			dto.setAdminDto(adminDto);
			dto.setMedicalStoreDto(medicalStoreDto);
			
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff data deleted successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FOUND);
			
		}else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to delete the staff");
		}
	}

}
