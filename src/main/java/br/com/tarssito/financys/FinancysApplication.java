package br.com.tarssito.financys;

import br.com.tarssito.financys.domain.User;
import br.com.tarssito.financys.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class FinancysApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinancysApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Tarssito Araujo", "tarssito@gmail.com", "tarssito.araujo", "123456");
		User user2 = new User(null, "Théo Kaan", "theo@gmail.com","theo.kaan", "12345");

		userRepository.saveAll(Arrays.asList(user1, user2));
	}
}
