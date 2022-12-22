package android;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class AutomationTask extends BaseTest {
	
	@Test
	public void selectAsset() throws MalformedURLException, InterruptedException
	{
		
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
		
	}
	
}
