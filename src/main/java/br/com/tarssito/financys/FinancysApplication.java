package br.com.tarssito.financys;

import br.com.tarssito.financys.domain.User;
import br.com.tarssito.financys.domain.enums.Profile;
import br.com.tarssito.financys.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class FinancysApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FinancysApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Tarssito Araujo", "tarssito@gmail.com",
				"tarssito.araujo", passwordEncoder.encode("123456"), Profile.ADMIN);
		User user2 = new User(null, "Théo Kaan", "theo@gmail.com",
				"theo.kaan", passwordEncoder.encode("12345"), Profile.USER);

		userRepository.saveAll(Arrays.asList(user1, user2));
	}
}
