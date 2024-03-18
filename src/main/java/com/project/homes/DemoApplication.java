package com.project.homes;

import com.project.homes.entity.House;
import com.project.homes.repo.ApartmentRepository;
import com.project.homes.repo.HouseRepository;
import com.project.homes.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication{
	@Autowired
	private PropertyService propertyService;

	private final House HOUSE1 = TestConstants.HOUSE_1;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(HouseRepository houseRepo, ApartmentRepository apartmentRepo)
	{
		return args -> {

//			propertyService.deleteHouseProperties();
//			Property addedProperty = propertyService.addProperty(HOUSE1);
//			String addedPropertyId = addedProperty.getId();
//			propertyService.addProperty(TestConstants.HOUSE_1);
//			propertyService.deleteProperty("65cde580a5e7703ce007fc9b", PropertyType.APARTMENT);
		};
	}
}
