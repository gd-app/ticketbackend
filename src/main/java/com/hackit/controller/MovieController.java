package com.hackit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.List;

import com.hackit.modal.*;
import com.hackit.service.*;
import com.hackit.util.*;

@RestController
@RequestMapping("/api/movie/v1/")
public class MovieController {

    @Autowired
	private MovieService movieService;
    @Autowired
	private BookingService bookingService;
    @Autowired
    private SecurityHelper securityHelper;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<Movie>> list() {
        logger.debug("List called");
        //get a list of movie that availble for booking
        //date & time is not considered in this implementation
        //movieService.CreateDummy();
        List<Movie> movies = movieService.GetMovieList();
        if (movies.size()>0)
            return ResponseEntity.ok(movies);
        else return ResponseEntity.noContent().build();

    }

    @GetMapping("/dummy")
    public ResponseEntity<String> dummy() {
        logger.debug("dummy called");
        try{
            movieService.CreateDummy();
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    

    @PostMapping("/seatstatus")
	public ResponseEntity<List<String>> SeatStatus(@RequestBody String movieTimeId,@RequestHeader(value = "request-hash", required = false) String requestJWT) {
        logger.debug("SeatStatus called, movieTimeId："+movieTimeId);
        logger.debug("requestJWT ："+requestJWT);
        Booking b = new Booking();
        try{
            if(requestJWT != null && securityHelper.validateToken(requestJWT)){
                String bookingId = securityHelper.getBookingIdFromToken(requestJWT);
                logger.debug("bookid id from jwt ："+bookingId);
                UUID uBookingId = UUID.fromString(bookingId);

                b = bookingService.VerifyDraftBooking(uBookingId);
            }

            UUID uMovieTimeId = UUID.fromString(movieTimeId);
            List<String> seats = movieService.GetSeatStatus(uMovieTimeId);
            if (seats != null){
                if (b == null || b.getId() == null){
                    logger.debug("create draft booking");
                    b = bookingService.CreateDraftBooking();
                }
                logger.debug(" Booking ：Id : "+b.getIdInStr());
                String jwt = securityHelper.generateToken(b);
                logger.debug("JWT generated  ：jwt : "+jwt);
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("request-hash", jwt);
                return ResponseEntity.ok().headers(responseHeaders).body(seats);
            } 
            else 
                return ResponseEntity.badRequest().build();
        } catch (Exception e){
            logger.error("Error at SeatStatus", e);
            return ResponseEntity.badRequest().build();
        }
       
    }

}
