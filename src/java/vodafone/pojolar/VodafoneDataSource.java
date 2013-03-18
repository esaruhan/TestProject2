/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.pojolar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author LifeBook
 */
public class VodafoneDataSource  {


ArrayList<VodafonePojo>  data = new ArrayList<VodafonePojo>();

public void add(VodafonePojo vpojo){
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
    
    
}
