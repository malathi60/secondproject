package pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import utilities.WebSiteUtility;
public class HomePage
{
	public static RemoteWebDriver driver;
	public static FluentWait<RemoteWebDriver> wait;
	@FindBy(how=How.XPATH,using="(//*[name()='svg'])[2]")
	private WebElement graph;
	@FindBy(how=How.XPATH,using="//*[name()='svg']//*[name()='g' and contains(@class,'highcharts-column-series')]"
			+ "//*[name()='rect' and @opacity]")
	private List<WebElement> verticalbarpoints;
	@FindBy(how=How.XPATH,using="//*[name()='svg']//*[name()='g' and @class='highcharts-label highcharts-tooltip highcharts-color-undefined']")
	private WebElement textofpoint;
	@FindBy(how=How.XPATH,using="(//*[name()='svg'])[2]//*[name()='g' and contains(@class,'highcharts-markers highcharts-series-2 highcharts-spline-series  highcharts-tracker')]//*[name()='path' and not(@visibility)]")
	private List<WebElement> linepoints;
	public HomePage(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
		this.wait=wait;
	}
	public void moveToGraph()
	{
		WebElement x=wait.until(ExpectedConditions.visibilityOf(graph));
		driver.executeScript("arguments[0].scrollIntoView()", x);
	}
	public ArrayList<String> collectAllpointsOnBars(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		List<WebElement> x=wait.until(ExpectedConditions.visibilityOfAllElements(verticalbarpoints));
		ArrayList<String> bartype=new ArrayList<String>();
		ArrayList<String> bardetails=new ArrayList<String>();
		Actions a=new Actions(driver);
		for(WebElement point:x)
		{
			a.moveToElement(point).perform();
			WebElement value=wait.until(ExpectedConditions.visibilityOf(textofpoint));
			bardetails.add(value.getText());
			String b[]=value.getText().toString().split(":");
			String y=b[1].trim();
			bartype.add(y);
		}
		System.out.println("toatl point on graph:" + x.size());
		return(bardetails);
	}
	
	public ArrayList<String> getTextOfBarpoints(ArrayList<String> x,String amounttype)
	{
		ArrayList<String> list=new ArrayList<String>();
		if(amounttype.contains("Interest") || amounttype.contains("Principal"))
		{
			for(int i=0;i<x.size();i++)
			{
				if(x.get(i).contains(amounttype) && amounttype.contains("Interest"))
				{
					//System.out.println(x.get(i));
					list.add(getList(x.get(i)));	
				}
				else if(x.get(i).contains(amounttype) && amounttype.contains("Principal"))
				{
					//System.out.println(x.get(i));
					list.add(getList(x.get(i)));
				}
			}
		}
		else
		{
			Actions a=new Actions(driver);
			List<WebElement> b=wait.until(ExpectedConditions.visibilityOfAllElements(linepoints));
			for(WebElement point:b)
			{
				a.moveToElement(point).perform();
				WebElement t=wait.until(ExpectedConditions.visibilityOf(textofpoint));
				//System.out.println(t.getText());
				list.add(getBalanceList(t));
			}
		}
		return(list);
	}
	public static String getBalanceList(WebElement x)
	{
		String s=x.getText().split(":")[2].split(" ")[2].trim().replace(",", "");
		//System.out.println(s);
		String c=s.substring(0,s.length()-4).trim();
		return(c);
	}
	public static String getList(String x)
	{
		//System.out.println(x.split(":")[2].split(" ")[2].replace(",", ""));
		String a=x.split(":")[2].split(" ")[2].replace(",", "");
		String c=a.substring(0, a.length()-5);
		//System.out.println(c);
		return(c);
	}
	
	public static void main(String[] args) throws Exception
	{
		WebSiteUtility wu=new WebSiteUtility();
		driver=wu.openBrowser("chrome");
		wait=wu.defineWait(driver);
		wu.launchSite(driver,"url1");
		HomePage hp=new HomePage(driver,wait);
		hp.moveToGraph();
		ArrayList<String> a=hp.collectAllpointsOnBars( driver,wait);
		hp.getTextOfBarpoints(a, "Principal");
		wu.closeSite(driver);
	}
}
