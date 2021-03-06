package KeyFinder;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLOutput;
import java.util.*;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * KeyFinder Database Handler Module was created by Mark Scott-Kiddie and Lewis Ross on 04/10/2018.
 */
public class KeyFinder {

    private List<List<String>> spreadSheet;

    public KeyFinder(){
        spreadSheet = new ArrayList<List<String>>();
    }

    public void displaySpecific(int y, int x){
        System.out.println(spreadSheet.get(y).get(x));
    }

    public void displayArray(){
        for(int r = 0; r < spreadSheet.size(); r++){
            for(int c = 0; c < spreadSheet.get(r).size(); c++){
                System.out.println(spreadSheet.get(r).get(c));
            }
        }
    }

    public void loadData() {
        try {
             //File myFile = new File("C:\\Users\\mwsco\\IdeaProjects\\KeyFinder\\src\\main\\resources\\Key Records Sample.xlsx");
            File myFile = new File("/Users/Lewis/Desktop/University/CM3108/KeyRecordsSample.xlsx");

            FileInputStream fis = new FileInputStream(myFile);

            //Finds the workbook instance for XLSX file
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

            //Return first sheet from the XLSX workbook
            XSSFSheet sheetOne = myWorkBook.getSheetAt(0);

            //Get iterator to move through all rows in the sheet
            Iterator<Row> rowIterator = sheetOne.iterator();

            //Traverse over the row of the XLSX file
            int rowNum = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                spreadSheet.add(new ArrayList<String>());

                //For each row iterate through the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                String fullRow = "";
                while (cellIterator.hasNext()) {


                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.println(cell.getStringCellValue() + "\t");
                            fullRow += cell.getStringCellValue() + "\t";
                            spreadSheet.get(rowNum).add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            System.out.println(cell.getNumericCellValue() + "\t");
                            fullRow += cell.getNumericCellValue() + "\t";
                            spreadSheet.get(rowNum).add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            System.out.println(cell.getBooleanCellValue() + "\t");
                            fullRow += cell.getBooleanCellValue() + "\t";
                            spreadSheet.get(rowNum).add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        case BLANK:
                            System.out.println("[BLANK]");
                            break;
                        case _NONE:
                            System.out.println(cell.getStringCellValue() + "\t");
                            fullRow += cell.getStringCellValue() + "\t";
                            spreadSheet.get(rowNum).add(cell.getStringCellValue());
                            break;

                        default:
                    }
                }
                System.out.println(fullRow);
                rowNum++;
            }

        } catch (Exception e) {
            System.out.println("error" + e);
            System.exit(1);
        }
    }
}
