package org.bashemera.openfarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bashemera.openfarm.model.Animal;
import org.bashemera.openfarm.model.Cow;
import org.bashemera.openfarm.model.Role;
import org.bashemera.openfarm.model.User;
import org.bashemera.openfarm.repository.CowRepository;
import org.bashemera.openfarm.repository.RoleRepository;
import org.bashemera.openfarm.repository.UserRepository;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OpenFarmApplication implements CommandLineRunner {

	@Autowired
	private CowRepository repository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
		/** Test data not to be used in production */
		repository.deleteAll();
		roleRepository.deleteAll();
		userRepository.deleteAll();
		
		Cow kyiguyi = new Cow("Kyiguyi", "M", null, null, new ArrayList<Cow>(),new ArrayList<Cow>(), "9890");
		Cow kyiguyiSaved  = repository.save(kyiguyi);
		
		Cow kyasha = new Cow("Kyasha", "F", null, null, new ArrayList<Cow>(),new ArrayList<Cow>(), "1234");
		//Cow(String name, String gender, Date dateOfBirth, Date dateOfDeath, List<Cow> parents, List<Cow> children, String tagId)
		Cow kyashaSaved  = repository.save(kyasha);
		
		System.out.println(kyashaSaved);
		List<Cow> kyihembeParents = new ArrayList<>();
		kyihembeParents.add(kyashaSaved);
		kyihembeParents.add(kyiguyiSaved);
		
		Cow kyihembe = new Cow("Kyihembe", "F", null, null, kyihembeParents, new ArrayList<Cow>(), "5678");
		
		repository.save(kyihembe);
		
		Role adminRole = roleRepository.findByRole("ADMIN");
        if (adminRole == null) {
            Role newAdminRole = new Role();
            newAdminRole.setRole("ADMIN");
            Role admin = roleRepository.save(newAdminRole);
            
            User mitch = new User();
            mitch.setEmail("mitch@gmail.com");
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
