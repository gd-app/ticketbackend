package com.hackit.model;
import java.util.List;

public class Movie {

    public Movie(String _id, String _name, String _duration){
        this.name = _name;
        this.id = _id;
        this.duration = _duration;
    }
    public String name;
    public String id;
    public String duration;
    public List<MovieTime> movieTime;
    
}
