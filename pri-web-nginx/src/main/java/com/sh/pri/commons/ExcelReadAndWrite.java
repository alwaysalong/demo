package com.sh.pri.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

/**
 * 解析excel工具类
 * @author admin
 *
 */
public class ExcelReadAndWrite {
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_XLSX = "xlsx";
	private static Log log = LogFactory.getLog(ExcelReadAndWrite.class);

	/***
	 * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
	 *   xls:使用HSSFWorkbook
	 *   xlsx：使用XSSFWorkbook
	 * @param filePath
	 * @return
	 */
	public static Workbook getWorkbook(File file, String filename) throws IOException {
		Workbook workbook = null;
		FileInputStream is = new FileInputStream(file);
		if(StringUtils.isBlank(filename)){
			workbook = new XSSFWorkbook(is);
		} else if (filename.endsWith(EXTENSION_XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (filename.endsWith(EXTENSION_XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		return workbook;
	}

	/**
	 * 读取excel文件内容
	 * 
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws FileFormatException
	 */
	public static List<List<String>> readExcel(String filename, File file) throws Exception {
		log.info("****************开始解析文件************");
		// 获取workbook对象
		Workbook workbook = null;
		List<List<String>> result = new ArrayList<List<String>>();
		try {
			workbook = getWorkbook(file, filename);
			// 读文件 一个sheet一个sheet地读取
			for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
				Sheet sheet = workbook.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				int firstRowIndex = sheet.getFirstRowNum();
				int lastRowIndex = sheet.getLastRowNum();
				log.info("****************count："+lastRowIndex+"************");
				if (lastRowIndex < 1) {
					continue;
				}
				// 读取数据行
				log.info("****************开始解读数据************");
				for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
					List<String> line = new ArrayList<String>();
					Row currentRow = sheet.getRow(rowIndex);// 当前行
					int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
					int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
					for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
						Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
						String currentCellValue = getCellValue(currentCell, true);// 当前单元格的值
						line.add(currentCellValue);
					}
					result.add(line);
				}
				log.info("****************结束解读数据************");
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			log.info("解析文件 error************" + e.getMessage());
		}
		log.info("****************结束解析文件************");
		return result;
	}

	/**
	 * 取单元格的值
	 * 
	 * @param cell
	 *            单元格对象
	 * @param treatAsStr
	 *            为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
	 * @return
	 */
	private static String getCellValue(Cell cell, boolean treatAsStr) {
		if (cell == null) {
			return "";
		}
		if (treatAsStr) {
			// 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
			// 加上下面这句，临时把它当做文本来读取
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

}

