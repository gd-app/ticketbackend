package com.hackit.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackit.modal.*;

@Repository
public interface MovieRepository 
  extends CrudRepository<Movie, UUID> {

    List<Movie> findByStatusAndAvailToGreaterThanAndAvailFromLessThan(Integer Status,Date from, Date to);
}
