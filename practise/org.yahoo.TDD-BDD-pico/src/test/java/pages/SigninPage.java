package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import utilities.WebsitUtility;

public class SigninPage 
{
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public WebsitUtility su;
	
	@FindBy(how=How.LINK_TEXT,using="Sign in")
	private WebElement sgnin;
	
	@FindBy(how=How.NAME,using="username")
	private WebElement uid;
	
	@FindBy(how=How.NAME,using="signin")
	private WebElement next;
	
	@FindBy(how=How.XPATH,using="//p[contains(text(),'Sorry, ')]")
	private WebElement blankuiderr;
	
	//constructor method
	public SigninPage(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		AjaxElementLocatorFactory af=new AjaxElementLocatorFactory(driver,50);
		PageFactory.initElements(af, this);
		this.driver=driver;
		this.wait=wait;
	}
	//operational methods
	public void clickSigin()
	{
		wait.until(ExpectedConditions.visibilityOf(sgnin)).click();
	}
	public void filluid(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(uid)).sendKeys(x);
	}
	public void clickNext()
	{
		wait.until(ExpectedConditions.visibilityOf(next)).click();
	}
	public boolean isBlankInvaliduiderr()
	{
		try 
		{
			su=new WebsitUtility();
			By b=su.getByFromWebElement(blankuiderr);
			//wait.until(ExpectedConditions.visibilityOf(blankuiderr));
			wait.until(ExpectedConditions.presenceOfElementLocated(b));
			return(true);
		}
		catch(Exception ex)
		{
			return(false);
		}
	}
	public boolean isPasswordVisible()
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
			return(true);
		}
		catch(Exception ex)
		{
			return(false);
		}
	}
}
