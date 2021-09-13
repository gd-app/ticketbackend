package com.hackit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackit.modal.*;
import com.hackit.service.*;
import com.hackit.util.*;

@RestController
@RequestMapping("/api/booking/v1")
public class BookingController {

    @Autowired
	private MovieService movieService;
    @Autowired
	private BookingService bookingService;
    @Autowired
    private SecurityHelper securityHelper;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${hackit.jwt.disabled}")
    private Boolean JWT_DISABLED;

    @PostMapping("/reserve")
	@ResponseBody
	public ResponseEntity<String> reserve(@RequestBody Booking b, @RequestHeader("request-hash") String jwt) {
        logger.debug("reserve called");
        logger.debug("JWT : "+jwt);
        //validate request
        if (!JWT_DISABLED && !securityHelper.validateToken(jwt)){
            logger.info("securityHelper.validateToken failed ");

            return ResponseEntity.badRequest().build();
        }
        
        if (!b.getIdInStr().equals(securityHelper.getBookingIdFromToken(jwt)) ){
            logger.info("booking id & jwt not match, request rejected");
            return ResponseEntity.badRequest().build();
        }
        try {
            Boolean result = bookingService.ReserveBooking(b);
            if (result){
                String jwt_renewed = securityHelper.generateToken(b);
                logger.debug("JWT renewed：jwt : "+jwt);
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("request-hash", jwt_renewed);
                return ResponseEntity.ok().headers(responseHeaders).build();
		}
            else return ResponseEntity.badRequest().build();
        }
        catch(Exception e){
            logger.error("error when reserve booking", e);
            return ResponseEntity.badRequest().build();
        }
        
    }

    @PostMapping("/purchase")
	@ResponseBody
	public ResponseEntity<String> purchase(@RequestBody Booking b, @RequestHeader("request-hash") String jwt) {
        logger.debug("purchase called");
        logger.debug("JWT : "+jwt);
        //validate request
        if (!JWT_DISABLED &&  !securityHelper.validateToken(jwt)){
            logger.info("securityHelper.validateToken failed ");

            return ResponseEntity.badRequest().build();
        }

        //validate input
        

        //update booking with input
        try{
            Boolean result = bookingService.PurchaseBooking(b);

            if (result){
                //send email??

                //return success
                return ResponseEntity.ok().build();
            }
            else return ResponseEntity.badRequest().build();
        }
        catch(Exception e ){
            logger.error("Error on purchase booking",e);
            return ResponseEntity.badRequest().build();

        }

    }

}
