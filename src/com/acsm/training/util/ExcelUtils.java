package com.acsm.training.util;

import static org.apache.poi.ss.usermodel.Sheet.TopMargin;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtils {
	private static final boolean EXACTLY_FORMAT = true;
	private static final boolean EVALUATOR_FORMULA = true;
	private static final DataFormatter FORMATTER = new DataFormatter();
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Read cell
	 * 
	 * @param cell
	 * @return
	 */
	public static Object read(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING: {
			return cell.getRichStringCellValue().getString();
		}
		case Cell.CELL_TYPE_NUMERIC:
			if (EXACTLY_FORMAT)
				if (DateUtil.isCellDateFormatted(cell)) {
					return new SimpleDateFormat(DATE_FORMAT).format(cell
							.getDateCellValue());
				} else {
					return FORMATTER.formatCellValue(cell);
				}
			else {
				if (DateUtil.isCellDateFormatted(cell)) {
					return cell.getDateCellValue();
				} else {
					return cell.getNumericCellValue();
				}
			}
		case Cell.CELL_TYPE_BOOLEAN:
			if (EXACTLY_FORMAT)
				return FORMATTER.formatCellValue(cell);
			else
				return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_FORMULA:
			if (EVALUATOR_FORMULA) {
				FormulaEvaluator evaluator = cell.getSheet().getWorkbook()
						.getCreationHelper().createFormulaEvaluator();
				return read(evaluator.evaluateInCell(cell));
			} else
				return cell.getCellFormula();
		default:
			return null;
		}
	}

	/**
	 * Read row
	 * 
	 * @param row
	 * @return
	 */
	public static Object[] read(Row row) {
		Object[] cellData = new Object[row.getLastCellNum()];
		for (Cell cell : row) {
			cellData[cell.getColumnIndex()] = read(cell);
		}
		return cellData;
	}

	/**
	 * Read sheet
	 * 
	 * @param sheet
	 * @return
	 */
	public static Object[][] read(Sheet sheet) {
		Object[][] rowData = new Object[sheet.getLastRowNum() + 1][];
		for (Row row : sheet) {
			rowData[row.getRowNum()] = read(row);
		}
		return rowData;
	}

	/**
	 * Read excel book
	 * 
	 * @param wb
	 * @return
	 */
	public static Object[][][] read(Workbook wb) {
		int sheetCount = wb.getNumberOfSheets();
		Object[][][] data = new Object[sheetCount][][];
		for (int sheetNum = 0; sheetNum < sheetCount; sheetNum++) {
			Sheet sheet = wb.getSheetAt(sheetNum);
			data[sheetNum] = read(sheet);
		}
		return data;
	}

	/**
	 * Read assign sheet
	 * 
	 * @param wb
	 * @param sheetIndex
	 * @return
	 */
	public static Object[][] read(Workbook wb, int sheetIndex) {
		return read(wb.getSheetAt(sheetIndex));
	}

	/**
	 * Read assign sheet
	 * 
	 * @param wb
	 * @param sheetName
	 * @return
	 */
	public static Object[][] read(Workbook wb, String sheetName) {
		return read(wb.getSheet(sheetName));
	}

	/**
	 * Read assign row
	 * 
	 * @param sheet
	 * @param rowIndex
	 * @return
	 */
	public static Object[] read(Sheet sheet, int rowIndex) {
		return read(sheet.getRow(rowIndex));
	}

	/**
	 * Read assign row
	 * 
	 * @param wb
	 * @param sheetIndex
	 * @param rowIndex
	 * @return
	 */
	public static Object[] read(Workbook wb, int sheetIndex, int rowIndex) {
		return read(wb.getSheetAt(sheetIndex), rowIndex);
	}

	/**
	 * Read assign row
	 * 
	 * @param wb
	 * @param sheetName
	 * @param rowIndex
	 * @return
	 */
	public static Object[] read(Workbook wb, String sheetName, int rowIndex) {
		return read(wb.getSheet(sheetName), rowIndex);
	}

	/**
	 * Read assign cell
	 * 
	 * @param row
	 * @param cellIndex
	 * @return
	 */
	public static Object read(Row row, int cellIndex) {
		return read(row.getCell(cellIndex));
	}

	/**
	 * Read assign cell
	 * 
	 * @param sheet
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public static Object read(Sheet sheet, int rowIndex, int cellIndex) {
		return read(sheet.getRow(rowIndex), cellIndex);
	}

	/**
	 * Read assign cell
	 * 
	 * @param wb
	 * @param sheetIndex
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public static Object read(Workbook wb, int sheetIndex, int rowIndex,
			int cellIndex) {
		return read(wb.getSheetAt(sheetIndex), rowIndex, cellIndex);
	}

	/**
	 * Read assign cell
	 * 
	 * @param wb
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public static Object read(Workbook wb, String sheetName, int rowIndex,
			int cellIndex) {
		return read(wb.getSheet(sheetName), rowIndex, cellIndex);
	}

	/**
	 * Read excel book from file
	 * 
	 * @param file
	 * @return
	 */
	public static Object[][][] read(File file) {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(is);
			return read(wb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Read excel book from file path
	 * 
	 * @param path
	 * @return
	 */
	public static Object[][][] read(String path) {
		File file = new File(path);
		if (file.exists()) {
			return read(file);
		} else {
			throw new RuntimeException("File is not exist: " + path);
		}
	}

	public static void mergeBook(Workbook mergeBook, Workbook fromBook) {
		for (int i = 0; i < fromBook.getNumberOfSheets(); i++) {
			addSheet(mergeBook, fromBook.getSheetAt(i));
		}
	}

	/**
	 * Add sheet to book
	 * 
	 * @param wb
	 * @param fromSheet
	 */
	public static void addSheet(Workbook wb, Sheet fromSheet) {
		addSheet(wb, fromSheet, -1, -1, -1, -1);
	}

	/**
	 * Add sheet to book by range
	 * 
	 * @param wb
	 * @param fromSheet
	 * @param firstRow
	 * @param firstCell
	 * @param lastRow
	 * @param lastCell
	 */
	public static void addSheet(Workbook wb, Sheet fromSheet, int firstRow,
			int firstCell, int lastRow, int lastCell) {
		if (fromSheet != null) {
			Sheet newSheet = wb.createSheet();
			newSheet.setMargin(TopMargin, fromSheet.getMargin(Sheet.TopMargin));
			newSheet.setMargin(Sheet.BottomMargin,
					fromSheet.getMargin(Sheet.BottomMargin));
			newSheet.setMargin(Sheet.LeftMargin,
					fromSheet.getMargin(Sheet.LeftMargin));
			newSheet.setMargin(Sheet.RightMargin,
					fromSheet.getMargin(Sheet.RightMargin));

			PrintSetup ps = newSheet.getPrintSetup();
			ps.setLandscape(false); // Print style, true：horizontal,
									// false：vertical (default)
			ps.setVResolution((short) 600);
			ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); // Page style

			copyData(wb, fromSheet, newSheet, firstRow, firstCell, lastRow,
					lastCell);
		}
	}

	/**
	 * Copy sheet
	 * 
	 * @param wb
	 * @param fromSheet
	 * @param newSheet
	 * @param firstRow
	 * @param firstCell
	 * @param lastRow
	 * @param lastCell
	 */
	public static void copyData(Workbook wb, Sheet fromSheet, Sheet newSheet,
			int firstRow, int firstCell, int lastRow, int lastCell) {
		if (firstRow == -1 || firstRow < fromSheet.getFirstRowNum()) {
			firstRow = fromSheet.getFirstRowNum();
		}
		if (lastRow == -1 || lastRow > fromSheet.getLastRowNum()) {
			lastRow = fromSheet.getLastRowNum();
		}
		// Copy merged cell
		for (int i = 0; i < fromSheet.getNumMergedRegions(); i++) {
			CellRangeAddress range = fromSheet.getMergedRegion(i);
			if (range.getFirstRow() < firstRow)
				range.setFirstRow(firstRow);
			if (range.getFirstColumn() < firstCell)
				range.setFirstColumn(firstCell);
			if (range.getLastRow() > lastRow)
				range.setLastRow(lastRow);
			if (range.getLastColumn() > lastCell)
				range.setLastColumn(lastCell);
			newSheet.addMergedRegion(range);
		}
		// Set column width
		for (short i = 0; i <= lastCell - firstCell; i++) {
			fromSheet
					.setColumnWidth(i, fromSheet.getColumnWidth(i + firstCell));
			fromSheet.setColumnHidden(i,
					fromSheet.isColumnHidden(i + firstCell));
		}
		for (int i = 0; i <= lastRow - firstRow; i++) {
			Row fromRow = fromSheet.getRow(i + firstRow);
			if (fromRow == null) {
				continue;
			}
			Row newRow = newSheet.createRow(i);
			copyRow(fromRow, newRow, firstCell, lastCell);
		}
	}

	/**
	 * Copy row
	 * 
	 * @param fromRow
	 * @param newRow
	 * @param firstCell
	 * @param lastCell
	 */
	public static void copyRow(Row fromRow, Row newRow, int firstCell,
			int lastCell) {
		newRow.setHeight(fromRow.getHeight());
		if (firstCell == -1 || firstCell < fromRow.getFirstCellNum()) {
			firstCell = fromRow.getFirstCellNum();
		}
		if (lastCell == -1 || lastCell > fromRow.getLastCellNum()) {
			lastCell = fromRow.getLastCellNum();
		}
		for (int j = 0; j <= firstCell - lastCell; j++) {
			Cell fromCell = fromRow.getCell(j + firstCell);
			if (fromCell == null) {
				continue;
			}
			Cell newCell = newRow.createCell(j);
			copyCell(fromCell, newCell);
		}
	}

	/**
	 * Copy cell
	 * 
	 * @param fromCell
	 * @param newCell
	 */
	public static void copyCell(Cell fromCell, Cell newCell) {
		newCell.setCellStyle(fromCell.getCellStyle());
		int cType = fromCell.getCellType();
		newCell.setCellType(cType);
		switch (cType) {
		case HSSFCell.CELL_TYPE_STRING:
			newCell.setCellValue(fromCell.getRichStringCellValue());
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			newCell.setCellValue(fromCell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			newCell.setCellFormula(fromCell.getCellFormula());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			newCell.setCellValue(fromCell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			newCell.setCellValue(fromCell.getErrorCellValue());
			break;
		default:
			break;
		}
	}

	/**
	 * Add all data into a exist workbook
	 * 
	 * @param wb
	 * @param data
	 * @param sheetName
	 * @return
	 */
	public static Workbook write(Workbook wb, Object[][][] data,String[] sheetName) {
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle headCellStyle = wb.createCellStyle();
        headCellStyle.setFont(font);
        headCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        headCellStyle.setWrapText(true);
        
		DataFormat format = wb.createDataFormat();
		CellStyle commonCellStyle = wb.createCellStyle();
		commonCellStyle.setDataFormat(format.getFormat(DATE_FORMAT));
		commonCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		commonCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		commonCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		commonCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		commonCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		commonCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		commonCellStyle.setWrapText(true);
		int sheetCount = sheetName.length;
		int dataCount = data.length;
		
		
		for (int sheetNum = 0; sheetNum < (sheetCount < dataCount ? dataCount: sheetCount); sheetNum++) {
			Sheet sheet = null;
			if (sheetNum < sheetCount)
				sheet = wb.createSheet(sheetName[sheetNum]);
			else
				sheet = wb.createSheet();
			
			if (sheetNum < dataCount) {
				Object[][] sheetData = data[sheetNum];
				for (int rowNum = 0; rowNum < sheetData.length; rowNum++) {
					Object[] cellData = sheetData[rowNum];
					Row row = sheet.createRow(rowNum);
					//Default row height
					row.setHeightInPoints(35);
					for (int cellNum = 0; cellNum < cellData.length; cellNum++) {
						Object cellValue = cellData[cellNum];
						Cell cell = row.createCell(cellNum);
						if(rowNum==0){
							cell.setCellStyle(headCellStyle);
						}else{
							cell.setCellStyle(commonCellStyle);
						}
						if (cellValue != null) {
							if (cellValue instanceof String) {
								String value = (String) cellValue;
								cell.setCellValue(value);
							} else if (cellValue instanceof Double
									|| cellValue instanceof Float
									|| cellValue instanceof Long
									|| cellValue instanceof Integer
									|| cellValue instanceof Short
									|| cellValue instanceof Byte) {
								Double value = (Double) cellValue;
								cell.setCellValue(value);
							} else if (cellValue instanceof Boolean) {
								Boolean value = (Boolean) cellValue;
								cell.setCellValue(value);
							} else if (cellValue instanceof Date) {
								Date value = (Date) cellValue;
								cell.setCellValue(value);
							} else if (cellValue instanceof Calendar) {
								Calendar value = (Calendar) cellValue;
								cell.setCellValue(value);								
							}
							//Default columnd width
//							sheet.setColumnWidth(cellNum, 1000);
//							sheet.setDefaultColumnWidth(1000);
						}
					}
					
					//Over 250 rows do not autosize
					if(sheetData.length<=250){
						for(int cellNum = 0; cellNum < cellData.length; cellNum++){
							sheet.autoSizeColumn((short)cellNum); 
						}
					}
									
				}
			}
		}
		return wb;
	}

	/**
	 * Add all data into a new workbook
	 * 
	 * @param data
	 * @param sheetName
	 * @return
	 */
	public static Workbook write(Object[][][] data, String[] sheetName) {
		return write(new HSSFWorkbook(), data, sheetName);
	}

	/**
	 * Add all data into a new workbook
	 * 
	 * @param data
	 * @return
	 */
	public static Workbook write(Object[][][] data) {
		return write(data, new String[] {});
	}

	/**
	 * Add a sheet data into a exist workbook
	 * 
	 * @param wb
	 * @param sheetData
	 * @param sheetName
	 * @return
	 */
	public static Workbook write(Workbook wb, Object[][] sheetData,
			String sheetName) {
		return write(wb, new Object[][][] { sheetData },
				new String[] { sheetName });
	}

	/**
	 * Add a sheet data into a new workbook
	 * 
	 * @param sheetData
	 * @param sheetName
	 * @return
	 */
	public static Workbook write(Object[][] sheetData, String sheetName) {
		return write(new Object[][][] { sheetData }, new String[] { sheetName });
	}

	/**
	 * Add a sheet data into a new workbook
	 * 
	 * @param sheetData
	 * @return
	 */
	public static Workbook write(Object[][] sheetData) {
		return write(new Object[][][] { sheetData });
	}
}
