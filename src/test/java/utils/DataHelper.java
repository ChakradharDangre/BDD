package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.Assert;

public class DataHelper {

    private static Workbook workbook;

    public static HashMap<String, String> readData(String filePath, String sheetName, String caseName) {
        HashMap<String, String> data = null;

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            Sheet sheet = null;

            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            }

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                if (workbook.getSheetName(i).contentEquals(sheetName)) {
                    sheet = workbook.getSheetAt(i);
                    break;
                }
            }

            if (sheet == null) {
                Assert.fail("Sheet with name \"" + sheetName + "\" does not exist in \"" + filePath + "\"..!!");
            }

            Row headerRow = sheet.getRow(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row currentRow = sheet.getRow(i);
                Cell caseNameCell = currentRow.getCell(0);

                if (caseNameCell != null && caseNameCell.getStringCellValue().equals(caseName)) {
                    data = new HashMap<>();
                    DataFormatter formatter = new DataFormatter();

                    for (int j = 1; j < headerRow.getPhysicalNumberOfCells(); j++) {
                        Cell headerCell = headerRow.getCell(j);
                        Cell currentCell = currentRow.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                        if (headerCell != null) {
                            String key = formatter.formatCellValue(headerCell);
                            String value = (currentCell != null) ? formatter.formatCellValue(currentCell) : "";
                            data.put(key, value);
                        }
                    }
                    break;
                }
            }

            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail("File not found, please provide the correct path.");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Error reading the file.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An error occurred while reading data from the file \"" + filePath + "\".");
        }

        if (data == null) {
            Assert.fail("Specified data is not available or might be numeric in the excel (Add single quotes to the data).");
        }

        return data;
    }

    public static HashMap<String, String> readDataRowWise(String filePath, String sheetName, String caseName) {
        HashMap<String, String> data = null;

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            Sheet sheet = null;

            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            }

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                if (workbook.getSheetName(i).contentEquals(sheetName)) {
                    sheet = workbook.getSheetAt(i);
                    break;
                }
            }

            if (sheet == null) {
                Assert.fail("Sheet with name \"" + sheetName + "\" does not exist in \"" + filePath + "\"..!!");
            }

            Row headerRow = sheet.getRow(0);

            for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
                Cell headerCell = headerRow.getCell(j);

                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                    Row currentRow = sheet.getRow(i);
                    Cell caseNameCell = currentRow.getCell(0);

                    if (caseNameCell != null && caseNameCell.getStringCellValue().equals(caseName)) {
                        if (data == null) {
                            data = new HashMap<>();
                        }

                        DataFormatter formatter = new DataFormatter();
                        Cell currentCell = currentRow.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                        String key = formatter.formatCellValue(headerCell);
                        String value = (currentCell != null) ? formatter.formatCellValue(currentCell) : "";
                        data.put(key, value);
                    }
                }
            }

            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail("File not found, please provide the correct path.");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Error reading the file.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An error occurred while reading data from the file \"" + filePath + "\".");
        }

        if (data == null) {
            Assert.fail("Specified data is not available or might be numeric in the excel (Add single quotes to the data).");
        }

        return data;
    }

}

