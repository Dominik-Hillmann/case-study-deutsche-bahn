package com.bahn.casestudy;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bahn.casestudy.download.CsvDownloader;

/**
 * The class that contains the <code>main</code> method.
 * @author Dominik Hillmann
 */
@SpringBootApplication
public class CasestudyApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(CasestudyApplication.class, args);
	}
}
