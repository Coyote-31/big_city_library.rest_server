package com.coyote.big_city_library.rest_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RestController
public class RestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

		log.debug("Server test on /Hello with param : \"{}\"", name);

	return String.format("Hello %s!", name);
	}
}
