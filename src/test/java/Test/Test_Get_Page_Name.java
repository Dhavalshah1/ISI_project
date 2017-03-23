package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import POM.TestUpdatelinks;

public class Test_Get_Page_Name extends TestUpdatelinks {

	public static String Target;
	public static String value;
	public WebElement link;
	public WebElement sitenav;
	public String Leo = "Fail";
	public String Nav = "Fail";
	public String bread = "Fail";
	public static int failed = 0;
	public int stock_get = 0;

	public static String pagename;
	
	
	
	@BeforeClass
	  public void setup() throws IOException, InterruptedException
	  {
		  SetupBrowser();
		  
		  login();
		  
	  }

	@Test
	public void Get_Page() throws EncryptedDocumentException, InvalidFormatException, IOException {
		File file = new File(EXL_FILE);

		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(inputStream);
		Sheet TestSheet = wb.getSheet("Links");
		int rowCount = TestSheet.getPhysicalNumberOfRows();

		// Create a loop over all the rows of excel file to read it
		
		try
		{
			
		    for (int i = 338; i <= 716; i++) {

			Row row = TestSheet.getRow(i);
			
			value = row.getCell(1).getStringCellValue();
			System.out.println(value);
			getmepagename(value);
			Cell cell=row.createCell(3);
            cell.setCellValue(pagename);
		    }
		}
			 catch (Exception ie) {
				System.out.print("Failed at" + "   " + value + "\n");
				
			}
			
			 finally
			    {
			    System.out.println("Test Case exit");
			    inputStream.close();
			    FileOutputStream fos =new FileOutputStream(file);
			    wb.write(fos);
				fos.close();
			    }

		}
	

	public static void getmepagename(String url) throws IOException {

		d1.navigate().to(url);
		failed = 0;

		try {
			wait_t.until(ExpectedConditions.presenceOfElementLocated(By.id("O365_MainLink_Settings")));

			if (!d1.getTitle().contains("Page not found")) {

				Assert.assertTrue(d1.findElement(By.xpath("//div[@class='isi-global-header-logo']/a/img")).isEnabled(),
						"Leo Image is NOT present");
				d1.findElement(By.id("O365_MainLink_Settings")).click();
				d1.findElement(By.linkText("Site settings")).click();
				d1.findElement(By.linkText("Title, description, and logo")).click();
				pagename = d1.findElement(By.id("ctl00_PlaceHolderMain_titleSection_ctl01_TxtWebTitle")).getAttribute("value");
                System.out.println("I am here"+pagename);
			} else {
				System.out.println("###############################################");
				System.out.println("Page not found in URL" + Target);
				System.out.println("###############################################");
				pagename = "Failed";
			}

		} catch (AssertionError e) {
			System.out.println("Assertion Error in URL" + Target);
		} catch (ElementNotFoundException e) {
			System.out.println("###############################################");
			System.out.println("ElementNotFoundException in URL" + Target);
			System.out.println("###############################################");
			failed = 4;
		} catch (NoSuchElementException e) {
			System.out.println("###############################################");
			System.out.println("NoSuchElementException in URL" + Target);
			System.out.println("###############################################");
			failed = 4;
		} catch (TimeoutException e) {
			System.out.println("###############################################");
			System.out.println("TimeoutException in URL" + Target);
			System.out.println("###############################################");
			failed = 5;
		}

	}

}
