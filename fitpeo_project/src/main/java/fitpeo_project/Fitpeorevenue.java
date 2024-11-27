package fitpeo_project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class Fitpeorevenue {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://fitpeo.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();
		driver.manage().window().maximize();

		//Dragging slider 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait.until ( ExpectedConditions.elementToBeClickable ( By.cssSelector ( "span.MuiSlider-root" ) ) );
		wait.until ( ExpectedConditions.visibilityOfElementLocated ( By.cssSelector ( "span.MuiSlider-root" ) ) );
		js.executeScript ( "arguments[0].scrollIntoView(true);" , driver.findElement ( By.cssSelector ( "div.MuiBox-root>div.MuiGrid-root" ) ) );
		WebElement slider = driver.findElement ( By.cssSelector ( "span.MuiSlider-root" ) );
		Actions action = new Actions(driver);
		action.moveToElement ( slider ).click ().dragAndDropBy (slider,-27,0  ).build ().perform ();
		Thread.sleep ( 1000 );
		int priceValue = Integer.parseInt( driver.findElement ( By.cssSelector ( "input.MuiInputBase-input" ) ).getAttribute("value"));
		wait.until ( ExpectedConditions.elementToBeClickable ( By.cssSelector ( "input.MuiInputBase-input" ) ) );
		wait.until ( ExpectedConditions.visibilityOfElementLocated ( By.cssSelector ( "input.MuiInputBase-input" ) ) );
		driver.findElement ( By.cssSelector ( "input.MuiInputBase-input" ) ).sendKeys ( Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE ) ;
		Thread.sleep ( 1000 );

		//Updating Text field to 560
		driver.findElement ( By.cssSelector ( "input.MuiInputBase-input" ) ).sendKeys ( Keys.chord("560") );
		String sliderPercent= driver.findElement ( By.cssSelector ( "span.MuiSlider-thumb" ) ).getAttribute ( "style" );
		sliderPercent = sliderPercent.replace ( "left:","" );
		sliderPercent = sliderPercent.replace ( ";","" );
		sliderPercent = sliderPercent.trim ();

		//validation for slider position
		try {	
			Assert.assertEquals(sliderPercent,"28%");
			System.out.println("slider position has changed to 560");
		}
		catch (Exception e) {
			System.out.println("slider position not canged ");
		}


		WebElement checkbox1=driver.findElement(By.xpath("(//*[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[1]"));
		checkbox1.click();
		WebElement checkbox2=driver.findElement(By.xpath("(//*[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[2]"));
		checkbox2.click();
		WebElement checkbox3=driver.findElement(By.xpath("(//*[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[3]"));
		checkbox3.click();
		WebElement checkbox4=driver.findElement(By.xpath("(//*[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[8]"));
		checkbox4.click();
		System.out.println("selected all cpt codes");

		//validation for Total Recurring Reimbursement Header
		WebElement totalReimbursementHeader = driver.findElement(By.cssSelector("header.MuiPaper-root>div>p:nth-child(4)>p") );// Update with the correct locator
		String totalReimbursementText = totalReimbursementHeader.getText();
		if (totalReimbursementText.contains("$110700")) {
			System.out.println("Validation successful: Total Recurring Reimbursement is $110700.");
		} else {
			System.out.println("Validation failed: Expected Total Recurring Reimbursement $110700, but got " + totalReimbursementText);
		}

	}


	}


