package resources;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	

	public ReadExcel(String xlFilePath) throws Exception {
		fis = new FileInputStream(xlFilePath);
		workbook = new XSSFWorkbook(fis);
		fis.close();
		
	}

	public void getCellData(String sheetName, String colName, int rowNum, String searchKey) {
		try {
			int col_Num = -1;
			int row_Num = -1;
			int testcase_row_Num = -1;
			String testStep;
			String expctedRsult;
			String scrnCaptrNeed;
			XSSFCell Cell;
			
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) 
			{
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}

			// **************************************************************************************

			/*
			 * it gets column no of ID Search for particular testcaseID in ID column
			 */

			for (int i = 0; i < sheet.getLastRowNum(); i++) 
			{
				row = sheet.getRow(i);

				if (row.getCell(col_Num).getStringCellValue().trim().equalsIgnoreCase(searchKey)) 
				{
					testcase_row_Num = i;
					break;
				}else System.out.println("not found search key");

			}

			/* Then read data from the next cell(Testcase name ) return a s a string */
			int testcaseName_col = col_Num+1 ;
			String Testcase = row.getCell(testcaseName_col).getStringCellValue().trim();
			int testStep_col = testcaseName_col + 1;
			int expctedRsult_col = testStep_col + 1;
			int scrnCaptrNeed_col = expctedRsult_col + 1;

			/* Declare a two dimensional array to store cell steps and expected value */
			
			ArrayList<String[]> arrayofSteps = new ArrayList<String[]>();

			for (int i = testcase_row_Num + 1; i < sheet.getLastRowNum(); i++)

			{ // getting values of teststep , expected result and scrn capture nedded clumns
				// from excel and adding to array list

				row = sheet.getRow(i);
				Cell = row.getCell(testStep_col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				if (cell==null) {
					// This cell is empty
					break;
				} else {
					
				
					
					testStep = (row.getCell(testStep_col)== null||row.getCell(testStep_col).getStringCellValue().equalsIgnoreCase(""))?
								"" : row.getCell(testStep_col).getStringCellValue();
					
					
					expctedRsult = (row.getCell(expctedRsult_col)== null||row.getCell(expctedRsult_col).getStringCellValue().equalsIgnoreCase(""))?
							"" : row.getCell(expctedRsult_col).getStringCellValue();
					
					scrnCaptrNeed = (row.getCell(scrnCaptrNeed_col)== null||row.getCell(scrnCaptrNeed_col).getStringCellValue().equalsIgnoreCase(""))?
							"" : row.getCell(scrnCaptrNeed_col).getStringCellValue();
					

					String Step[] = { testStep, expctedRsult, scrnCaptrNeed };
					
					arrayofSteps.add(Step);
				}
			}
			
			System.out.println(arrayofSteps);
			/*
			 * Then read data from next cell(Steps) and next cell (Expected result) and next
			 * cell (Screenscapture required?) If screenscapture required yes then find the
			 * image corresponding to that step and record it record "all expected result"
			 * strings that have Screen capture required in an array B and return it
			 * Continue reading steps and expected result until Steps are finished record
			 * steps in array C and return it
			 * 
			 * Now normal execution happens
			 * 
			 * After execution is done
			 * 
			 * Pass Testcase name to docwrite method Pass test steps C to docwrite method to
			 * document it Pass array B , whcih should serach for matching names in
			 * Screenshot folder and then document it
			 */

//			row = sheet.getRow(rowNum - 1);
//			cell = row.getCell(col_Num);
/*
			if (cell.getCellTypeEnum() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
				String cellValue = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					DateFormat df = new SimpleDateFormat("dd/MM/yy");
					Date date = cell.getDateCellValue();
					cellValue = df.format(date);
				}
				return cellValue;
			} else if (cell.getCellTypeEnum() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());*/
		} catch (Exception e) {
			e.printStackTrace();
			//return "row " + rowNum + " or column " + colName + " does not exist  in Excel";
		}
	}
}
