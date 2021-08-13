package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import pages.HomePage;

public class Sample 
{
	@Test
	public void test()
	{
		 RemoteWebDriver driver=null;
		 FluentWait<RemoteWebDriver> wait=null;
		HomePage hp=new HomePage(driver,wait);
		hp.uidFill("id");
		System.out.println("id entered");
	}
		

	
}
