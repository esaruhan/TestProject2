/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import vodafone.pojolar.OperatoreGorePojo;
import vodafone.pojolar.TelefonPojo;
import vodafone.utils.Utils;

/**
 *
 * @author LifeBook
 */
public class GorusmeleriDuzenle {
   
    
    public  OperatoreGorePojo duzenle(ArrayList<ArrayList<String>> gorusmeler){
        
        OperatoreGorePojo operatorler = new OperatoreGorePojo();
        
        
        
        for(int i = 2;gorusmeler!=null & i<gorusmeler.size() ; i ++){
            
            ArrayList<String>  line = gorusmeler.get(i);
            
            
            String tarih        = line.get(0);
            String type         = line.get(1);
            String numara       = line.get(2);
            String aranan_ismi     = line.get(3);
            String operator     = line.get(4);
            String sure         = line.get(5);
            String tutar        = line.get(6);
            
            
            TelefonPojo     telpojo = new TelefonPojo();
            
            String unique_key = numara+"**"+operator;
            telpojo.setUnique_key(unique_key);
            telpojo.setNumara(numara);
            telpojo.setOperator(operator);
            telpojo.setTutar(Utils.checkAndReturnLong(tutar));
            telpojo.setTarih(tarih);
            telpojo.setDate(ConvertToDate(tarih));
            telpojo.setType(type);
            telpojo.setAranan_ismi(aranan_ismi);
            
            if(type.equalsIgnoreCase("Telefon")){
                  telpojo.setSure(Utils.checkAndReturnLong(sure));
            } else if(type.equalsIgnoreCase("SMS")){
                  telpojo.setSure(Utils.checkAndReturnLong(sure));
            } else if(type.equalsIgnoreCase("İnternet Kullanımı")){
                 telpojo.setSure(Utils.checkAndReturnSure(sure));
            }
            
            
            if(operator.equalsIgnoreCase("Vodafone")){
                
                
                if(operatorler.getVodafone().containsKey(unique_key)){
                    
                     operatorler.getVodafone().get(unique_key).add(telpojo);
                } else{
                      operatorler.getVodafone().put(unique_key,new ArrayList<TelefonPojo>() ); 
                      operatorler.getVodafone().get(unique_key).add(telpojo);
                }
                
            } else if ( operator.equalsIgnoreCase("Avea")){
                
                
                if(operatorler.getAvea().containsKey(unique_key)){
      
                     operatorler.getAvea().get(unique_key).add(telpojo);
                } else{
                      operatorler.getAvea().put(unique_key,new ArrayList<TelefonPojo>() ); 
                      operatorler.getAvea().get(unique_key).add(telpojo);
                }
                
            } else if ( operator.equalsIgnoreCase("Turkcell")){
                
                
                if(operatorler.getTurkcell().containsKey(unique_key)){
                    
                     operatorler.getTurkcell().get(unique_key).add(telpojo);
                } else{
                      operatorler.getTurkcell().put(unique_key,new ArrayList<TelefonPojo>() ); 
                      operatorler.getTurkcell().get(unique_key).add(telpojo);
                }
                
            } else if ( operator.equalsIgnoreCase("Turk Telekom")||operator.equalsIgnoreCase("Tellcom İletişim")){
                
                if(operatorler.getSabithat().containsKey(unique_key)){
                    
                     operatorler.getSabithat().get(unique_key).add(telpojo);
                } else{
                      operatorler.getSabithat().put(unique_key,new ArrayList<TelefonPojo>() ); 
                      operatorler.getSabithat().get(unique_key).add(telpojo);
                }
                
            }  else if (type.contains("nternet")||unique_key.equalsIgnoreCase("Internet")){
                telpojo.setOperator("Internet");
                telpojo.setType("Internet");
               
                if(operatorler.getInternet().containsKey(unique_key)){
                    
                     operatorler.getInternet().get(unique_key).add(telpojo);
                     
                } else{
                      operatorler.getInternet().put(unique_key,new ArrayList<TelefonPojo>() ); 
                      operatorler.getInternet().get(unique_key).add(telpojo);
                }
            } else {
                
                if(operatorler.getDiger().containsKey(unique_key)){
                    
                     operatorler.getDiger().get(unique_key).add(telpojo);
                } else{
                      operatorler.getDiger().put(unique_key,new ArrayList<TelefonPojo>() ); 
                      operatorler.getDiger().get(unique_key).add(telpojo);
                }
                
            }
            
        }
        
        return operatorler;
    }
    
    private  Date ConvertToDate(String dateString){
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    Date convertedDate = new Date();
    try {
        convertedDate = dateFormat.parse(dateString);
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return convertedDate;
}
}
