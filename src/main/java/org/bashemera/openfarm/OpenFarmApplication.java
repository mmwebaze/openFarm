package org.bashemera.openfarm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bashemera.openfarm.model.Animal;
import org.bashemera.openfarm.model.AnimalType;
import org.bashemera.openfarm.model.Role;
import org.bashemera.openfarm.model.User;
import org.bashemera.openfarm.repository.AnimalRepository;
import org.bashemera.openfarm.repository.AnimalTypeRepository;
import org.bashemera.openfarm.repository.RoleRepository;
import org.bashemera.openfarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OpenFarmApplication implements CommandLineRunner {

	/*
	 * @Autowired private CowRepository repository;
	 */
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private AnimalTypeRepository animalTypeRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(OpenFarmApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		/** Test data not to be used in production */
		//repository.deleteAll();
		roleRepository.deleteAll();
		userRepository.deleteAll();
		animalRepository.deleteAll();
		animalTypeRepository.deleteAll();
		
		AnimalType cow = new AnimalType("Cow");
		animalTypeRepository.save(cow);
		
		AnimalType goat = new AnimalType("Goat");
		AnimalType savedCow = animalTypeRepository.save(goat);
		
		AnimalType pig = new AnimalType("Pig");
		AnimalType savedPig = animalTypeRepository.save(pig);
		
		System.out.println(savedPig);
		
		
		Animal kyiguyi = new Animal("Kyiguyi", "M", null, null, new ArrayList<Animal>(),new ArrayList<Animal>(), false, "9890", savedCow);
	
		Animal kyiguyiSaved  = animalRepository.save(kyiguyi);
		
		Animal kyasha = new Animal("Kyasha", "F", null, null, new ArrayList<Animal>(),new ArrayList<Animal>(), true, "1234", savedCow);
		
		Animal kyashaSaved  = animalRepository.save(kyasha);
		
		System.out.println(kyashaSaved);
		List<Animal> kyihembeParents = new ArrayList<>();
		kyihembeParents.add(kyashaSaved);
		kyihembeParents.add(kyiguyiSaved);
		
		Animal kyihembe = new Animal("Kyihembe", "F", null, null, kyihembeParents, new ArrayList<Animal>(), true, "5678", savedCow);
		animalRepository.save(kyihembe);
		
		Animal mbuziM = new Animal(savedPig, "M");
		animalRepository.save(mbuziM);
		
		Animal mbuziF = new Animal(savedPig, "F");
		animalRepository.save(mbuziF);
		
		Role adminRole = roleRepository.findByRole("ADMIN");
        if (adminRole == null) {
            Role newAdminRole = new Role();
            newAdminRole.setRole("ADMIN");
            Role admin = roleRepository.save(newAdminRole);
            
            User mitch = new User();
            mitch.setEmail("mitch.jenkins@gmail.com");
            mitch.setFullname("Mitchel Jenkins");
            mitch.setPassword(bCryptPasswordEncoder.encode("12345678"));
            Set<Role> roles = new HashSet<Role>();
            roles.add(admin);
            mitch.setRoles(roles);
            userRepository.save(mitch);
        }

        Role userRole = roleRepository.findByRole("USER");
        if (userRole == null) {
            Role newUserRole = new Role();
            newUserRole.setRole("USER");
            roleRepository.save(newUserRole);
        }
	}

}
