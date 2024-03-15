package com.project.homes.repo;

import com.project.homes.entity.Apartment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends MongoRepository<Apartment,String> {
}
