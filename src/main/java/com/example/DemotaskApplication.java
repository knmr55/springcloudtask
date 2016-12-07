package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@EnableTask
@EnableBatchProcessing
@SpringBootApplication
public class DemotaskApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemotaskApplication.class, args);
	}
	
	@Bean
   public CommandLineRunner commandLineRunner() {
        return strings ->
                System.out.println("Simple task application :Simple task application :Simple task application :Simple task application :" + 
                      new SimpleDateFormat().format(new Date()));
    }
}
