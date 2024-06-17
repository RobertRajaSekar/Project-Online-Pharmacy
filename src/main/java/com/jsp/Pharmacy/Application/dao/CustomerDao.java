package com.jsp.Pharmacy.Application.dao;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Pharmacy.Application.entity.Customer;
import com.jsp.Pharmacy.Application.repo.CustomerRepo;

@Repository
public class CustomerDao {
	
	@Autowired
	private CustomerRepo repo;
	
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	
	public Customer updateCustomer(int customerId, Customer customer) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
//			customer is present
			
//			before updating the data i need to set the relationship entities
			customer.setAddresses(optional.get().getAddresses());
			customer.setBookings(optional.get().getBookings());
			customer.setCustomerId(customerId);
			
			return repo.save(customer);
		}
		return null;
	}
	
	
	public Customer findCustomer(int customerId) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
//			customer is present
			
			return optional.get();
		}
		return null;
	}
	
	
	public Customer deleteCustomer(int customerId) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
//			customer is present
			repo.deleteById(customerId);
			return optional.get();
		}
		return null;
	}
	
	
	public Customer findCustomerByEmail(String email) {
		Optional<Customer> optional=repo.findbyEmail(email);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
