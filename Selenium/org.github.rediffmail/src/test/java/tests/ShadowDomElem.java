package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowDomElem 
{
	public static void main(String[] args) throws Exception
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://books-pwakit.appspot.com/");
		WebElement text_box=(WebElement) driver.executeScript("return(document.querySelector('book-app').shadowRoot"
				+ ".querySelector('app-header>app-toolbar.toolbar-bottom>book-input-decorator').querySelector('input#input'))");
		text_box.sendKeys("abdul kalam");
		driver.navigate().to("chrome://downloads/");
		WebElement text=(WebElement) driver.executeScript("return(document.querySelector('downloads-manager')."
			+ "shadowRoot.querySelector('downloads-toolbar').shadowRoot.querySelector('cr-toolbar')."
		+ "shadowRoot.querySelector('cr-toolbar-search-field').shadowRoot.querySelector('div#searchTerm>input#searchInput'))");
		text.sendKeys("abdul kalam");
		WebElement clear=(WebElement) driver.executeScript("return(document.querySelector('downloads-manager')."
				+ "shadowRoot.querySelector('downloads-toolbar').shadowRoot.querySelector('cr-toolbar')."
				+ "shadowRoot.querySelector('cr-toolbar-search-field')."
				+ "shadowRoot.querySelector('cr-icon-button#clearSearch').shadowRoot.querySelector('iron-icon'))");
		clear.click();
		WebElement select=(WebElement) driver.executeScript("return(document.querySelector('downloads-manager')."
				+ "shadowRoot.querySelector('downloads-toolbar')."
				+ "shadowRoot.querySelector('cr-icon-button').shadowRoot.querySelector('iron-icon'))");
		select.click();
		WebElement openfolder=(WebElement) driver.executeScript("return(document.querySelector('downloads-manager')."
				+ "shadowRoot.querySelector('downloads-toolbar')."
				+ "shadowRoot.querySelector('cr-action-menu>button.dropdown-item.clear-all+button'))");
		openfolder.click();
		Thread.sleep(5000);
		System.out.println("shadow-dom elements succesfully operated using javascript");
		driver.quit();
		}

}
