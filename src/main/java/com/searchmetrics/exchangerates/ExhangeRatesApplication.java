package com.searchmetrics.exchangerates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The Class ExhangeRatesApplication.
 */
@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class ExhangeRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExhangeRatesApplication.class, args);
	}

}
