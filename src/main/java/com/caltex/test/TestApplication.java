package com.caltex.test;

import com.caltex.test.process.Reader;
import com.caltex.test.process.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;

@SpringBootApplication
@ComponentScan(basePackages = "com.caltex.test")
public class TestApplication implements CommandLineRunner {

	@Autowired
	private Reader reader;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reader.readExcelFile();
	}
}
