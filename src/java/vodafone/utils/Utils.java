/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.utils;

/**
 *
 * @author LifeBook
 */
public class Utils {
    
    
    public static Double checkAndReturnSure(String sure){
        sure = sure.replace(",", ".");
        if(sure!=null&sure.contains("KB")){
            sure = sure.replace("KB", "");
            sure = sure.trim();
            return Double.valueOf(sure);
        }else if(sure!=null&sure.contains("MB")){
            sure = sure.replace("KB", "");
            sure = sure.trim();
            return Double.valueOf(sure);
        } else {
            return 0.0;
        } 
    }
    public static Double checkAndReturnLong(String value){
        value = value.replace(",", ".");
        if(value!=null&&!value.isEmpty()&&!value.trim().isEmpty()){
            return Double.valueOf(value);
        } else {
            return 0.0;
        }
        
    }
}
