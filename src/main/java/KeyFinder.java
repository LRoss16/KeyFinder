import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLOutput;
import java.util.*;

import org.apache.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * KeyFinder Database Handler Module was created by Mark Scott-Kiddie and Lewis Ross on 04/10/2018.
 */
public class KeyFinder {

    public void loadData() {
        try {
            File myFile = new File("/resources/Key Record Sample.xlsx");

            FileInputStream fis = new FileInputStream(myFile);

            //Finds the workbook instance for XLSX file
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

            //Return first sheet from the XLSX workbook
            XSSFSheet sheetOne = myWorkBook.getSheetAt(0);

            //Get iterator to move through all rows in the sheet
            Iterator<Row> rowIterator = sheetOne.iterator();

            //Traverse over the row of the XLSX file
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                //For each row iterate through the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.println(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.println(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.println(cell.getBooleanCellValue() + "\t");
                            break;
                        case _NONE:
                            System.out.println(cell.getStringCellValue() + "\t");
                            break;

                        default:
                    }

                }
                System.out.println("");
            }

        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }
}