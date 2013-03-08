/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.tarife_oner_islemler;

import java.util.ArrayList;
import vodafone.pojolar.TarifePojo;

/**
 *
 * @author LifeBook
 */
public class Singleton {
    private static Singleton instance = new Singleton();
    private  ArrayList<TarifePojo> tarifeler = new ArrayList<TarifePojo>();
    private  static String  contextPath  = "";
    
    private Singleton(){           
             TarifeOku oku = new TarifeOku();
             tarifeler = oku.readTarifeler();   
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

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String contextPath) {
        Singleton.contextPath = contextPath;
    }
    
    
    
}
