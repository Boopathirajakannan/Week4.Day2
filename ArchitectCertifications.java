package week4.day2;

	import java.time.Duration;
	import java.util.ArrayList;
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

	public class ArchitectCertifications {

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
			//Click Resources and 
			Shadow dom = new Shadow(driver);
			WebElement resources = dom.findElementByXPath("//span[text()='Learning']");
			Thread.sleep(2000);
			resources.click();
			//find webelemnt of Learning On Trailhead
			WebElement trailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
			//mouse hover on Learning On Trailhead
			Actions builder = new Actions (driver);
			Thread.sleep(5000);
			builder.moveToElement(trailHead).perform();
			//need to scroll & click certificate
			WebElement certifi = dom.findElementByXPath("//a[text()='Salesforce Certification']");
			builder.scrollToElement(certifi).perform();
			builder.click(certifi).perform();
			//Choose Your Role as Salesforce Architect
			driver.findElement(By.xpath("//img[@alt='Salesforce<br/>Architect']")).click();
			//Get the Text(Summary) of Salesforce Architect
			WebElement summary = driver.findElement(By.xpath("//h1[text()='Salesforce Architect']/following-sibling::div"));
			System.out.println("The summary of Salesforce Architect:" +summary.getText());
			//Salesforce Architect Certifications Available
			List <WebElement> Salesforce = driver.findElements(By.xpath("//div[@class='credentials-card_title']//a"));
			System.out.println("The Available Salesforce Architect Certifications:-");
			for (int i = 0; i < Salesforce.size(); i++) {
				int j = i+1;
				String name = Salesforce.get(i).getText();
				System.out.println(j+"."+name);
			}
			//Click on Application Architect
			WebElement appArchi = driver.findElement(By.xpath("//a[text()='Application Architect']"));
			builder.scrollToElement(appArchi).perform();
			builder.click(appArchi).perform();
			//Get the List of Certifications available
			//WebElement certi = driver.findElement(By.xpath("//div[@class='credentials-card_title']//a"));
			List <WebElement> avail = driver.findElements(By.xpath("//div[@class='credentials-card_title']//a"));
			System.out.println("The Available Certifications:-");
			for (int i = 0; i < avail.size(); i++) {
				int j = i+1;
				String text = avail.get(i).getText();
				System.out.println(j+"."+text);
			}
			String title = driver.getTitle();
			System.out.println("The Tittle is "+title);
			if (title.contains("Architect")) {
				System.out.println("Succesfully Done");
			}else {
				System.out.println("Failed");	
			}
			driver.quit();
			
		}
}
