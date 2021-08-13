package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import utilities.WebsitUtility;

public class MailPage 
{
//(//ul[@aria-label='Message list']//a[@role='article']) | (//ul[@aria-label='Message list']//div[contains(@class,'message-list-placeholder')])
	public static RemoteWebDriver driver;
	public static FluentWait<RemoteWebDriver> wait;
	@FindBy(how=How.XPATH,using="(//ul[@aria-label='Message list']//a[@role='article']) | (//ul[@aria-label='Message list']//div[contains(@class,'message-list-placeholder')])")
	private WebElement mailscount;
	public MailPage(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
		this.wait=wait;
	}
	public int getMailsCount()
	{
		WebsitUtility wu=new WebsitUtility();
		By b=wu.getByFromWebElement(mailscount);
		System.out.println(b);
		int size=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(b)).size();
		return(size);
		
	}
	public static void main(String[] args) throws Exception
	{
		WebsitUtility wu=new WebsitUtility();
		driver=wu.OpenBrowser("chrome");
		wu.launchSite(driver, "url");
		wait=wu.defineWait(driver);
		SigninPage sp=new SigninPage(driver,wait);
		sp.clickSigin();
		sp.filluid("msdet21");
		sp.clickNext();
		sp.clickcaptchaIfExist();
		PasswordPage p=new PasswordPage(driver,wait);
		p.fillpwd("Learn@123");
		p.clickNext();
		try {
		sp.clickcaptchaIfExist();
		p.fillpwd("Learn@123");
		p.clickNext();
		}
		catch(Exception ex)
		{
			
		}
		
		p.clickMailicon();
		MailPage m=new MailPage(driver,wait);
		int x=m.getMailsCount();
		System.out.println("total mails in inbox is: " +x);
		
	}
	
}
