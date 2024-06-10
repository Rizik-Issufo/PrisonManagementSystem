package com.roi.PrisonManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@SpringBootApplication
//@RestController
public class PrisonManagementSystemApplication {

	public static void main(String[] args) {
		System.out.println("Hello");
		SpringApplication.run(PrisonManagementSystemApplication.class, args);
		System.out.println("Hello");
		System.out.println("localDate"+ LocalDate.now());
	}
//	@GetMapping("/")
//	public String index(){
//		return "Hello";
//	}

}
