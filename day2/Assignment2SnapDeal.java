package week4.day2;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.analysis.function.Add;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Assignment2SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");


		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.snapdeal.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement sports = driver.findElement(By.xpath("(//span[contains(text(),'Men')])[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(sports).perform();

		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();


		WebElement count = driver.findElement(By.xpath("//span[@class='category-name category-count']"));
		String number = count.getText();
		System.out.println(number);

		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();

		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();

		driver.findElement(By.xpath("(//ul[@class='sort-value']//li)[2]")).click();
		
		Thread.sleep(4000);	
		
		
		List<WebElement> prices = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		String[] allprices = new String[prices.size()];
		
		List<Integer> allamounts = new ArrayList<>();
		
	
			
		for (int i = 0; i < prices.size(); i++) {

			allprices[i] = prices.get(i).getAttribute("display-price");
				
			int amount = Integer.parseInt(allprices[i]);	
			
			System.out.println(amount);
			
		     allamounts.add(i, amount);
							   
		}		
	
		System.out.println(allamounts);	
		Collections.sort(allamounts);		
		List<Integer> newamountlist = new ArrayList<>(allamounts);
		
		if (allamounts.equals(newamountlist)) {			
			System.out.println("Sorting is as per price from low to high");
		}
		
		
		driver.findElement(By.xpath("(//input[@class='input-filter'])[1]")).clear();		
		driver.findElement(By.xpath("(//input[@class='input-filter'])[1]")).sendKeys("900");
		
		
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).clear();		
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).sendKeys("1200");
		
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@value='Navy']//following::label")).click();
		
		WebElement result = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		Actions buildero = new Actions(driver);
		buildero.moveToElement(result).perform();
		
		
		WebElement quickview = driver.findElement(By.xpath("//div[contains(text(),'Quick View')]"));
		driver.executeScript("arguments[0].click()", quickview);
		
		
		
		WebElement am = driver.findElement(By.xpath("//span[contains(@class,'payBlkBig')]"));
		String itemamount = am.getText();
		System.out.println("Price of the item is : Rs " + itemamount);
		
		
		WebElement disc = driver.findElement(By.xpath("//span[contains(@class,'percent-desc')]"));
		String discper = disc.getText();
		System.out.println("Discount percentage is : " + discper);
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./snapshots/SnapdealShoes.jpg");
		FileUtils.copyFile(source, destination);
		
		driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
		
		Thread.sleep(3000);
		
		driver.quit();

	}	


}


