package test_data;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Data_Provider {
	public static Workbook wb;
	public static Sheet sheet;
	public static Object[][] read_data(String sheetname){
		FileInputStream file=null;
		try {
			file = new FileInputStream("C:\\Users\\jvnsi\\eclipse-workspace\\Amazon_Project\\src\\test\\java\\test_data\\data.xlsx");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(file);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheet(sheetname);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}

}
