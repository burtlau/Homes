package com.example.Homes.repo;

import com.example.Homes.entity.Apartment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends MongoRepository<Apartment,String> {
}
