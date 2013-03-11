/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.pojolar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author LifeBook
 */
public class OperatoreGorePojo {


	public OperatoreGorePojo() {
                
            
	}
        
        ArrayList<Operator1> operatorler_rapor = new ArrayList<Operator1>();
        
	HashMap<String, ArrayList<TelefonPojo>> turkcell    = new HashMap<String, ArrayList<TelefonPojo>>();
	HashMap<String, ArrayList<TelefonPojo>> vodafone    = new HashMap<String, ArrayList<TelefonPojo>>();
	HashMap<String, ArrayList<TelefonPojo>> avea        = new HashMap<String, ArrayList<TelefonPojo>>();
	HashMap<String, ArrayList<TelefonPojo>> sabithat    = new HashMap<String, ArrayList<TelefonPojo>>();
	HashMap<String, ArrayList<TelefonPojo>> diger       = new HashMap<String, ArrayList<TelefonPojo>>();
	HashMap<String, ArrayList<TelefonPojo>> internet    = new HashMap<String, ArrayList<TelefonPojo>>();

	HashMap<String,DatabasePojo>  databasepojo = new HashMap<String, DatabasePojo>();
        
        
        public void test(HashMap<String, ArrayList<TelefonPojo>> op, String name){
            
           HashMap<String, ArrayList<TelefonPojo>> operator = op;
           
           System.out.println("Operator  = "+name);
           
           Iterator<String> it = operator.keySet().iterator(); 
           
           while(it.hasNext()){
               String number = it.next();
               ArrayList<TelefonPojo> tel = operator.get(number);
               
               System.out.println(name+"size :"+tel.size());
               
           }
           
        }
        
        public void TEST (){
            
            test(turkcell,"turkcell");
             test(vodafone,"vodafone");
              test(avea,"avea");
               test(sabithat, "avea");
                test(diger,"diger");
        }
        
        public HashMap<String, ArrayList<TelefonPojo>> getTurkcell() {
		return turkcell;
	}

	public HashMap<String, ArrayList<TelefonPojo>> getVodafone() {
		return vodafone;
	}

	public HashMap<String, ArrayList<TelefonPojo>> getAvea() {
		return avea;
	}

	public HashMap<String, ArrayList<TelefonPojo>> getSabithat() {
		return sabithat;
	}

	public HashMap<String, ArrayList<TelefonPojo>> getDiger() {
		return diger;
	}

	public HashMap<String, ArrayList<TelefonPojo>> getInternet() {
		return internet;
	}

        public HashMap<String, DatabasePojo> getDatabasepojo() {
            return databasepojo;
        }

        public void setDatabasepojo(HashMap<String, DatabasePojo> databasepojo) {
            this.databasepojo = databasepojo;
        }

    public ArrayList<Operator1> getOperatorler_rapor() {
        return operatorler_rapor;
    }

    public void setOperatorler_rapor(ArrayList<Operator1> operatorler_rapor) {
        this.operatorler_rapor = operatorler_rapor;
    }
        
    
}
