package com.bahn.casestudy.download;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CsvDownloader extends WebClient {
	private final String DOWNLOAD_URL = "https://data.deutschebahn.com/dataset/data-betriebsstellen.html";
	private final String SAVE_PATH = "./src/main/resources/static/operation-sites-data.csv";
	
	private final Pattern YEAR_PATTERN = Pattern.compile("Stand(\\d{4})-");
	private final Pattern MONTH_PATTERN = Pattern.compile("-(\\d{2}).csv");
	
	private int youngestYear = 0;
	private int youngestMonth = 0;
	
	
	public CsvDownloader() {
		getOptions().setCssEnabled(false);
		getOptions().setJavaScriptEnabled(false);
	}
	
	
	private String determineNewestCsv(List<String> urls) {
		Map<String, Integer> years = getYearsMap(urls);
		Map<String, Integer> months = getMonthsMap(urls);
		

		years.values().forEach(year -> {
			if (year > youngestYear) {
				youngestYear = year;
			}
		});
		
		Map<String, Integer> filteredByYear = where(years, youngestYear);
		
		filteredByYear.values().forEach(month -> {
			if (month > youngestMonth) {
				youngestMonth = month;
			}
		});
		
		Map<String, Integer> filteredByMonth = where(filteredByYear, youngestMonth);
		
		String youngestUrl = (String) filteredByMonth.keySet().toArray()[0];		
		return youngestUrl;
	}
	
	Map<String, Integer> getYearsMap(List<String> urls) {
		Map<String, Integer> years = new HashMap<String, Integer>();
		Matcher matcher;
		
		for (String url : urls) {
			matcher = YEAR_PATTERN.matcher(url);
			if (matcher.find()) {
				int year = Integer.parseInt(matcher.group(1));
				years.put(url, year);
			}		
		}
		
		return years;		
	}
	
	Map<String, Integer> getMonthsMap(List<String> urls) {
		Map<String, Integer> months = new HashMap<String, Integer>();
		Matcher matcher;
		
		for (String url : urls) {			
			matcher = MONTH_PATTERN.matcher(url);
			if (matcher.find()) {
				int month = Integer.parseInt(matcher.group(1));
				months.put(url, month);
			}			
		}
		
		return months;
	}
	
	
	private Map<String, Integer> where(Map<String, Integer> map, int dateNum) {
		Map<String, Integer> filtered = new HashMap<String, Integer>();
		map.forEach((url, num) -> {
			if (num == dateNum)
				filtered.put(url, num);
		});
		
		return filtered;
	}
	
	public void saveNewestCsv() throws IOException {
		HtmlPage page = getPage(DOWNLOAD_URL);
		@SuppressWarnings("unchecked")
		List<HtmlElement> csvItems = (List<HtmlElement>) page.getByXPath("//ul[@class='dropdown-menu']");
		List<String> csvUrls = new ArrayList<String>();
		csvItems.forEach(item -> {
			csvUrls.add(item
				.getElementsByTagName("a")
				.get(1)
				.getAttribute("href"));
		});
		
		String newestUrl = determineNewestCsv(csvUrls);
		downloadCsv(newestUrl);
	}
	
	private void downloadCsv(String url) throws MalformedURLException, IOException {
		Files.copy(
			new URL(url).openStream(),
			Paths.get(SAVE_PATH),
			StandardCopyOption.REPLACE_EXISTING
		);
	}
} 
