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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FlightsSnapshots {

	public static void main(String[] args) throws IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");


		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		String Ptitle = driver.getTitle();
		String Pwindow = driver.getWindowHandle();
		System.out.println("Parent window handle : "+Pwindow);
		System.out.println("Parent window title : "+Ptitle);
		
		driver.findElement(By.xpath("//a[text() = ' FLIGHTS ']")).click();
		
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
		File destination = new File("./snapshots/FlightsInIRCTC.jpg");
		FileUtils.copyFile(source, destination);
		
		driver.quit();
		

	}

}
