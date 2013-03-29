/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.tarife_oner_islemler;

import java.util.ArrayList;
import vodafone.pojolar.SMS;
import vodafone.pojolar.TarifePojo;

/**
 *
 * @author LifeBook
 */
public class Singleton {
    private  static Singleton               instance     = new Singleton();
    private         ArrayList<TarifePojo>   tarifeler    = new ArrayList<TarifePojo>();
     private         ArrayList<SMS>   mesaj_paketleri    = new ArrayList<SMS>();
    private  static String                  contextPath  = "" ;
    
    private Singleton(){           
         
    }
    
    public void mesajPaketOku(){
            
            SMS sms1 = new SMS("SMS Avantaj 100",5,100,0);  
            SMS sms2 = new SMS("SMS Avantaj 11.000 ",10,1000,10000);
            SMS sms3 = new SMS("Her Yöne Midi",8,500,0);
            SMS sms4 = new SMS("Vodafone'lularla Maksi",8,0,5000);
            SMS sms5 = new SMS("SMS Tanışma",3,25,0);
            
            mesaj_paketleri.add(sms1);
             mesaj_paketleri.add(sms2);
              mesaj_paketleri.add(sms3);
               mesaj_paketleri.add(sms4);
                mesaj_paketleri.add(sms5);
               
              
            
    }
    public static Singleton getInstance() {
        return instance;
    }

    public  ArrayList<TarifePojo> getTarifeler() {
        return tarifeler;
    }

    public  void setTarifeler(ArrayList<TarifePojo> tarifeler) {
        this.tarifeler = tarifeler;
    }

    public  String getContextPath() {
        return contextPath;
    }

    public  void setContextPath(String contextPath) {
        Singleton.contextPath = contextPath;
        TarifeOku oku = new TarifeOku();
        tarifeler = oku.readTarifeler();
        mesajPaketOku();
    }

    public ArrayList<SMS> getMesaj_paketleri() {
        return mesaj_paketleri;
    }

    public void setMesaj_paketleri(ArrayList<SMS> mesaj_paketleri) {
        this.mesaj_paketleri = mesaj_paketleri;
    }
        
    
}
