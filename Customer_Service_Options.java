package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Customer_Service_Options {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//call WDM 
		WebDriverManager.chromedriver().setup();
		//Launch the browser
		ChromeDriver driver = new ChromeDriver();
		//load the URL
		driver.get("https://login.salesforce.com/");
		//Maxi the Browser
		driver.manage().window().maximize();
		//Enter the username as 
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		//Enter the password as
		driver.findElement(By.id("password")).sendKeys("Password$123");
		//click on the login button
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String window1 = driver.getWindowHandle();
		System.out.println("The 1st Window is "+window1);
		//click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("//span[text()='Mobile Publisher']/following::button[1]")).click();
		//Switch to the next window 
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("The Number of windows is:" +windowHandles.size());
		//to get 2nd window need to change from set to List
		List<String> listwindowHandles = new ArrayList<String>(windowHandles);
		String window2 = listwindowHandles.get(1);
		System.out.println("The 2nd Window is "+window2);
		//switch to 2nd window
		driver.switchTo().window(window2);
		//click on the confirm button
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		//click on Products
		Shadow dom = new Shadow(driver);
		WebElement products = dom.findElementByXPath("//span[text()='Products']");
		Thread.sleep(2000);
		products.click();
		//Mousehover on Service
		WebElement service = dom.findElementByXPath("//span[text()='Service']");
		Actions builder = new Actions(driver);
		Thread.sleep(2000);
		builder.moveToElement(service).perform();
		builder.click(service).perform();
		//Click on Customer Services
		WebElement cusSer = dom.findElementByXPath("//a[text()='Customer Service']");
		builder.scrollToElement(cusSer).perform();
		builder.click(cusSer).build().perform();
		//Get the names Of Services Available 
		//WebElement names = driver.findElement(By.xpath("//ul[@class='page-list page-list-level-1 ']//li//a"));
		List<WebElement> ser = driver.findElements(By.xpath("//ul[@class='page-list page-list-level-1 ']//li//a[contains(text(),'Service')]"));
		System.out.println("The Available Services are");
		for (int i = 0; i < ser.size(); i++) 
		{	int j = i+1;
			String text = ser.get(i).getText();
			System.out.println(j+"."+text);
			}
		String title = driver.getTitle();
		System.out.println("The Title is "+title);
		if (title.contains("Customer Service")){
			System.out.println("Sussfully Done");}
		else {
			System.out.println("Failed");
		}
	}
	}
			



