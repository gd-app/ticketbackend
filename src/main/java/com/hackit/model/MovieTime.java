package com.hackit.model;

import java.sql.Time;
import java.util.List;

public class MovieTime {

    public MovieTime(Movie _movie, String _id, Time _from){
        this.id = _id;
        this.from = _from;
        this.movie = _movie;
    }
    public Movie movie;
    public String id;
    public Time from;
    public List<MovieSeat> seats;
    
}
