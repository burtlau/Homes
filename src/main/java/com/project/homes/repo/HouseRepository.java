package com.project.homes.repo;

import com.project.homes.entity.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends MongoRepository<House,String> {

}