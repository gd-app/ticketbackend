package com.hackit.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;

import com.hackit.modal.*;
import com.hackit.repository.*;

@Service
public class MovieService {

    @Autowired
	private MovieRepository movieRepository;
    @Autowired
	private MovieTimeRepository movieTimeRepo;
    @Autowired
	private HallRepository hallRepo;
    @Autowired
	private BookingSeatRepository bookingSeatRepo;

    public List<Movie> GetMovieList() {
        Date curDate = new Date(System.currentTimeMillis());
        return movieRepository.findByStatusAndAvailToGreaterThanAndAvailFromLessThan(1, curDate, curDate);
        
    }

    public void CreateDummy()
    {
        Movie m = new Movie();
        m.setName("Movie 2");
        m.setAvailFrom(new Date(1621359943*1000));
        m.setAvailTo(new Date(1721359943*1000));
        m.setStatus(1);
        movieRepository.save(m);
        Hall h = new Hall();
        h.setName("Hall A");
        h.setLayout("['1A','1B','1C','1D','1E'],['2A','2B','2C','2D','2E']");
        hallRepo.save(h);
        MovieTime mt = new MovieTime();
        mt.setMovie(m);
        mt.setTime("1000");
        mt.setHall(h);
        movieTimeRepo.save(mt);
        mt = new MovieTime();
        mt.setMovie(m);
        mt.setTime("1200");
        mt.setHall(h);
        movieTimeRepo.save(mt);
        mt = new MovieTime();
        mt.setMovie(m);
        mt.setTime("1400");
        mt.setHall(h);
        movieTimeRepo.save(mt);
        
    }

    public List<String> GetSeatStatus(UUID movieTimeId) {
        List<BookingSeat> bs = bookingSeatRepo.findByBookingMovieTimeId(movieTimeId);
        List<String> result = new ArrayList<String>();
        for (BookingSeat b : bs) {
            result.add(b.getName());
        }
        return result;
    }
    
}
