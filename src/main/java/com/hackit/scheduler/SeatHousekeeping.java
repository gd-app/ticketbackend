package com.hackit.scheduler;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hackit.service.BookingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SeatHousekeeping {
    @Autowired
    private BookingService bookingService;
    
    private static final Logger logger = LoggerFactory.getLogger(SeatHousekeeping.class);
	

	@Scheduled(fixedRateString ="${hackit.bookingseat.housekeeping.miliseconds}", initialDelay=30000)
	public void housekeep() {
		logger.debug("housekeep booking seat");
        bookingService.HousekeepSeat();

	}
}
