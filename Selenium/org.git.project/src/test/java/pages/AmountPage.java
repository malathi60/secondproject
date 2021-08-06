package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import utilities.WebSiteUtility;

public class AmountPage
{
	public static RemoteWebDriver driver;
	public static FluentWait<RemoteWebDriver> wait;
	@FindBy(how=How.XPATH,using="//*[name()='svg']//*[name()='g' and @class='highcharts-label highcharts-tooltip highcharts-color-undefined']")
	private WebElement textofpoint;
	@FindBy(how=How.XPATH,using="(//*[name()='svg'])[2]//*[name()='g' and contains(@class,'highcharts-markers highcharts-series-2 highcharts-spline-series  highcharts-tracker')]//*[name()='path' and not(@visibility)]")
	private List<WebElement> linepoints;
	@FindBy(how=How.XPATH,using="//tr[contains(@class,'yearlypaymentdetails')]")
	private List<WebElement> amounts;
	public AmountPage(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
		this.wait=wait;
	}
	public ArrayList<String> collectAmount(ArrayList<String> x,RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		ArrayList<String> amount=new ArrayList<String>();
		for(String line:x)
		{
			String a[]=x.toString().split("?");
			String b=a[0].trim();
			String c=b.replace(",", "").substring(0, b.length()-4);
			//System.out.println(c);
			amount.add(c);
		}
		return(amount);
	}
	public ArrayList<String> getValueFromTable(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait,String amtype)
	{
		List<WebElement> amnts=wait.until(ExpectedConditions.visibilityOfAllElements(amounts));
		ArrayList<String> princ=new ArrayList<String>();
		ArrayList<String> intr=new ArrayList<String>();
		ArrayList<String> balnc=new ArrayList<String>();
		for(WebElement amount:amnts)
		{
			String a=amount.getText();
			String b[]=a.toString().split(" ");
			String p=b[2].replace(",", "").trim();
			princ.add(p);
			String i=b[4].replace(",", "").trim();
			intr.add(i);
			String bal=b[8].split(" ")[0].replace(",","").trim();
			balnc.add(bal);
			//System.out.println(p + "----" + i +"-----" + bal);	
		}
		if(amtype.equals("Interest"))
		{
			return(intr);
		}
		else if(amtype.equals("Principal"))
		{
			return(princ);
		}
		else
		{
			return(balnc);
		}
	}
	public int validateAmount(ArrayList<String> x,ArrayList<String> y)
	{
		int flag=0;
		for(int i=0;i<x.size();i++)
		{
			System.out.println(x.get(i)+ "---from bar=================from table---" +y.get(i));
			if(x.get(i).contains(y.get(i)))
			{
				flag=0;
			}
			else
			{
				flag=1;
			}
		}
		return(flag);
		
	}
	public static void main(String[] args) throws Exception
	{
		WebSiteUtility wu=new WebSiteUtility();
		driver=wu.openBrowser("chrome");
		wait=wu.defineWait(driver);
		wu.launchSite(driver,"url1");
		AmountPage ap=new AmountPage(driver,wait);
		HomePage hp=new HomePage(driver,wait);
		ArrayList<String> v=ap.getValueFromTable(driver, wait, "Balance");
		/*for(String x:v) 
		{
			System.out.println(x);
		}*/
		hp.moveToGraph();
		ArrayList<String> u=hp.getTextOfBarpoints(hp.collectAllpointsOnBars(driver, wait), "Balance");
		if(ap.validateAmount(u, v)==0)
		{
			System.out.println("Test passed");
		}
		else
		{
			System.out.println("Test failed");
		}
		wu.closeSite(driver);
	}
}
