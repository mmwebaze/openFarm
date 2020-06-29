package org.bashemera.openfarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bashemera.openfarm.model.Animal;
import org.bashemera.openfarm.model.Cow;
import org.bashemera.openfarm.repository.CowRepository;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenFarmApplication implements CommandLineRunner {

	@Autowired
	@Lazy
	 private CowRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(OpenFarmApplication.class, args);
	}
	/*@Bean	
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");
			
			String[] beanNames = ctx.getBeanDefinitionNames();
			
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}*/
	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		
		Cow kyasha = new Cow("Kyasha", "F", null, null, new ArrayList<Cow>(),new ArrayList<Cow>(), "1234");
		//Cow(String name, String gender, Date dateOfBirth, Date dateOfDeath, List<Cow> parents, List<Cow> children, String tagId)
		Cow kyashaSaved  = repository.save(kyasha);
		
		System.out.println(kyashaSaved);
		List<Cow> kyihembeParents = new ArrayList<>();
		kyihembeParents.add(kyashaSaved);
		
		Cow kyihembe = new Cow("Kyihembe", "F", null, null, kyihembeParents, new ArrayList<Cow>(), "5678");
		
		repository.save(kyihembe);
	}

}
