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
 * @author Ertugrul
 */
public class OperatorDataSource {
    
    ArrayList<Operator1>  data = new ArrayList<Operator1>();

    public void add(Operator1 oper){
        data.add(oper);
    }
    
    public void setData(ArrayList<Operator1>  data){
        this.data = data;
        for(int i = 0 ; i< data.size() ; i++){
            System.err.println(data.get(i).getOperator());
          
           System.err.println(data.get(i).getMesaj_sayisi());
        }
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
