package com.hackit.modal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Index;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.datetime.standard.DateTimeContext;

@Entity
public class Booking {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
 
    private String name; 

    private String email; 

    private String status;

    private Date createdOn;

    private Date modifiedOn;
    
    private Date purchasedOn;

    @OneToOne
    @JoinColumn(name = "movieTimeId")
    @RestResource(path = "movieTime", rel="movieTime")
    private MovieTime movieTime;
    
    @OneToMany(mappedBy = "booking")
    @RestResource(path = "bookingSeat", rel="bookingSeat")
    @JsonIgnoreProperties("bookingSeat")
    private List<BookingSeat> bookingSeat;


    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }
 
    /**
     * @return the id in String format
     */
    public String getIdInStr() {
        return id.toString();
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }
 
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
   /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
 
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
   /**
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }
 
    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
  /**
     * @return the modifiedOn
     */
    public Date getModifiedOn() {
        return modifiedOn;
    }
 
    /**
     * @param modifiedOn the modifiedOn to set
     */
    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
  /**
     * @return the purchasedOn
     */
    public Date getPurchasedOn() {
        return purchasedOn;
    }
 
    /**
     * @param purchasedOn the purchasedOn to set
     */
    public void setPurchasedOn(Date purchasedOn) {
        this.purchasedOn= purchasedOn;
    }
    /**
     * @return the movieTime
     */
    public MovieTime getMovieTime() {
        return movieTime;
    }
 
    /**
     * @param movieTime the movieTime to set
     */
    public void setMovieTime(MovieTime movieTime) {
        this.movieTime = movieTime;
    }
    /**
     * @return the bookingSeat
     */
    public List<BookingSeat> getBookingSeat() {
        return bookingSeat;
    }
 
    /**
     * @param bookingSeat the bookingSeat to set
     */
    public void setBookingSeat(List<BookingSeat> bookingSeat) {
        this.bookingSeat = bookingSeat;
    }

}
