package Test;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


 
public class RomefinderApp {
 
WebDriver  driver ;
Workbook wb;
Sheet sh1;
int numrow;
String name; 
int count = 0;
@Test(dataProvider="Meeting room")
public void TestFireFox(String name) throws InterruptedException 
 
{
	if (!name.equals("")) {
		//if (count == 0) {

				System.setProperty("webdriver.chrome.driver", "C://chromedriver_win32//chromedriver.exe");
				ChromeOptions options = new ChromeOptions();

				options.addArguments("--disable-extensions");

				driver = new ChromeDriver(options);
				driver.get("https://meetingroomfinder.herokuapp.com/");
				Thread.sleep(5000);
				driver.findElement(By.id("search-desktop")).sendKeys(name);
				/*Thread.sleep(5000);
				WebElement ImageFile = driver
						.findElement(By.cssSelector("#language_isi_image_container > #isi_image > img.isi_image"));
				Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
						"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
						ImageFile);
				if (ImagePresent) {
					System.out.println("Image is displayed.");
				} else {
					// driver.quit();
				}*/
			
		}
		// driver.quit();

}
 
@DataProvider(name="Meeting room")
public Object[][] TestDataFeed(){
	ReadExcelroomname read= new ReadExcelroomname("C:\\Users\\dhavalsh\\Desktop\\Meeting room.xlsx");
	
	int rowCount=read.getRowColumn(0);
	Object[][] row1=new Object[rowCount][1];
	for (int i=1;i<rowCount;i++){
		row1[i][0]=read.getdata(0, i, 0);
		
	}
	return row1;
}
@AfterTest
public void QuitTC(){
 
// close browser after execution


}}