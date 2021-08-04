package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import utilities.WebSiteUtility;

public class LoginPage
{
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	@FindBy(how=How.NAME,using="identifier")
	private WebElement uid;
	@FindBy(how=How.XPATH,using="//span[text()='Next']/parent::button")
	private WebElement next;
	@FindBy(how=How.NAME,using="password")
	private WebElement pwd;
	@FindBy(how=How.XPATH,using="//*[text()='Compose']")
	private WebElement compose;
	@FindBy(how=How.NAME,using="to")
	private WebElement to;
	@FindBy(how=How.NAME,using="subjectbox")
	private WebElement sub;
	@FindBy(how=How.XPATH,using="//div[text()='Send']")
	private WebElement send;
	@FindBy(how=How.XPATH,using="//div[@aria-label='Attach files']")
	private WebElement att;
	public LoginPage(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		AjaxElementLocatorFactory af=new AjaxElementLocatorFactory(driver,50);
		PageFactory.initElements(af,this);
		this.driver=driver;
		this.wait=wait;
	}
	public void filluid(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(uid)).sendKeys(x);
	}
	public void fillpwd(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(pwd)).sendKeys(x);
	}
	public void clickNext()
	{
		wait.until(ExpectedConditions.visibilityOf(next)).click();;
	}
	public void clickAttach()
	{
		wait.until(ExpectedConditions.visibilityOf(att)).click();;
	}
	public void clicksend()
	{
		wait.until(ExpectedConditions.visibilityOf(send)).click();;
	}
	public void fillto(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(to)).sendKeys(x);
	}
	public void fillsub(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(sub)).sendKeys(x);
	}
	public void clickCompose()
	{
		wait.until(ExpectedConditions.visibilityOf(compose)).click();;
	}
	public void attachFile() throws IOException, Exception
	{
		WebSiteUtility wu=new WebSiteUtility();
		By b=wu.getByFromWebElement(att);
		wait.until(ExpectedConditions.presenceOfElementLocated(b)).click();
		Process pro=Runtime.getRuntime().exec("src\\test\\resources\\fileupload.exe");
		pro.waitFor();
	}
	public void attachfileSikulix() throws FindFailed
	{
		WebSiteUtility wu=new WebSiteUtility();
		By b=wu.getByFromWebElement(att);
		wait.until(ExpectedConditions.presenceOfElementLocated(b)).click();
		Screen s=new Screen();
		s.exists("src\\test\\resources\\fileupload.PNG");
		s.type("C:\\Users\\Dell\\Pictures\\images.jpg");
		s.exists("src\\test\\resources\\openfile.png");
		s.click();
		//s.click("src\\test\\resources\\openfile.png");
		
	}
	
	
}
