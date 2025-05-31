package com.automation.utlilities;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcell {

	private static String dataSheetpath=null;

	public static void setDataSheetPath(String path){
		dataSheetpath=path;
	}
	public static Row returnRowOfCell(String cellData) {
		try {
			//FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\DataFiles\\TestData.xlsx");
			FileInputStream fis = new FileInputStream(dataSheetpath);
			BufferedInputStream bis = new BufferedInputStream(fis);
			String dataSheet = "DataSheet1";
			Workbook wb = new XSSFWorkbook(bis);
			int noOfSheets = wb.getNumberOfSheets();
			for (int i = 0; i < noOfSheets; i++) {
				System.out.println("sheet name: " + wb.getSheetName(i));
				if (wb.getSheetName(i).equalsIgnoreCase(dataSheet)) {
					Sheet sheet = wb.getSheetAt(i);
					Iterator<Row> rowi = sheet.iterator();
					Row r1 = null;
					while (rowi.hasNext()) {
						Row r = rowi.next();
						Iterator<Cell> celli = r.cellIterator();
						while (celli.hasNext()) {
							Cell singleCell = celli.next();
							String cellcontent = singleCell.getStringCellValue();
							if (cellcontent.equals(cellData)) {
								r1 = singleCell.getRow();
								return r1;
							}
						}
					}
				} // loopinsheets

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public static void printRowData(Row r)
	{
		Iterator<Cell> cell1=r.cellIterator();
		while(cell1.hasNext())
		{
			System.out.println(cell1.next().getStringCellValue());
		}
		
	}
	
	public static HashMap<String, String> returnTestData(Row firstRow,Row dataRow)
	{
		HashMap<String, String> testdata= new HashMap<String,String>();
		
		Iterator<Cell> firstRowCells= firstRow.cellIterator();
		Iterator<Cell> dataRowCells= dataRow.cellIterator();
		
		while(firstRowCells.hasNext())
		{
			testdata.put(firstRowCells.next().getStringCellValue(),dataRowCells.next().getStringCellValue());
		}
		
		
		return testdata;
		
	}

	public static Map<String,String> returnTestDataMap(String columnName,String rowName)
	{
		Row row1 = returnRowOfCell(columnName);
		Row row2 = returnRowOfCell(rowName);
		printRowData(row1);
		printRowData(row2);

		HashMap<String, String> testdata1= returnTestData(row1, row2);
		return testdata1;
	}
	



	public static void main(String[] args) {

		
	Row row1 = returnRowOfCell("Testcases");
	Row row2 = returnRowOfCell("Login");
	printRowData(row1);
	printRowData(row2);
	
	HashMap<String, String> testdata1= returnTestData(row1, row2);
	Set<String> keys= testdata1.keySet();
	
	for(String key:keys)
	{
		System.out.println("key is : "+key +" and value is : "+testdata1.get(key));
	}
	}

}
