package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import entita.Book;
import repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.boot.CommandLineRunner;
@SpringBootApplication

@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "entita")
@ComponentScan(basePackages = "controller")




public class TestSpringBootApplication implements CommandLineRunner{
	@Autowired 
	private BookRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	//	Book book = new Book("titolo", "autore", "isbn");
	//	repository.save(book);
	}

}
