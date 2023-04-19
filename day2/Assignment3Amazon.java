package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Assignment3Amazon {

	public static void main(String[] args) throws IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");


		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();

		WebElement Price = driver.findElement(By.xpath("//span[@class='a-price-whole']"));		
		String amount = Price.getText();
		System.out.println("Price of mobile displayed as first search record is " +amount);


		WebElement cr = driver.findElement(By.xpath("//span[@class='a-price-whole']"));		
		String ratings = cr.getText();		
		System.out.println("Number of customer ratings is " +ratings);


		String Ptitle = driver.getTitle();
		String Pwindow = driver.getWindowHandle();
		System.out.println("Parent window handle : "+Pwindow);
		System.out.println("Parent window title : "+Ptitle);



		driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();


		Set<String> Allwindows = driver.getWindowHandles();
		System.out.println(Allwindows.size());

		List<String> listofwindows = new ArrayList<>(Allwindows);
		String childwindow = listofwindows.get(1);

		driver.switchTo().window(childwindow);

		String Ctitle = driver.getTitle();
		String Cwindow = driver.getWindowHandle();
		System.out.println("Child window handle : "+Cwindow);
		System.out.println("Child window title : "+Ctitle);

		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./snapshots/OnePlusMobile.jpg");
		FileUtils.copyFile(source, destination);

		driver.findElement(By.id("add-to-cart-button")).click();

		driver.findElement(By.xpath("//form[@id='attach-view-cart-button-form']//span//span//input")).click();


		WebElement total = driver.findElement(By.xpath("//div[@data-name='Subtotals']//span[@id='sc-subtotal-amount-buybox']//span"));
		String subtotal = total.getText();		
		System.out.println("Cart Subtotal is "+subtotal);

		if(subtotal.contains(amount)) {
			System.out.println("Cart subtotal is correct");
		}
		else
			System.out.println("Cart subtotal is incorrect");

		driver.quit();

	}

}
