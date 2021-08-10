package pages;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class LoginPage 
{
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	@FindBy(how=How.LINK_TEXT,using="Sign in")
	private WebElement signin;
	public void clickSignin()
	{
		wait.until(ExpectedConditions.visibilityOf(signin)).click();
	}
	
}
