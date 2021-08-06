package mypack;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test2 {

	public static void main(String[] args) 
	{
			WebDriverManager.chromedriver().setup();
			ChromeOptions co=new ChromeOptions();
			co.addArguments("--headless");               //browser is not visible but requried operations are performed
			ChromeDriver driver=new ChromeDriver(co);
			driver.get("https://www.google.com");
		System.out.println(driver.getCurrentUrl());
		
			

	}

}
