package com.jsp.Pharmacy.Application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Pharmacy.Application.entity.Bookings;
import com.jsp.Pharmacy.Application.repo.BookingRepo;

@Repository
public class BookingDao {
	
	@Autowired
		private BookingRepo repo;
	
	public Bookings saveBooking(Bookings bookings) {
		return repo.save(bookings);
	}
}
