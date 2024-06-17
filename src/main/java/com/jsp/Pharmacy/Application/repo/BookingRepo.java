package com.jsp.Pharmacy.Application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Pharmacy.Application.entity.Bookings;

public interface BookingRepo extends JpaRepository<Bookings, Integer>{

}
