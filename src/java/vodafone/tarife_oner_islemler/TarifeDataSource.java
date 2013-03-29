/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.tarife_oner_islemler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author LifeBook
 */
public class TarifeDataSource {
    
private  ArrayList<TarifeData>  data = new ArrayList<TarifeData>();

public void add(TarifeData vpojo){
    data.add(vpojo);
}
  
public  Object[] getBeanArray()
{
        return data.toArray();
}
public  Collection getBeanCollection()
{   
        return Arrays.asList(data);
}
    
    public void clearData(){
        if(this.data!=null){
            data.clear();
        }
    }
}
