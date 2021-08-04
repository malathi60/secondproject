package mypack;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test2 {

	public static void main(String[] args) 
	{
			WebDriverManager.firefoxdriver().setup();
			FirefoxDriver driver=new FirefoxDriver();
			driver.get("https://www.google.com");
		System.out.println(driver.getCurrentUrl());
			

	}

}
