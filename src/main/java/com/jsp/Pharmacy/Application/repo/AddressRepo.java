package com.jsp.Pharmacy.Application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Pharmacy.Application.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}
