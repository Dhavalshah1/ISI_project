package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelroomname {
	XSSFWorkbook wb;
	XSSFSheet sh1;

	public  ReadExcelroomname(String Excelpath) {
		// TODO Auto-generated method stub
		
					  // Specify the path of file
		//C:\\Users\\dhavalsh\\Desktop\\Meeting room.xlsx
			 
			 
			   // load file
			   FileInputStream fis;
			try {
				 File src=new File(Excelpath);
				fis = new FileInputStream(src);
				 wb=new XSSFWorkbook(fis);
				
			} catch (Exception e) {
				 System.out.println(e.getMessage());
			}
			 
	}   // Load workbook
			   
	public String getdata (int sheetnumber,int row,int column){	
		
	
			    sh1= wb.getSheetAt(sheetnumber);
			    String date=sh1.getRow(row).getCell(column).getStringCellValue();
			   return date;
			  
			   	}
	public int getRowColumn(int sheetindex){
		int row=wb.getSheetAt(sheetindex).getLastRowNum();
		row=row+1;
		return row;
	}
	


}
		
		
		
		
		
		
		

	

