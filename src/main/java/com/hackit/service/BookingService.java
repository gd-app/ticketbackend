package com.hackit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.hackit.modal.*;
import com.hackit.repository.*;

@Service
public class BookingService {

    @Autowired
	private BookingRepository bookingRepo;
    @Autowired
	private BookingSeatRepository bookingSeatRepo;
    @Autowired
	private MovieTimeRepository MovieTimeRepo;
    @Autowired
    private MovieService movieService;

    @Value("${hackit.booking.expiry.seconds}")
    private Long BOOKING_EXPIRY;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Booking CreateDraftBooking()
    {
        Booking b = new Booking();
        Date curTime = new Date(System.currentTimeMillis());

        b.setStatus("DRF");
        b.setCreatedOn(curTime);
        b.setModifiedOn(curTime);
        bookingRepo.save(b);
        return b;
    }

    @Transactional
    public boolean ReserveBooking(Booking booking) throws Exception{
        //Never Trust user Input, Check if Seat Id is valid
        ValidateSeat(booking);
        
        //Never Trust user Input, Check if Status is Draft
        if (VerifyDraftBooking(booking.getId())==null){
            throw new Exception("Attempt to reserve booking with non-draft status, booking id :"+booking.getId()); 
        }

        //check seat availability from request
        CheckSeatAvail(booking);

        //reserve
        Date curTime = new Date(System.currentTimeMillis());

        booking.setStatus("PEN");
        booking.setModifiedOn(curTime);
        bookingRepo.save(booking);
        for (BookingSeat bs : booking.getBookingSeat()) {
            bs.setBooking(booking);
            bookingSeatRepo.save(bs);    
        }
        
        return true;

    }

    private void CheckSeatAvail(Booking booking) throws Exception {
        Optional<MovieTime> mt = MovieTimeRepo.findById(booking.getMovieTime().getId());
        List<String> seats = movieService.GetSeatStatus(booking.getMovieTime().getId());
        for (BookingSeat bs : booking.getBookingSeat()) {
            if (Arrays.asList(seats).contains(bs.getName()))
            {
                throw new Exception("Seat Already taken by others : "+bs.getName()+", movieTime:"+mt.get().getId()); 
            }
        }
    }

    private void ValidateSeat(Booking booking) throws Exception {
        Optional<MovieTime> mt = MovieTimeRepo.findById(booking.getMovieTime().getId());
        String[] seats =  mt.get().getHall().getLayout().replace("[","").replace("]","").split(",");
        //validate seat id
        for (BookingSeat bs : booking.getBookingSeat()) {
            if (!Arrays.asList(seats).contains(bs.getName()))
            {
                throw new Exception("Unable to find Seat Id : "+bs.getName()+", movieTime:"+mt.get().getId()); 
            }
        }
    }

    @Transactional
    public boolean PurchaseBooking(Booking booking)throws Exception{
        //Never Trust user Input, Check if Seat Id is valid
        ValidateSeat(booking);
        
        //Never Trust user Input, Check if Status is Draft
        if (VerifyPendingBooking(booking.getId())==null){
            throw new Exception("Attempt to submit booking with non-pending status, booking id :"+booking.getId()); 
        }

        //check seat availability from request
        CheckSeatAvail(booking);

        //submit
        Date curTime = new Date(System.currentTimeMillis());

        booking.setStatus("COM");
        booking.setPurchasedOn(curTime);
        booking.setModifiedOn(curTime);
        bookingRepo.save(booking);
        for (BookingSeat bs : booking.getBookingSeat()) {
            bs.setBooking(booking);
            bookingSeatRepo.save(bs);    
        }
        
        return true;

    }

    public Booking VerifyDraftBooking(UUID uBookingId) {
        Optional<Booking> b = bookingRepo.findById(uBookingId);
        if (!b.isEmpty()){
            if (!"DRF".equals(b.get().getStatus())){
                return null;
            }
        }
        return b.get();
    }
    public Booking VerifyPendingBooking(UUID uBookingId) {
        Optional<Booking> b = bookingRepo.findById(uBookingId);
        if (!b.isEmpty()){
            if (!"PEN".equals(b.get().getStatus())){
                return null;
            }
        }
        return b.get();
    }
    @Transactional
    public void HousekeepSeat() {
        //this shall housekeep all seats that more than x minutes but not in completed status
        Date expiry = new Date(System.currentTimeMillis() - BOOKING_EXPIRY * 1000);
        List<Booking> bookings = bookingRepo.findByModifiedOnLessThanAndStatusNot(expiry,"COM");
        //List<BookingSeat> bs = bookingSeatRepo.findByBookingModifiedOnLessThanAndBookingStatusNot(expiry,"COM");
        for(Booking b : bookings ){
            for (BookingSeat bs : b.getBookingSeat()){
                bookingSeatRepo.delete(bs);
            }
            
            bookingRepo.delete(b);
        }
    }
}
