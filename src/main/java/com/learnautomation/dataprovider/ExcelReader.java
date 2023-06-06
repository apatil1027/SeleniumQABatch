package com.learnautomation.dataprovider;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader 
{
	
	public static XSSFWorkbook wb;
	
	public static Object[][] getDataFromSheet(String sheetName)
	{
		try 
		{
			wb = new XSSFWorkbook(new File(System.getProperty("user.dir")+"/TestData/AccountDetails.xlsx"));
			
		} catch (InvalidFormatException e) 
		{
			System.out.println("Invalid file "+e.getMessage());
		} catch (IOException e) {
			
			System.out.println("Unable to load the file "+e.getMessage());
		}
		
		int rows=wb.getSheet(sheetName).getPhysicalNumberOfRows();
		
		int column=wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
		
		Object[][]arr=new Object[rows-1][column];
				
		for(int i=1;i<rows;i++)
		{
			for(int j=0;j<column;j++)
			{
				arr[i-1][j]= getData(sheetName, i, j);
			}
			
		}
				
		return arr;
		
	}
	
	
	public static String getData(String sheetName,int row,int column)
	{
		 XSSFCell cell=wb.getSheet(sheetName).getRow(row).getCell(column);
		 
		 String data="";
		 
		 if(cell.getCellType()==CellType.STRING)
		 {
			 	data=cell.getStringCellValue();
		 }
		 else if(cell.getCellType()==CellType.NUMERIC)
		 {
			 	double valueInDouble=cell.getNumericCellValue();
			 	
			 	data=String.valueOf(valueInDouble);
			 
		 }
		 else if(cell.getCellType()==CellType.BOOLEAN)
		 {
			 boolean valueInBooleanCell=cell.getBooleanCellValue();
			 
			 data=String.valueOf(valueInBooleanCell);
		 }
		 else if(cell.getCellType()==CellType.BLANK)
		 {
			 data="";
		 }
		 
		 return data;
		 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
