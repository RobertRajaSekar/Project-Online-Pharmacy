package com.jsp.Pharmacy.Application.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Pharmacy.Application.dao.BookingDao;
import com.jsp.Pharmacy.Application.dao.CustomerDao;
import com.jsp.Pharmacy.Application.dao.MedicineDao;
import com.jsp.Pharmacy.Application.dto.BookingDto;
import com.jsp.Pharmacy.Application.entity.Bookings;
import com.jsp.Pharmacy.Application.entity.Customer;
import com.jsp.Pharmacy.Application.entity.Medicine;
import com.jsp.Pharmacy.Application.enums.BookingStatus;
import com.jsp.Pharmacy.Application.exception.CustomerIdNotFoundException;
import com.jsp.Pharmacy.Application.exception.MedicineIdNotFoundException;
import com.jsp.Pharmacy.Application.util.ResponseStructure;

@Service
public class BookingService {

	@Autowired
	private BookingDao dao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private MedicineDao medicineDao;
	
	
	public ResponseEntity<ResponseStructure<Bookings>> addBooking(int customerId,int [] medicineId,BookingDto bookingDto){
		Customer dbCustomer=customerDao.findCustomer(customerId);
		if(dbCustomer!=null) {
//			that customer is present and you can add booking
			Bookings bookings=this.mapper.map(bookingDto, Bookings.class);
			bookings.setCustomer(dbCustomer);
			List<Medicine> list=new ArrayList<Medicine>();
			
			for(int medId:medicineId) {
				Medicine dbMedicine=medicineDao.findMedicine(medId);
				if(dbMedicine!=null) {
//					that medicine is present
					list.add(dbMedicine);
//					update the Stock Quantity of Medicine
					dbMedicine.setStockQuantity(dbMedicine.getStockQuantity());
				}
				else {
//					medicine is not present
					throw new MedicineIdNotFoundException("Sorry Failed to Add Bookings");
				}
			}
//			end of for each loop
			
//			set the list of medicines to your booking
			bookings.setMedicines(list);
//			update customer also with respect to bookings
			List<Bookings> bookings2=new ArrayList<Bookings>();
			bookings2.add(bookings);
			dbCustomer.setBookings(bookings2);
			customerDao.updateCustomer(customerId, dbCustomer);
//			decide the booking status
			bookings.setBookingStatus(BookingStatus.ACTIVE);
			
//			shall i save this booking
			
			Bookings dbBookings=dao.saveBooking(bookings);
			ResponseStructure<Bookings> structure=new ResponseStructure<>();
			structure.setMessage("Booking Saved Success Fully");
			structure.setHttpStatus(HttpStatus.CREATED.value());
			structure.setData(dbBookings);
			return new ResponseEntity<ResponseStructure<Bookings>>(structure,HttpStatus.CREATED);
		}
		else {
//			customer is not present
			throw new CustomerIdNotFoundException("Sorry Failed to Add Bookings");
		}
	}
}
