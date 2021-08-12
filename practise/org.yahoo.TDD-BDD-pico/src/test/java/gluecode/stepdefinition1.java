package gluecode;
import java.util.List;

import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ComposePage;
import pages.LogoutPage;
import pages.PasswordPage;
import pages.SigninPage;
import utilities.WebsitUtility;

public class stepdefinition1 
{
	public RemoteWebDriver driver;
    public FluentWait<RemoteWebDriver> wait;
    public SigninPage sp;
    public  WebsitUtility wu;
    public PasswordPage pp;
    public SoftAssert sf;
	public ComposePage cp;
	public LogoutPage lop;
    @Given("Launch site using {string}")
	public void Launch_site_using(String bn) throws Exception
	{
	    wu=new WebsitUtility();
	    driver=wu.OpenBrowser(bn);
	    wait=wu.defineWait(driver);
	    sf=new SoftAssert();
	    wu.launchSite(driver, "url");
	}
    
	@When("enter uid as {string}")
	public void enter_uid_as(String uid)
	{
		sp=new SigninPage(driver,wait);
		sp.clickSigin();
		sp.filluid(uid);
	}

	@When("click uid next button")
	public void click_uid_next_button() 
	{
		sp.clickNext();
	}

	@When("validate outcome related to uid criteria as {string}")
	public void validate_outcome_related_to_uid_criteria_as(String uc) throws Exception 
	{
		try {
		if(uc.contains("blank")  || uc.contains("invalid"))
		{
			if(sp.isBlankInvaliduiderr())
			{
				System.out.println("blank uid test passed");
				sf.assertTrue(true);
			}
			else
			{
				System.out.println("blankk/invalid uid test passed");
				//Assert.assertTrue(false);
				sf.fail();
			}
		}
		else 
		{
			System.out.println("uid test passed");
			//Assert.assertTrue(true);
			sf.assertTrue(true);
		}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage()+"and see:");
			String fp=wu.captureScreenShot(driver);
			Reporter.log("<a href=\""+fp+"\" ><img src=\""+fp+"\" width=\"100\" height=\"100\" /></a>");
			//Assert.assertTrue(false);
			sf.fail();
		}
	}

	@When("enter password as {string}")
	public void enter_password_as(String pwd) throws Exception
	{
		pp=new PasswordPage(driver,wait);
		try {
			if(sp.isPasswordVisible())
			{
				pp.fillpwd(pwd);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage()+" and see:");
			String fp=wu.captureScreenShot(driver);
			System.out.println("<a href=\""+fp+"\" ><img src=\""+fp+"\" width=\"100\" height=\"100\" /></a>");
			//Assert.assertTrue(false);
			sf.fail();
		}
		
	}

	@When("click password next button")
	public void click_password_next_button() 
	{
		if(sp.isPasswordVisible()) {
	    pp.clickNext();}
		else
		{
			System.out.println("test not required");
		}
	}

	@When("validate outcome related to pwd criteria as {string}")
	public void validate_outcome_related_to_pwd_criteria_as(String pc) throws Exception
	{
		try {
		if(pc.length()==0)
		{
			if(pp.isBlankPassword())
			{
				System.out.println("blank password test passed");
				//Assert.assertTrue(true);
				sf.assertTrue(true);
			}
			else
			{
				System.out.println("blank password testfailed");
				//Assert.assertTrue(false);
				sf.fail();
			}
			wu.closeSite(driver);
		}
		else if(pc.contains("invalid") || pc.contains("N/A"))
		{
			if(pp.isPwdInvalid())
			{
				System.out.println("invalid password test passed");
				//Assert.assertTrue(true);
				sf.assertTrue(true);
			}
			else
			{
				System.out.println("invalid password testfailed");
				//Assert.assertTrue(false);
				sf.fail();
			}
		}
		else
		{
			pp.clickMailicon();
			if(pp.isComposeVisible())
			{
				System.out.println("valid password test passed");
			}
		}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage()+" and see:");
			String fp=wu.captureScreenShot(driver);
			System.out.println("<a href=\""+fp+"\" ><img src=\""+fp+"\" width=\"100\" height=\"100\" /></a>");
			//Assert.assertTrue(false);
			sf.fail();
		}   
	}
	@Then("title of Page is {string}")
	public void title_of_Page_is(String title) 
	{
	   String x=driver.getTitle();
	   try {
	   if(x.contains(title))
	   {
		   System.out.println("title validated");
		   //Assert.assertTrue(true);
		   sf.assertTrue(true);
	   }
	   else
	   {
		   System.out.println("title test failed");
		   //Assert.assertTrue(false);
		   sf.fail();
	   }
	   sf.assertAll();
	   }
	   catch(Exception ex) {}
	}

	@When("close the site")
	public void close_the_site() 
	{
	   wu.closeSite(driver);
	}
	@And("do logout")
	public void do_logout()
	{
		lop=new LogoutPage(driver,wait);
		wu=new WebsitUtility();
		lop.clickProfilePic();
		lop.clickLogout();
	}
	@Then("validate login")
	public void validate_login()
	{
		pp=new PasswordPage(driver,wait);
		pp.clickMailicon();
		org.testng.Assert.assertEquals(pp.isComposeVisible(), true);
	}
	@Then("compose mail and test")
	public void compose_mail_and_test(DataTable dt)
	{
		List<Map<String,String>> m=dt.asMaps();
		for(int i=0;i<m.size();i++)
		{
			cp=new ComposePage(driver,wait);
			cp.clickCompose();
			cp.fillToaddress(m.get(i).get("to"));
			cp.fillSubject(m.get(i).get("subject"));
			cp.fillMessageBody(m.get(i).get("messagebody"));
			cp.sendMail();
			if(cp.isMailSend())
			{
				System.out.println("mail has been send");
			}
			else
			{
				System.out.println("mail has not been send");
			}
		}	    
	}
	
}
