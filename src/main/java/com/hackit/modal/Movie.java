package com.hackit.modal;

 
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
import com.hackit.modal.*;
@Entity
public class Movie {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
 
    private String name; 
    private Integer status; 
    private Date availFrom; 
    private Date availTo; 

    @OneToMany(mappedBy = "movie")
    @RestResource(path = "movieTime", rel="movieTime")
    @JsonIgnoreProperties("movie")
    private List<MovieTime> movieTime;

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
     * @return the status
     * 1:Active
     * 0:Inactive
     */
    public Integer getStatus() {
        return status;
    }
 
    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

        /**
     * @return the movie available start date
     */
    public Date getAvailFrom() {
        return availFrom;
    }
 
    /**
     * @param availFrom the movie available start date
     */
    public void setAvailFrom(Date availFrom) {
        this.availFrom = availFrom;
    }

    /**
     * @return the movie available end date
     */
    public Date getAvailTo() {
        return availTo;
    }
 
    /**
     * @param availTo the movie available end date
     */
    public void setAvailTo(Date availTo) {
        this.availTo = availTo;
    }
    /**
     * @return the movieTime
     */
    public List<MovieTime> getMovieTime() {
        return movieTime;
    }
 
    /**
     * @param movieTime the movieTime to set
     */
    public void setMovieTime(List<MovieTime> movieTime) {
        this.movieTime = movieTime;
    }


}
