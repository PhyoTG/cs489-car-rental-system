package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Role;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FairfieldCarRentalApplication  implements CommandLineRunner {
	private final RoleRepository roleRepository;

	public FairfieldCarRentalApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FairfieldCarRentalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var role = new Role(1,"ROLE_ADMIN");
		roleRepository.save(role);
	}
}
