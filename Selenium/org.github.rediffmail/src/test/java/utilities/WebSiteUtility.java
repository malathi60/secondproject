package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebSiteUtility 
{
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public RemoteWebDriver openBrowser(String bn)
	{
		if(bn.contains("chorme"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(bn.equalsIgnoreCase("firfox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(bn.equalsIgnoreCase("opera"))
		{
			WebDriverManager.operadriver().setup();
			driver=new OperaDriver();
		}
		else if(bn.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		return driver;
	}
	public FluentWait<RemoteWebDriver> defineWaitObject(RemoteWebDriver driver) throws Exception
	{
		wait=new FluentWait<RemoteWebDriver>(driver);
		String temp1=PropertyFileUtility.getValueInProperty("maxwait");
		int max=Integer.parseInt(temp1);
		String interval=PropertyFileUtility.getValueInProperty("interval");
		int intrvl=Integer.parseInt(interval);
		wait.withTimeout(Duration.ofSeconds(max));
		wait.pollingEvery(Duration.ofMillis(intrvl));
		return(wait);
	}
	public void closeSite(RemoteWebDriver driver)
	{
		driver.quit();
	}
	public By getByfromWebElement(WebElement e)
	{
		String[] x=e.toString().split("->");
		String[] y=x[1].split(": ");
		String locatortype=y[0].trim();
		String locatorvalue=y[1].substring(0, y[1].length()-1).trim();
		By b=null;
		if(locatortype.contains("name"))
		{
			b=By.name(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			b=By.id(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("tagname"))
		{
			b=By.tagName(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("linktext"))
		{
			b=By.linkText(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("partialtext"))
		{
			b=By.partialLinkText(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			b=By.xpath(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("class"))
		{
			b=By.className(locatorvalue);
		}
		else
		{
			b=By.cssSelector(locatorvalue);
		}
		return(b);	
	}
}
