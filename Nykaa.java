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

	public class Nykaa {

		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub
			//call WDM 
			WebDriverManager.chromedriver().setup();
			//Launch the browser
			ChromeDriver driver = new ChromeDriver();
			//load the URL
			driver.get("https://www.nykaa.com/");
			//Maxi the Browser
			driver.manage().window().maximize();
			//2) Mouseover on Brands and Search L'Oreal Paris
			Actions builder = new Actions(driver);
			WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
			builder.moveToElement(brands).perform();
			//driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
			//3) Click L'Oreal Paris
			Thread.sleep(2000);
			driver.findElement(By.linkText("L'Oreal Paris")).click();
			//4) Check the title contains L'Oreal Paris(Hint-GetTitle)
			String title = driver.getTitle();
			if (title.contains("L'Oreal Paris")) {
				System.out.println("Entered into L'Oreal Paris");}
			else {
				System.out.println("Failed");}
			//5) Click sort By and select customer top rated
			driver.findElement(By.xpath("//button[@class=' css-n0ptfk']//span")).click();
			driver.findElement(By.xpath("//div[@class='control-value']//span[text()='customer top rated']")).click();
			//6) Click Category and click Hair->Click haircare->Shampoo
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='Category']")).click();
			driver.findElement(By.xpath("//span[text()='Hair']")).click();
			driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
			driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
			//7) Click->Concern->Color Protection
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='Concern']")).click();
			driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
			//8)check whether the Filter is applied with Shampoo
			WebElement shampoo = driver.findElement(By.xpath("//div[@class='css-19j3ean']//following::span"));
			String text = shampoo.getText();
			if (text.contains("Shampoo")) {
				System.out.println("Filter contains Shampoo");}
			else {
				System.out.println("Filter does not contains Shampoo");
			}
			//9) Click on L'Oreal Paris Colour Protect Shampoo
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[contains(text(),'Protect Shampoo')]")).click();
			//10) GO to the new window and select size as 175ml
			Set<String> windowHandles = driver.getWindowHandles();
			//need to change set to List
			List<String> listWindow = new ArrayList<String>(windowHandles);
			String window2 = listWindow.get(1);
			driver.switchTo().window(window2);
			//select size as 175ml
			WebElement dropDown = driver.findElement(By.xpath("//select[@class='css-2t5nwu']"));
			Select select = new Select (dropDown);
			select.selectByVisibleText("175ml");		
			//11) Print the MRP of the product
			WebElement mrp = driver.findElement(By.xpath("//span[text()='MRP:']/following::span"));
			String price = mrp.getText();
			System.out.println("The MRP of the product:"+price);
			//12) Click on ADD to BAG
			driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
			//13) Go to Shopping Bag 
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@class='cart-count']")).click();
			//14) Print the Grand Total amount
			//move to frame
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement frame = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
			driver.switchTo().frame(frame);
			Thread.sleep(2000);
			WebElement grand1 = driver.findElement(By.xpath("(//span[text()='Grand Total']//following::div[1])[1]"));
			String grandTotal1 = grand1.getText();
			System.out.println("Grand Total amount:"+grandTotal1);
			//15) Click Proceed
			driver.findElement(By.xpath("//span[text()='Proceed']")).click();
			//16) Click on Continue as Guest
			driver.findElement(By.xpath("//button[@class='btn full big']")).click();
			//17) Check if this grand total is the same in step 14
			WebElement grand = driver.findElement(By.xpath("//div[text()='Grand Total']//following::div[1]"));
			String grandTotal2 = grand.getText();
			if (grandTotal2.contains(grandTotal1)) {
				System.out.println("The Grand Total is Same");}
			else {
				System.out.println("The Grand Total is not Same");}
			//18) Close all windows
			driver.quit();
			}
			
			}
			
		