package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory 
{
	
	public static WebDriver getBrowser(String browserName)
	{
		WebDriver driver=null;
		
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C://chromedriver_win32//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
				 
			
			options.addArguments("--disable-extensions");
			 
			
			driver = new ChromeDriver(options); 
			
			
		}
		if(browserName.equalsIgnoreCase("Firefox"))
		{
			ProfilesIni profilesIni = new ProfilesIni();
			FirefoxProfile profile = profilesIni.getProfile("Test");
			driver = new FirefoxDriver(profile);
			
		}
		if(browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver","C://IEDriverServer_Win32_2.52.1//IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();	
		driver.manage().window().setPosition(new Point(0,0));
		
		driver.manage().window().setSize(new Dimension(1280,1024));
		
		/*String browserName1 = ((Capabilities)driver).getBrowserName();
		System.out.println(browserName1);*/
		return driver;
	}
	
	public static void quitApplication(WebDriver driver)
	{
		driver.quit();
	}
	
}
