package ru.kirill98.sovetromantica;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j
public class SovetromanticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SovetromanticaApplication.class, args);
		log.info("Start app");
	}

}
