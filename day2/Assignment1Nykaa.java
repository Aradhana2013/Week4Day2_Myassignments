package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Assignment1Nykaa {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");


		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.nykaa.com/");
		
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		
		WebElement Loreal = driver.findElement(By.xpath("//div[@id='brandCont_Popular']//ul//li[5]//a//img"));
		Actions LP = new Actions(driver);
		LP.click(Loreal).perform();
		
		String Ptitle = driver.getTitle();
		
		if (Ptitle.contains("Paris")) {
			
			System.out.println("The title is correct and is: "+Ptitle);
			
		}
		
		driver.findElement(By.xpath("//div[@id='filter-sort']//div//div//button")).click();		
		driver.findElement(By.xpath("//label[@for='radio_customer top rated_undefined']//div[contains(@class,'control-indicator radio')]")).click();
		
		driver.findElement(By.xpath("//span[text()='Category']")).click();		
		driver.findElement(By.xpath("//ul[@id='custom-scroll']//ul//li//div//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//ul[@id='custom-scroll']//ul//li//ul//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("(//label[@class='control control-checkbox'])[2]")).click();
		
		
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		
		WebElement filter = driver.findElement(By.xpath("//span[@class='filter-value']"));
		String value = filter.getText();
		//System.out.println(value);
		
		if (value.contains("Shampoo")) {			
			System.out.println("Filter is applied with Shampoo");			
		}
		
		
		String PtitleO = driver.getTitle();
		String Pwindow = driver.getWindowHandle();
		System.out.println("Parent window title : "+PtitleO);
		System.out.println("Parent window handle : "+Pwindow);
		
		driver.findElement(By.xpath("//div[@class='product-listing']//div//div//div//div//a//div//img")).click();
		
		Set<String> Allwindows = driver.getWindowHandles();
		System.out.println("No of windows : "+Allwindows.size());
		
		List<String> Listofwindows = new ArrayList<>(Allwindows);
		String Childwindow = Listofwindows.get(1);
		
		
		driver.switchTo().window(Childwindow);
		
		String Ctitle = driver.getTitle();
		String Cwindow = driver.getWindowHandle();
		System.out.println("Child window title : "+Ctitle);
		System.out.println("Child widnow handle : "+Cwindow);
		
		
		driver.findElement(By.xpath("//span[text()='180ml']")).click();
		
		
		WebElement price = driver.findElement(By.xpath("//span[text()='MRP:']//following::span"));
		String MRP = price.getText();
		System.out.println("MRP of the product : "+MRP);
		
		
		driver.findElement(By.xpath("(//span[@class='btn-text'])[1]")).click();
		
		driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
		
		
		driver.switchTo().frame(0);
		
		WebElement GT = driver.findElement(By.xpath("(//div[@data-test-id='footer']//div//div//div//div//div//div//span)[1]"));
		String Grand = GT.getText();
		System.out.println("Grand total on cart page : "+Grand);
		
		
		
		
		driver.quit();
		
		
		
		
		
		
		
	}

}
