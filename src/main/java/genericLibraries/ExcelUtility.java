package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class contains reusable methods to perform actions on excel file
 * @author Admin
 *
 */
public class ExcelUtility {
	private Workbook workbook;
	private DataFormatter df;
	/**
	 * This method is used to initialize the excel file
	 * @param excelPath
	 */
public void excelInit(String excelPath) {
	FileInputStream fis=null;
	try {
		fis = new FileInputStream(excelPath);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			try {
				 workbook=WorkbookFactory.create(fis);
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}


/**
 * This method is used to read single data from excel
 * @param sheetName
 * @param rowNum
 * @param cellNum
 * @return
 */
public String readDataFromExcel(String sheetName, int rowNum, int cellNum) {
	 df= new DataFormatter();
	return df.formatCellValue(workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
}


/**
 * This method is used to read multiple data from excel
 * @param sheetName
 * @return
 */
public Map<String,String> readDataFromExcel(String sheetName){
	Map<String,String> map= new HashMap<String, String>();
	df=new DataFormatter();
	Sheet sheet=workbook.getSheet(sheetName);
	
	for(int i=0;i<=sheet.getLastRowNum();i++) {
		String key= df.formatCellValue(sheet.getRow(i).getCell(0));
		String value=df.formatCellValue(sheet.getRow(i).getCell(1));
		map.put(key, value);
		
	}
	return map;
}
/**
 * This method is used to write and save data to excel
 * @param sheetName
 * @param rowNum
 * @param cellNum
 */
public void writeToExcel(String sheetName, int rowNum, int cellNum, String value, String excelpath) {
	Cell cell=workbook.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
	cell.setCellValue(value);
	FileOutputStream fos= null;
		try {
			fos=	new FileOutputStream(excelpath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
		workbook.write(fos);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void closeExcel() {
	try {
		workbook.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


























}
