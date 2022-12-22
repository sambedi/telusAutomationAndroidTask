package android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class StandaloneTest {
	
	@Test
	public void selectAsset() throws MalformedURLException, InterruptedException
	{
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
			.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel_4_XL"); //emulator
		
		options.setApp("//Users//Sam//Projects//Telus//src//test//java//resources//telus-tv.apk");
				
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("com.optiktv:id/enter_guest_mode")).click();
				
		driver.findElement(AppiumBy.accessibilityId("On Demand")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.optiktv:id/viewBtn")));
		
		driver.findElement(AppiumBy.androidUIAutomator(
	             "new UiScrollable(new UiSelector().scrollable(true))" +
	       ".scrollIntoView(new UiSelector().text(\"Movies  >\"))")).click();
		Thread.sleep(5000);
		
		driver.findElement(AppiumBy.androidUIAutomator(
	             "new UiScrollable(new UiSelector().scrollable(true))" +
	       ".scrollIntoView(new UiSelector().text(\"18A\"))")).click();
		Thread.sleep(5000);
		
		String verifyRating = driver.findElement(By.id("com.optiktv:id/subText2")).getText();
		Assert.assertTrue(verifyRating.contains("18A"));
		
		driver.quit();
		service.stop();
		
	}

}
