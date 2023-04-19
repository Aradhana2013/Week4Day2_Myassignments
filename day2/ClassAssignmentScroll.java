package week4.day2;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class ClassAssignmentScroll {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-notifications");


		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.ajio.com/");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		
		WebElement scrollele = driver.findElement(By.xpath("//a[@title = 'Collections']"));	
		Actions builder = new Actions(driver);
		builder.scrollToElement(scrollele).perform();
		String text = scrollele.getText();
		System.out.println(text);
		
		
		
		driver.close();

	}

}
