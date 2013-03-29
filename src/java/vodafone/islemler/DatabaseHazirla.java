/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import vodafone.pojolar.DatabasePojo;
import vodafone.pojolar.OperatoreGorePojo;
import vodafone.pojolar.TelefonPojo;

/**
 *
 * @author LifeBook
 */
public final class DatabaseHazirla {

    
    private OperatoreGorePojo tum_pojolar ;
    
    public DatabaseHazirla(OperatoreGorePojo pojo) {
        
    this.tum_pojolar = pojo;

    HashMap<String, ArrayList<TelefonPojo>> 		turkcell 		= tum_pojolar.getTurkcell();
    HashMap<String, ArrayList<TelefonPojo>> 		vodafone 		= tum_pojolar.getVodafone();
    HashMap<String, ArrayList<TelefonPojo>> 		avea 			= tum_pojolar.getAvea();
    HashMap<String, ArrayList<TelefonPojo>> 		sabithat		= tum_pojolar.getSabithat();
    HashMap<String, ArrayList<TelefonPojo>> 		diger 			= tum_pojolar.getDiger();
    HashMap<String, ArrayList<TelefonPojo>> 		internet 		= tum_pojolar.getInternet();

    databaseHazirla(turkcell);
     databaseHazirla(vodafone);
      databaseHazirla(avea);
       databaseHazirla(sabithat);
        databaseHazirla(diger);
         databaseHazirla(internet);
    }
    
 public  void databaseHazirla( HashMap<String, ArrayList<TelefonPojo>>  operator){
     
     if(operator!=null&&operator.size()>0){
         
         Iterator<String> it = operator.keySet().iterator();
         
         while(it.hasNext()){
                      
            String unique_key = it.next();
            
            ArrayList<TelefonPojo>  pojolar = operator.get(unique_key);
            
            DatabasePojo dataPojo 	= new DatabasePojo();
				
            for(int i = 0; i<pojolar.size();i++){
                    
               TelefonPojo telPojo = pojolar.get(i);  
               
             
               dataPojo.setNumara(telPojo.getNumara());
               dataPojo.setOperator(telPojo.getOperator());
               dataPojo.setAranan_ismi(telPojo.getAranan_ismi());
               
               if(telPojo.getType().equalsIgnoreCase("Telefon")){
                   
                   dataPojo.incrementAramaSayisi();
                   dataPojo.incrementToplamAramaUcret(telPojo.getTutar());
                   dataPojo.incrementToplamDakika(telPojo.getSure());
                  
               } else if (telPojo.getType().equalsIgnoreCase("SMS")){
                   
                   dataPojo.incrementMesajSayisi();
                   dataPojo.incrementToplamMesajUcret(telPojo.getTutar());
                   
               }  else if (telPojo.getType().equalsIgnoreCase("Internet")){
                   dataPojo.incrementAramaSayisi();
                   dataPojo.incrementToplamAramaUcret(telPojo.getTutar());
                   dataPojo.incrementToplamMiktar(telPojo.getSure());
                   dataPojo.incrementToplamDakika(telPojo.getSure());
                
               } 
            } // for
            dataPojo.setOrtalamaGorusme();
            tum_pojolar.getDatabasepojo().put(unique_key, dataPojo);
         }
     }
 }
 

    



}
