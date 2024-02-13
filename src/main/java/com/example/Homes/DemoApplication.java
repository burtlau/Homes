package com.example.Homes;

import com.example.Homes.entity.Address;
import com.example.Homes.entity.House;
import com.example.Homes.entity.Property;
import com.example.Homes.entity.PropertyType;
import com.example.Homes.repo.PropertyRepository;
import com.example.Homes.service.PropertyService;
import com.example.Homes.service.impl.PropertyServiceImpl;
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
	CommandLineRunner runner(PropertyRepository repository)
	{
		return args -> {
			Address address = new Address(
					"Canada",
					"Toronto",
					"M5V 0R1");

			Property house = new House(
					PropertyType.House,
					80.7,
                    900000.0,
					address,
					2,
					2,
					"",
                    "1",
					true,
					2
			);

			PropertyServiceImpl propertyService = new PropertyServiceImpl(repository);
			Property addedProperty = propertyService.addProperty(house);
			String addedPropertyId = addedProperty.getId();


			Property house2 = new House(
					PropertyType.House,
					80.7,
					1000000.0,
					address,
					2,
					2,
					"",
					"1",
					true,
					2
			);

//			repository.save(house);
//			propertyService.deleteAll();
			propertyService.updateProperty(addedPropertyId, house2);
			// Print out the retrieved properties
//			System.out.println("Properties in the database:");
//			for (Property property: properties) {
//				System.out.println(property);
//			}


		};
	}
}
