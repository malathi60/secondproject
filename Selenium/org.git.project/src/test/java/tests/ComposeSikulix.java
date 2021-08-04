package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import utilities.WebSiteUtility;

public class ComposeSikulix
{public RemoteWebDriver driver;
public FluentWait<RemoteWebDriver> wait;
public WebSiteUtility wu;
public LoginPage lp;
@BeforeClass
public void openbrowser() throws Exception
{
	wu=new WebSiteUtility();
	driver=wu.openBrowser("chrome");
	wait=wu.defineWait(driver);	
}
@Test
public void composeMail() throws Exception
{
	wu.launchSite(driver);
	lp=new LoginPage(driver,wait);
	lp.filluid("malathivan");
	lp.clickNext();
	lp.fillpwd("Mamatha@60");
	lp.clickNext();
	lp.clickCompose();
	lp.fillto("msdet21@yahoo.com");
	lp.fillsub("hi");
	lp.attachfileSikulix();
	Thread.sleep(5000);
	//lp.clicksend();

}
}
