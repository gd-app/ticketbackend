package com.hackit.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackit.modal.*;

@Repository
public interface BookingSeatRepository 
  extends CrudRepository<BookingSeat, UUID> {

    List<BookingSeat> findByBookingMovieTimeId(UUID movieTimeId);
    List<BookingSeat> findByBookingModifiedOnLessThanAndBookingStatusNot(Date lastModified, String completedStatus);
}
