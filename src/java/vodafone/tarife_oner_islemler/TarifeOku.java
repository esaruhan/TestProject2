/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.tarife_oner_islemler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import vodafone.pojolar.TarifePojo;

/**
 *
 * @author LifeBook
 */
public class TarifeOku {
    
   private  ArrayList<TarifePojo> tarifeler = new ArrayList<TarifePojo>();
    String tarifeDosyasi = "C:\\VodafoneRaporlar\\Vodafone Tarifeler_YENI.csv";
    BufferedReader rd = null ;
    public TarifeOku() {

    }
    
    public  ArrayList<TarifePojo>  readTarifeler(){
                   try {
            File file = new File(tarifeDosyasi);
            rd = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-9" ));
            String line = "";
            line = rd.readLine();
            int count = 0 ;
            while((line = rd.readLine())!=null){
                count++;
              //  System.out.println(line);
                TarifePojo tpojo = new TarifePojo();                
                String values [] = line.split(";");
              //  System.out.println( "Array Size"+values.length);
                tpojo.setTarife_ismi(values[0]);
                tpojo.setTarife_grubu(values[1]);
                tpojo.setHer_yone_dakika(values[2].equalsIgnoreCase("SINIRSIZ")?99999:values[2].contains("######")?0:Integer.parseInt(values[2]));
                tpojo.setVodafone_dakika(values[3].equalsIgnoreCase("SINIRSIZ")?99999:values[3].contains("######")?0:Integer.parseInt(values[3]));
                tpojo.setSabithat_dakika(values[4].equalsIgnoreCase("SINIRSIZ")?99999:values[4].contains("######")?0:Integer.parseInt(values[4]));
                tpojo.setHer_yone_sms(values[5].equalsIgnoreCase("SINIRSIZ")?99999:values[5].contains("######")?0:Integer.parseInt(values[5]));
                tpojo.setVodafone_sms(values[6].equalsIgnoreCase("SINIRSIZ")?99999:values[6].contains("######")?0:Integer.parseInt(values[6]));
               
                if(values[7].equalsIgnoreCase("VAR")){
                    tpojo.setInternet_kota(values[8]);
                    tpojo.setIsInternetPaket(true);
                } else{ 
                    tpojo.setInternet_kota("0KB");
                }
                
                if(values[9].equalsIgnoreCase("VAR")){
                    tpojo.setIsKontratVar(true);
                    tpojo.setKontrat_suresi(Integer.parseInt(values[11]));
                } else{
                    tpojo.setKontrat_suresi(0);
                }
                
                if(values[10].equalsIgnoreCase("VAR")){
                    tpojo.setKalmaSozu(true);
                    tpojo.setKontrat_suresi(Integer.parseInt(values[11]));
                } 
                
                tpojo.setFiyat(Integer.parseInt(values[12]));
                tarifeler.add(tpojo);
           
               
            }
                   
            
            } catch (UnsupportedEncodingException e) 
	    {
			  e.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
			  e.printStackTrace();
	    }
	    catch (Exception e)
	    {
                e.printStackTrace();
			  e.printStackTrace();
	    } finally{
               if(rd!=null)
                   try {
                    rd.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
           }
          
                return tarifeler;   
    }
    
//    public static void main (String ...a){
//        new TarifeOku();
//    }
    
    
}
