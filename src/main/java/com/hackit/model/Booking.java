package com.hackit.model;
import java.util.List;

public class Booking {

    public Booking(String _id){
        this.id = _id;

    }
    public String id;
    public List<MovieSeat> seats;
    public String firstName;
    public String lastName;
    public String email;
}
