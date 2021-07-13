package com.bahn.casestudy;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bahn.casestudy.download.CsvDownloader;

@SpringBootApplication
public class CasestudyApplication {
	public static void main(String[] args) throws IOException { //!!!!!!!!!!!!!!!!!!!!!!!!
		SpringApplication.run(CasestudyApplication.class, args);
//		CsvDownloader c = new CsvDownloader();
//		c.saveNewestCsv();
//		c.close();
	}
}
