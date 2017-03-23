package POM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUpdatelinks {
	
	public static WebDriver d1=null;

	public Properties  pro=new Properties();
	public static final String SERV_PROP_FILE = "Resources/Global.properties_leo.txt";
	public static final String EXL_FILE ="Resources/Master_List_Page.xlsx";
	
	static By Username=By.id("user-signin");
	static By Passwd  =By.id("pass-signin");
	
	private static String Uid="dhavalsh";
	private static String Upwd="Twinkle@1977";
	
		
	public static WebDriverWait wait_t;
	
	public void SetupBrowser() 
	{
			
		//d1=new HtmlUnitDriver();
		
		d1.get("https://intusurg.okta.com/");
		d1.manage().deleteAllCookies();
		d1.manage().window().maximize();
		
		d1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		wait_t=new WebDriverWait(d1, 10);
	
	}
	
	public static void readExcel() throws IOException, EncryptedDocumentException, InvalidFormatException{
		 
	    //Create a object of File class to open xlsx file
	 
	    File file = new File(EXL_FILE);
	 
	    //Create an object of FileInputStream class to read excel file
	 
	    FileInputStream inputStream = new FileInputStream(file);
	 
	    Workbook wb = WorkbookFactory.create(inputStream);
	 
	    Sheet TestSheet = wb.getSheet("test");
	 
	    //Find number of rows in excel file
	 
	    int rowCount = TestSheet.getLastRowNum()-TestSheet.getFirstRowNum();
	 
	    //Create a loop over all the rows of excel file to read it
	 
	    for (int i = 0; i < rowCount+1; i++) {
	 
	        Row row = TestSheet.getRow(i);
	 
	        //Create a loop to print cell values in a row
	       	String value=row.getCell(0).getStringCellValue();
	       	chnagenav(value);
	       	verfiynav(value);
	       	
	    }
	        	
	 
	}
	 
	
	public static void login() throws InterruptedException
	{
				
		//d1.findElement(By.id("cred_userid_inputtext")).sendKeys("dhaval.shah1@intusurg.com");
		
		//d1.findElement(By.id("cred_password_inputtext")).click();
		
		d1.manage().deleteAllCookies();
		d1.findElement(Username).clear();
		d1.findElement(Username).sendKeys(Uid);
		//
		d1.findElement(Passwd).sendKeys(Upwd);
		//d1.wait(3000);
		d1.findElement(By.id("signin-button")).click();	
		
		//d1.switchTo().defaultContent();
		d1.findElement(By.xpath("//div[@id='container']/div/div/div/div[2]/ul[2]/li[9]/a/img")).click();
		//https://intusurg.okta.com/home/bookmark/0oa16w6hlcc2EnaPB1d8/2557?fromHome=true
		
	}
	
	public static void chnagenav(String url) throws IOException
	{
		d1.navigate().to(url);
		wait_t.until(ExpectedConditions.presenceOfElementLocated(By.id("O365_MainLink_Settings")));
		d1.findElement(By.id("O365_MainLink_Settings")).click();
		d1.findElement(By.linkText("Site settings")).click();
		d1.findElement(By.linkText("Navigation")).click();
		d1.findElement(By.xpath("//input[@value='uniqueLeftNavRadioButton']")).click();
		d1.findElement(By.xpath("//input[@value='inheritTopNavRadioButton']")).click();
		if (!d1.findElement(By.id("ctl00_PlaceHolderMain_currentNavSection_ctl03_currentIncludeSubSites")).isSelected())
		{
		d1.findElement(By.id("ctl00_PlaceHolderMain_currentNavSection_ctl03_currentIncludeSubSites")).click();
		}
		d1.findElement(By.id("ctl00_PlaceHolderMain_ctl01_RptControls_topOKButton")).click();
		wait_t.until(ExpectedConditions.presenceOfElementLocated(By.id("O365_MainLink_Settings")));
		//d1.findElement(By.xpath("//li[@class='static selected home']")).click();
		d1.navigate().refresh();
		//d1.navigate().to(url);
		wait_t.until(ExpectedConditions.presenceOfElementLocated(By.id("O365_MainLink_Settings")));
		
	}
	
	public static void verfiynav(String url)
	{
	
		
	}

}
