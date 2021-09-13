package com.hackit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	private MovieRepository movieRepo;
    @Autowired
	private MovieTimeRepository movieTimeRepo;
    @Autowired
	private HallRepository hallRepo;
    @Autowired
	private BookingSeatRepository bookingSeatRepo;

    public List<Movie> GetMovieList() {
        Date curDate = new Date(System.currentTimeMillis());
        return movieRepo.findByStatusAndAvailToGreaterThanAndAvailFromLessThan(1, curDate, curDate);
        
    }

    @Transactional
    public void CreateDummy() throws Exception
    {

        //this is for development only
        //to create data for testing
        if (hallRepo.count() >0 || movieRepo.count()>0)
            throw new Exception("There is data in the table, cannot create dummy records");
        
        Hall h1 = new Hall();
        h1.setName("Hall A");
        h1.setLayout("[\"1A\",\"1B\",\"1C\",\"1D\",\"1E\"],[\"2A\",\"2B\",\"2C\",\"2D\",\"2E\"]");
        hallRepo.save(h1);
        Hall h2 = new Hall();
        h2.setName("Hall A");
        h2.setLayout("[\"1A\",\"1B\",\"1C\",\"1D\",\"1E\"],[\"2A\",\"2B\",\"2C\",\"2D\",\"2E\"],[\"3A\",\"3B\",\"3C\",\"3D\",\"3E\"],[\"4A\",\"4B\",\"4C\",\"4D\",\"4E\"]");
        hallRepo.save(h2);
        String[] movies ={"Movie 1","Movie 2","Movie 3"};
        Date dateFrom = new Date(System.currentTimeMillis()-1000000);
        Date dateTo = new Date(System.currentTimeMillis()+999999999);
        
        for(String movie : movies){

            Movie m = new Movie();
            m.setName(movie);
            m.setAvailFrom(dateFrom);
            m.setAvailTo(dateTo);
            m.setStatus(1);
            movieRepo.save(m);

            String[] times = {"1000","1200","1400","1600"};
            for(String time : times){
                MovieTime mt = new MovieTime();
                mt.setMovie(m);
                mt.setTime(time);
                if ("1200".equals(time) || "1600".equals(time))
                    mt.setHall(h2);
                else      
                    mt.setHall(h1);
                movieTimeRepo.save(mt);           
            }
        }
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
