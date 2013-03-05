/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.tarife_oner_islemler;

import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import vodafone.pojolar.TarifePojo;

/**
 *
 * @author LifeBook
 */
public class TarifeOku {
    
    ArrayList<TarifePojo> tarifeler = new ArrayList<TarifePojo>();
    String tarifeDosyasi = "C:\\Users\\LifeBook\\Dropbox\\Vodafone Tarifeler_YENI.csv";
    public TarifeOku() {
        try {
            //csv file containing data
        
        CSVReader reader = new CSVReader(new FileReader(tarifeDosyasi));
        String [] nextLine;
        int lineNumber = 0;
        while ((nextLine = reader.readNext()) != null) {
          lineNumber++;
          System.out.println("Line # "  + nextLine.length);

          // nextLine[] is an array of values from the line
          System.out.println(nextLine[0]  + "etc...");
        }   
        } catch (IOException ex) {
            Logger.getLogger(TarifeOku.class.getName()).log(Level.SEVERE, null, ex);
        } 
      
    }
    
    public static void main (String ...a){
        new TarifeOku();
    }
    
    
}
