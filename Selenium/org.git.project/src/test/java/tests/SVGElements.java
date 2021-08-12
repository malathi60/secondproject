package tests;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SVGElements {

	@Test
	public void methodSvg() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		Scanner sc=new Scanner(System.in);
		String items=sc.nextLine();
		driver.get("https://emicalculator.net/");
		FluentWait<ChromeDriver> wait=new FluentWait<ChromeDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(30));
		wait.pollingEvery(Duration.ofMillis(200));
		WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//*[name()='svg'])[2]")));
		driver.executeScript("arguments[0].scrollIntoView();", e);
		List<WebElement> barpoints=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//*[name()='svg']//*[name()='g' and contains(@class,'highcharts-column-series')]"
						+ "//*[name()='rect' and @opacity]")));
		ArrayList<String> type=new ArrayList<String>();
		ArrayList<String> details=new ArrayList<String>();
		System.out.println("total base points:" +barpoints.size());
		Actions a=new Actions(driver);
		WebElement item=wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[name()='text']//*[name()='tspan' and text()='"+items+"']")));
		WebElement text=null;
		for(WebElement point:barpoints)
		{
			a.moveToElement(point).perform();
			text=wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[name()='svg']//*[name()='g' and @class='highcharts-label highcharts-tooltip highcharts-color-undefined']")));
			//System.out.println(text.getText());
			details.add(text.getText());
			String x[]=text.getText().toString().split(":");
			String y=x[1].trim();
		//	System.out.println(y);
			type.add(y);
		}
		if(items.contains("Interest") | items.contains("Principal"))
		{
			for(int i=0;i<type.size();i++)
			{
				if(type.get(i).contains(items) && items.contains("Interest"))
				{
					System.out.println(details.get(i));
				}
				else if(type.get(i).contains(items) && items.contains("Principal"))
				{
					System.out.println(details.get(i));
				}
			}
		}
		else
		{
				a.moveToElement(item).perform();
				List<WebElement> b=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("(//*[name()='svg'])[2]//*[name()='g' and contains(@class,'highcharts-markers highcharts-series-2 highcharts-spline-series  highcharts-tracker')]//*[name()='path' and not(@visibility)]")));
				for(WebElement point:b)
				{
					a.moveToElement(point).perform();
					text=wait.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//*[name()='svg']//*[name()='g' and @class='highcharts-label highcharts-tooltip highcharts-color-undefined']")));
					System.out.println(text.getText());	
				}
		}
		
	}

}
