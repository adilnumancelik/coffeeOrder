package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeProjectApplication implements CommandLineRunner{
	@Autowired
    private ExampleClient exampleClient;

	
	public static void main(String[] args) {
		 SpringApplication sa = new SpringApplication(CoffeeProjectApplication.class);
	     sa.setBannerMode(Banner.Mode.OFF);
	     sa.run(args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		exampleClient.run();
	}

}
