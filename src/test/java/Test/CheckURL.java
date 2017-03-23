package Test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import POM.TestURL;

public class CheckURL extends TestURL{ 
	
	public String Target;
	public WebElement link;
	public WebElement sitenav;
	public String Leo="Fail";
	public String Nav="Fail";
	public String bread="Fail";
	public int failed=0;
	public int stock_get=0;
	
	@BeforeClass	
	  public void setup() throws IOException, InterruptedException
	  {
		//  Runtime.getRuntime().exec("cmd /c start C:\\000_Study\\mybat.bat");
		//  Thread.sleep(50000);
		  SetupBrowser();		  
		  login();
		  
	  }
	  
	
	  @Test
	  public void checkURL() throws EncryptedDocumentException, InvalidFormatException, IOException {
		  	File file = new File(EXL_FILE);
		  	
			 
		    FileInputStream inputStream = new FileInputStream(file);
			 		 
		    Workbook wb = WorkbookFactory.create(inputStream);
		   
		    Sheet TestSheet = wb.getSheet("Links"); 
		    
		    /*
		    ArrayList<String> listTwo = new ArrayList(Arrays.asList("ourcompany","mybenefitscareer", "ourresourcesapplications", "ourteams", "followed"));
		    ArrayList<String> places = new ArrayList();
		    
		    	    		 	 
		    int rowCount = TestSheet.getLastRowNum()-TestSheet.getFirstRowNum();*/
		    
		    try
		    {
		      for (int i = 1; i <=714; i++) {
	               Row row = TestSheet.getRow(i);	
	               Target=row.getCell(1).getStringCellValue();
	               checkmylink();
	               Cell cell=row.createCell(2);
	               Cell cell1=row.createCell(3);
	               Cell cell2=row.createCell(5);
	               cell.setCellType(cell.CELL_TYPE_STRING);
				   cell1.setCellType(cell.CELL_TYPE_STRING);
				   String err=Integer.toString(failed);
				   if (failed>0)
				   {
					   cell.setCellValue("Failed");
					   cell1.setCellValue(err);
					   
				   }
				  
				   else
				   {
					   cell.setCellValue("Passed");
				   }
				   
				   if (stock_get==6)
				   {
					   cell2.setCellType(cell.CELL_TYPE_STRING);
					   String err1=Integer.toString(stock_get);
					   //cell2.setCellValue("Stock Issue");
					   cell2.setCellValue(err1);
				   }
	               
		      }
		      
		    }    	
		    catch(Exception e)
		    {
		     System.out.println(e);
		     System.out.println("Failed for URL"+Target);
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
	  
	  public void checkmylink()
	  {
		  List<WebElement> alist=new ArrayList<WebElement>();
		  List<WebElement> sitelist=new ArrayList<WebElement>();
		  d1.navigate().to(Target);
		  failed=0;
		  stock_get=0;
		  try{
          wait_t.until(ExpectedConditions.presenceOfElementLocated(By.id("O365_MainLink_Settings")));
          
          if (!d1.getTitle().contains("Page not found"))
          {
			    		
          Assert.assertTrue(d1.findElement(By.xpath("//div[@class='isi-global-header-logo']/a/img")).isEnabled(),"Leo Image is NOT present");
	
          link=d1.findElement(By.xpath("//div[@id='isi-global-nav']/ul"));
	
          alist=link.findElements(By.tagName("li"));
	
          int asize=alist.size();
          if (asize!=5)
          {		    		    			    	
       	   for (int j=0;j<asize;j++)
       	   {
       		 System.out.println("###############################################");
			System.out.println(alist.get(j).getAttribute("class"));
			System.out.println("No Global Navigation"+Target);
			 System.out.println("###############################################");
			
		
       	   }
          }
          Assert.assertEquals(alist.size(), 5);    	
          
          Assert.assertEquals("Linking Employees Online", d1.findElement(By.xpath("//div[@class='isi-header-content']/p")).getText());
          
          WebElement stock=d1.findElement(By.className("isi-stock-info"));
          
          try{
          Assert.assertFalse(stock.isDisplayed());
          }
          catch(AssertionError e)
          {
        	  stock_get=6;
          }
                  
          
          
          //Assert.assertTrue(d1.findElement(By.linkText("Site Navigation")).isEnabled());
          
          sitenav=d1.findElement(By.xpath("//*[contains(@id,'QuickLaunchMenu')]/ul"));
          sitelist=sitenav.findElements(By.xpath ("//*[contains(@id,'QuickLaunchMenu')]/ul/li/a/span/span"));
          
          int count =0;
          //System.out.println(sitelist.size())
          for (int i=0;i<sitelist.size();i++)
          {
        	  if ((sitelist.get(i).getText().equals("HOME")))
        	  {
        		  
        		  count++;
        		  
        	  }
        	    if (!(sitelist.get(i).getText().equals("SITE CONTENT"))) {
        	    	

              	  System.out.println("###############################################");
              	  System.out.println(" No Site Conetnt at site Navagition or  "+Target);
              	  System.out.println("###############################################");
        	    }
        		if (!(sitelist.get(i).getText().equals("CREATE A COLLABORATION SITE")))  {

              	  System.out.println("###############################################");
              	  System.out.println(" No CREATE A COLLABORATION SITE  "+Target);
              	  System.out.println("###############################################");
        			
        		}
          }
         // System.out.println(count);
          
          try{
              Assert.assertFalse(stock.isDisplayed());
              }
              catch(AssertionError e)
              {
            	  stock_get=6;
              }
          
          if (count>3)
          {
        	  System.out.println("###############################################");
        	  System.out.println("More than 2 Home is present at URL "+Target);
        	  System.out.println("###############################################");
        	  failed=2;
        	  
          }
          else if (count==0)
          {
        	  System.out.println("###############################################");
        	  System.out.println(" No Home found  "+Target);
        	  System.out.println("###############################################");
        	  failed=3;
          }   
 
          if (failed==0)
          {
          
          System.out.println("Passed URL"+"\t"+Target);
          }
          if (stock_get==6)
          {
          
          System.out.println("Stock Stickrer"+"\t"+Target);
          }
          
          }
          else
          {
        	  System.out.println("###############################################");
        	  System.out.println("Page not found in URL"+Target);
        	  System.out.println("###############################################");
        	  failed=5;
          }               
          
          
       }
		  catch(AssertionError e)
		  {
			  System.out.println("Assertion Error in URL"+ Target);
		  }
		  catch (ElementNotFoundException e)
		  {
			  System.out.println("###############################################");
			  System.out.println("ElementNotFoundException in URL"+ Target);
			  System.out.println("###############################################");
			  failed=4;
		  }
		  catch(NoSuchElementException e)
		  {
			  System.out.println("###############################################");
			  System.out.println("NoSuchElementException in URL"+ Target);
			  System.out.println("###############################################");
			  failed=4;
		  }
		  catch(TimeoutException e)
		  {
			  System.out.println("###############################################");
			  System.out.println("TimeoutException in URL"+ Target);
			  System.out.println("###############################################");
			  failed=5;
		  }
	
	  }
	  
	  
	}
