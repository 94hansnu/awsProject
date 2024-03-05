package awsProject.awsProject;


import awsProject.awsProject.database.entity.Role;
import awsProject.awsProject.database.entity.User;
import awsProject.awsProject.database.repository.RoleRepository;
import awsProject.awsProject.database.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AwsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsProjectApplication.class, args);
	}



	@Bean // :)
	CommandLineRunner runner(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {

		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;

			Role admin = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roleSet = new HashSet<>();
			roleSet.add(admin);

			User adminUser = new User("admin", encoder.encode("admin"), roleSet);
			userRepository.save(adminUser);
		};

	}

	}



