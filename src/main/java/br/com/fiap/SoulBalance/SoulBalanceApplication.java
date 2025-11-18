package br.com.fiap.SoulBalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SoulBalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoulBalanceApplication.class, args);
	}

}
