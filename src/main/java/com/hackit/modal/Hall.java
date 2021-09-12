package com.hackit.modal;
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

@Entity
public class Hall {
 
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
 
    private String name; 

    private String layout;

    

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
     * @return the layout
     */
    public String getLayout() {
        return layout;
    }
 
    /**
     * @param layout the layout to set
     */
    public void setLayout(String layout) {
        this.layout = layout;
    }
}
