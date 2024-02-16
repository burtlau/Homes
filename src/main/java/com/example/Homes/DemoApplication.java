package com.example.Homes;

import com.example.Homes.entity.*;
import com.example.Homes.repo.ApartmentRepository;
import com.example.Homes.repo.HouseRepository;
import com.example.Homes.service.impl.PropertyServiceImpl;
import com.example.Homes.TestConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(HouseRepository houseRepo, ApartmentRepository apartmentRepo)
	{
		return args -> {

			PropertyServiceImpl propertyService = new PropertyServiceImpl(houseRepo, apartmentRepo);
//			Property addedProperty = propertyService.addProperty(apartment);
//			String addedPropertyId = addedProperty.getId();
//			propertyService.addProperty(TestConstants.HOUSE_1);
//			propertyService.deleteProperty("65cde580a5e7703ce007fc9b", PropertyType.APARTMENT);


		};
	}
}
