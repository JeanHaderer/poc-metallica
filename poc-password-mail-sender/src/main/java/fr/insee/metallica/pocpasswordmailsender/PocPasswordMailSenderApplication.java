package fr.insee.metallica.pocpasswordmailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAsync
public class PocPasswordMailSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocPasswordMailSenderApplication.class, args);
	}

}
