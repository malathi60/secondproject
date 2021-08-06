package gluecode;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.AmountPage;
import pages.HomePage;
import utilities.WebSiteUtility;


public class Shared 
{
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public AmountPage ap;
	public HomePage hp;
	public WebSiteUtility wu;
	public Scenario s;
	@Before
	public void method1(Scenario s)
	{
		driver=null;
		wait=null;
		this.s=s;
		s.log("\""+s.getName()+"\"execution started");
	}
	@After
	public void method2(Scenario s)
	{
		s.log("\"" +s.getName()+"\" execution stoped and result is " + s.getStatus().name());
	}
	
}
