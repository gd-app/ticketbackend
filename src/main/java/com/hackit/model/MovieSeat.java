package com.hackit.model;

public class MovieSeat {

    public MovieSeat(MovieTime _movieTime, String _id, int _row, int _loc){
        this.id = _id;
        this.row = _row;
        this.loc = _loc;
        this.movieTime = _movieTime;
    }
    public MovieTime movieTime;
    public String id;
    public int row;
    public int loc;
    
}
