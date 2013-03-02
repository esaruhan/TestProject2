/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.main;

import avea.islemler.DosyadanOkumaAvea;
import java.util.ArrayList;

/**
 *
 * @author LifeBook
 */
public class AveaTest {
    
    
    public static void main (String a[]){
        String filePath = "C://Users//LifeBook//Downloads//905056501019_201208.xls"; 
        DosyadanOkumaAvea  doku  = new DosyadanOkumaAvea();
        ArrayList<ArrayList<String>>  data = doku.DosyaOku(filePath);
        
        for(int i = 0 ; i<data.size() ; i++){
            
           ArrayList<String> line = data.get(i); 
            
           String tarih     = line.get(0);
           String saat      = line.get(1);
           String operator  = line.get(2);
           String type      = line.get(3);
           String numara    = line.get(4);
           String sure      = line.get(5);
           String gonderilen_data = line.get(6);
           String indirilen_data  = line.get(7);
           String toplam_data     = line.get(8);
           String tutar           = line.get(9);
           
        }
        


        System.out.println("data size:"+data.size());
    }
}
