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
        
        for(int i = 3 ;gorusmeler!=null & i<gorusmeler.size() ; i ++){
            
            ArrayList<String>  line = gorusmeler.get(i);
            
            String tarih        = line.get(0);
            String type         = line.get(1);
            String numara       = line.get(2);
            String bos_alan     = line.get(3);
            String operator     = line.get(4);
            String sure         = line.get(5);
            String tutar        = line.get(6);
            
            TelefonPojo     telpojo = new TelefonPojo();
            
            telpojo.setNumara(numara);
            telpojo.setOperator(operator);
            telpojo.setTutar(Utils.checkAndReturnLong(tutar));
            telpojo.setTarih(tarih);
            telpojo.setDate(ConvertToDate(tarih));
            telpojo.setType(type);
            
            if(type.equalsIgnoreCase("Telefon")){
                  telpojo.setSure(Utils.checkAndReturnLong(sure));
            } else if(type.equalsIgnoreCase("SMS")){
                  telpojo.setSure(Utils.checkAndReturnLong(sure));
            } else if(type.equalsIgnoreCase("İnternet Kullanımı")){
                 telpojo.setSure(Utils.checkAndReturnSure(sure));
            }
            
            
            if(operator.equalsIgnoreCase("Vodafone")){
                
                
                if(operatorler.getVodafone().containsKey(numara)){
                    
                     operatorler.getVodafone().get(numara).add(telpojo);
                } else{
                      operatorler.getVodafone().put(numara,new ArrayList<TelefonPojo>() ); 
                      operatorler.getVodafone().get(numara).add(telpojo);
                }
                
            } else if ( operator.equalsIgnoreCase("Avea")){
                
                
                if(operatorler.getAvea().containsKey(numara)){
                    
                     operatorler.getAvea().get(numara).add(telpojo);
                } else{
                      operatorler.getAvea().put(numara,new ArrayList<TelefonPojo>() ); 
                      operatorler.getAvea().get(numara).add(telpojo);
                }
                
            } else if ( operator.equalsIgnoreCase("Turkcell")){
                
                
                if(operatorler.getTurkcell().containsKey(numara)){
                    
                     operatorler.getTurkcell().get(numara).add(telpojo);
                } else{
                      operatorler.getTurkcell().put(numara,new ArrayList<TelefonPojo>() ); 
                      operatorler.getTurkcell().get(numara).add(telpojo);
                }
                
            } else if ( operator.equalsIgnoreCase("Turk Telekom")){
                
                if(operatorler.getSabithat().containsKey(numara)){
                    
                     operatorler.getSabithat().get(numara).add(telpojo);
                } else{
                      operatorler.getSabithat().put(numara,new ArrayList<TelefonPojo>() ); 
                      operatorler.getSabithat().get(numara).add(telpojo);
                }
                
            } else if ( operator.equalsIgnoreCase("Diğer Operatör")){
                
                if(operatorler.getDiger().containsKey(numara)){
                    
                     operatorler.getDiger().get(numara).add(telpojo);
                } else{
                      operatorler.getDiger().put(numara,new ArrayList<TelefonPojo>() ); 
                      operatorler.getDiger().get(numara).add(telpojo);
                }
                
            } else if (type.equalsIgnoreCase("İnternet Kullanımı")&numara.equalsIgnoreCase("Internet")){
                telpojo.setOperator("Internet");
                telpojo.setType("Internet");
               
                if(operatorler.getInternet().containsKey(numara)){
                    
                     operatorler.getInternet().get(numara).add(telpojo);
                     
                } else{
                      operatorler.getInternet().put(numara,new ArrayList<TelefonPojo>() ); 
                      operatorler.getInternet().get(numara).add(telpojo);
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
