package com.hackit.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackit.modal.*;

@Repository
public interface HallRepository 
  extends CrudRepository<Hall, UUID> {
}
