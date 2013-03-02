/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avea.islemler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


/**
 *
 * @author LifeBook
 */
public class DosyadanOkumaAvea {
    
    ArrayList<ArrayList<String>> excel_data = new ArrayList<ArrayList<String>>();

    public ArrayList<ArrayList<String>>  DosyaOku (String fileName) {
        
        try {
            
            InputStream	in = new FileInputStream(fileName);
            HSSFWorkbook wb = new HSSFWorkbook(in);                   
            HSSFSheet sheet = wb.getSheetAt(0);
            
            
            for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {

                    Row row = rit.next();

                    ArrayList<String> row_data = new ArrayList<String>();

                    for (Iterator<Cell> cit = row.cellIterator(); cit.hasNext(); ) {

                        Cell cell = cit.next();
                        // Do something here
                        if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){

                                //System.out.println(cell.getBooleanCellValue());
                                row_data.add(String.valueOf(cell.getBooleanCellValue()));
                        }
                        else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){

                                //System.out.println(cell.getNumericCellValue());
                                row_data.add(String.valueOf(cell.getNumericCellValue()));

                        }
                        else if(cell.getCellType() == Cell.CELL_TYPE_STRING){

                                //System.out.println(cell.getStringCellValue());
                                row_data.add(cell.getStringCellValue());

                        } else if(cell.getCellType() == Cell.CELL_TYPE_BLANK){

                                row_data.add(cell.getStringCellValue());

                        }
                    }
                    excel_data.add(row_data);
            }
            
            
        
        }catch (Exception ex){         
            ex.printStackTrace();          
        }
        
        return excel_data;
    }
    
    
}
