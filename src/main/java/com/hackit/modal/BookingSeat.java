package com.hackit.modal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Index;
import org.springframework.data.rest.core.annotation.RestResource;

import com.hackit.modal.*;
@Entity
public class BookingSeat {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
 
    private String name; 

    @OneToOne
    @JoinColumn(name = "hallId")
    @RestResource(path = "hall", rel="hall")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name="bookingId")
    private Booking booking;
    
    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }
 
    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
 
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the hall
     */
    public Hall getHall() {
        return hall;
    }
 
    /**
     * @param hall the hall to set
     */
    public void setHall(Hall hall) {
        this.hall = hall;
    }
    
    /**
     * @return the booking
     */
    
    public Booking getBooking() {
        return booking;
    }
    
    /**
     * @param booking the booking to set
     */
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
}
