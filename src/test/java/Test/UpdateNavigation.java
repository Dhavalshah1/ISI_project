package Test;

import static POM.TestUpdatelinks.EXL_FILE;

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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import POM.TestUpdatelinks;

public class UpdateNavigation extends TestUpdatelinks {
	
	  @BeforeClass
	  public void setup() throws IOException, InterruptedException
	  {
		  SetupBrowser();
		  
		  
			login();
		
		  
	  }
	  
	  @Test
  public void f() throws EncryptedDocumentException, InvalidFormatException, IOException {
	  	File file = new File(EXL_FILE);
		 
	    //Create an object of FileInputStream class to read excel file
	 
	    FileInputStream inputStream = new FileInputStream(file);
	    //FileOutputStream fos = new FileOutputStream(file);
	 
	    Workbook wb = WorkbookFactory.create(inputStream);
	 
	    Sheet TestSheet = wb.getSheet("links");
	 
	    //Find number of rows in excel file
	 
	    int rowCount = TestSheet.getLastRowNum()-TestSheet.getFirstRowNum();
	 
	    //Create a loop over all the rows of excel file to read it
	 
	    for (int i = 1; i <=236; i++) {
	 
	        Row row = TestSheet.getRow(i);
	       // Cell cell=row.createCell(1);
	       // cell.setCellType(cell.CELL_TYPE_STRING);
	 
	        //Create a loop to print cell values in a row
	       	String value=row.getCell(1).getStringCellValue();
	       	chnagenav(value);
	       	
	       	try
	       	{
	       	Assert.assertTrue(d1.findElement(By.xpath("//a[@href='#mm-2']")).isDisplayed());
	       	System.out.print("Passed with  "+"   " +value+"\n");
	    
	       	//cell.setCellValue("Passed");
	       	
	       	}
	       	catch(Exception ie)
	       	{
	       		System.out.print("Failed at"+"   "+value+"\n");
	       		//cell.setCellValue("Failed");
	       		       		
	       	}
	       	//wb.write(fos);
	       	//fos.close();
	       	
	       	
	  
  }
  }
}


