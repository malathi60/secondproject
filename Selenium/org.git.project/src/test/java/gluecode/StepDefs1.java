package gluecode;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.AmountPage;
import pages.HomePage;
import utilities.WebSiteUtility;

public class StepDefs1
{
	public Shared sh;
	public StepDefs1(Shared sh)
	{
		this.sh=sh;
	}
	@Given("open browser using {string}")
	public void method1(String browser) throws Exception
	{
		sh.wu=new WebSiteUtility();
		sh.driver=sh.wu.openBrowser(browser);
		sh.wait=sh.wu.defineWait(sh.driver);
	}
	@Then("launch site {string}")
	public void method2(String url) throws Exception
	{
		sh.wu.launchSite(sh.driver, url);
		
	}
	@Then("move to required svg element graph")
	public void method3()
	{
		sh.hp=new HomePage(sh.driver,sh.wait);
		sh.hp.moveToGraph();
	}
	@Then("validate {string} amount from table and graph")
	public void method4(String type)
	{
		sh.ap=new AmountPage(sh.driver,sh.wait);
		if(sh.ap.validateAmount(sh.hp.getTextOfBarpoints(sh.hp.collectAllpointsOnBars
				(sh.driver, sh.wait), type), sh.ap.getValueFromTable(sh.driver, sh.wait, type))==0)
		{
			sh.s.log("Amount Validation suucessful & test passed");
		}
		else
		{
			sh.s.log("Test failed");
		}
		
	}
	@And("close site")
	public void method5()
	{
		sh.wu.closeSite(sh.driver);
	}
}
