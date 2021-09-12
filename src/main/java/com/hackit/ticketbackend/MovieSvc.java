package com.hackit.ticketbackend;

import java.util.List;
import java.sql.Time;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 import com.hackit.model.*;
@RestController
public class MovieSvc {
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies")
    public List<Movie> GetMovies(@RequestParam(required = false) String mvid) {
        List<Movie> mvs = new ArrayList<Movie>();
        if (mvid != null) {
            Movie mv; 
            if (mvid.equals("1")){
                mv = new Movie("1","Mission Impossible","c");
                mv.movieTime = GetMovieTime(mv);
            }
            else if (mvid.equals("2") )
                mv = new Movie("2","Star Trek","c");
            else if (mvid.equals("3") )
                mv = new Movie("3","Jurrasic Park","c");
            else
                mv = null;
            mvs.add(mv);    
        ;
        }
        else {
            Movie mv = new Movie("1","Mission Impossible","c");
            mv.movieTime = GetMovieTime(mv);
            mvs.add(mv);
            mvs.add(new Movie("2","Star Trek","c"));
            mvs.add(new Movie("3","Jurassic Park","c"));
        }  
        return mvs;
    }
    private List<MovieTime>  GetMovieTime(Movie mv){
        List<MovieTime> result = new ArrayList<MovieTime>();
        result.add(new MovieTime(null, "1", new Time(2,0,0)));
        result.add(new MovieTime(null, "2", new Time(4,0,0)));
        result.add(new MovieTime(null, "3", new Time(6,0,0)));
        result.add(new MovieTime(null, "4", new Time(8,0,0)));

        return result;
    }
}
