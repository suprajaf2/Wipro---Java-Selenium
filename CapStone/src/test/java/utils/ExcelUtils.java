package utils;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getSheetData(String filePath, String sheetName) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("❌ Excel file not found at: " + filePath);
            return new Object[0][0]; // Avoid returning null
        }

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("❌ Sheet not found: " + sheetName);
                return new Object[0][0];
            }

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows - 1][cols]; // skip header row

            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = (cell == null) ? "" : cell.toString();
                }
            }
            return data;

        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }
}
