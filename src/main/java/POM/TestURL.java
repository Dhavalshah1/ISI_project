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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestURL {
	
	public static WebDriver d1=null;

	public Properties  pro=new Properties();
	public static final String SERV_PROP_FILE = "Resources/Global.properties_leo.txt";
	public static final String EXL_FILE ="Resources/Site_05077.xlsx";
	
	static By Username=By.id("user-signin");
	static By Passwd  =By.id("pass-signin");
	
	private static String Uid="dhavalsh";
	private static String Upwd="Twinkle@1977";
	
		
	public static WebDriverWait wait_t;
	
	public void SetupBrowser() throws IOException
	{
		System.setProperty("webdriver.chrome.driver", "C://chromedriver_win32//chromedriver.exe");
		d1=new ChromeDriver();
		//d1=new HtmlUnitDriver();
		
		d1.get("https://intusurgops.sharepoint.com/");
		
		d1.manage().window().maximize();
		
		d1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		wait_t=new WebDriverWait(d1, 10);
		d1.manage().deleteAllCookies();
	
	}
	
		
	public static void login()
	{
				
		d1.findElement(By.id("cred_userid_inputtext")).sendKeys("dhaval.shah1@intusurg.com");
		
		d1.findElement(By.id("cred_password_inputtext")).click();
		
		d1.findElement(Username).sendKeys(Uid);
		
		d1.findElement(Passwd).sendKeys(Upwd);
		
		d1.findElement(By.id("signin-button")).click();	
			
		
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
