/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.sound.midi.Soundbank;
import vodafone.pojolar.Operator;
import vodafone.pojolar.TarifeOnerPojo;
import vodafone.pojolar.TarifePojo;
import vodafone.tarife_oner_islemler.Singleton;
import vodafone.tarife_oner_islemler.tarife_oner_compare;

/**
 *
 * @author LifeBook
 */
public class TarifeOner {
    
    private  ArrayList<TarifePojo> tarifeler = new ArrayList<TarifePojo>();
    private  TarifeOnerPojo        toner     = null;
     private HashMap<String, Operator> operatorler = new HashMap<String,Operator>();
     
    private  Operator  vodafone  ;
    private  Operator  turkcell  ;
    private  Operator  avea      ;
    private  Operator  sabit     ;
    private  Operator  diger     ;
    
            
        int     toplam_gorusme = 0 ;       
	int     aramaSayisi = 0;
	int     mesajSayisi = 0 ;
	int     toplamNumara = 0;
	Double  toplamSure = 0.0 ;
	Double  toplamMesajUcret = 0.0 ;
	Double  toplamAramaUcret = 0.0 ;
        Double  toplamPeriyodSure = 0.0;
    
        
        
    public  TarifeOner(TarifeOnerPojo ptoner) {        
         this.toner = ptoner;                 
         toplam_gorusme = toner.getToplam_gorusme();
	 aramaSayisi = toner.getAramaSayisi();
	 mesajSayisi = toner.getMesajSayisi();
	 toplamNumara = toner.getToplamNumara();
	 toplamSure = toner.getToplamSure();
	 toplamMesajUcret = toner.getToplamMesajUcret();
	 toplamAramaUcret = toner.getToplamAramaUcret();
         toplamPeriyodSure = toner.getToplamPeriyodSure();
        
         
         operatorler = toner.getOperatorler();
         
         vodafone = operatorler.get("Vodafone");
         turkcell = operatorler.get("Turkcell");
         avea     = operatorler.get("Avea");
         sabit    = operatorler.get("SabitHat");
         diger    = operatorler.get("Diğer Operatorler");
         
         tarifeler = Singleton.getInstance().getTarifeler();
         
         Collections.sort(tarifeler);
         tarifeOner();
         System.out.println("tarifeler size"+tarifeler.size());
    }
        
    
    
    
    public void tarifeOner(){
        
        ArrayList<tarife_oner_compare>  ilk_eleme = new ArrayList<tarife_oner_compare>();
        
        Double puan = 0.0;
        
        for(int i = 0 ; i< tarifeler.size() ; i++){
            puan = 0.0;
            TarifePojo  tpojo = tarifeler.get(i);
            
            Double  periyod_sure_dk   = toplamPeriyodSure/60;
            Double  vodafone_dakika   = vodafone.getToplam_periyod_sure() / 60;
            
            Integer tarife_heryone          = tpojo.getHer_yone_dakika();
            Integer tarife_vodafone         = tpojo.getVodafone_dakika();
            Integer tarife_mesaj_heryone    = tpojo.getHer_yone_sms();
            Integer tarife_mesaj_vodafone   = tpojo.getVodafone_sms();
             
            Double puan1 = 0.0 ;
            Double puan2 = 0.0 ;
            Double puan3 = 0.0 ;
            
            Double puan11 = 0.0 ;
            Double puan22 = 0.0 ;
            Double puan33 = 0.0 ;
            Double puan_ortalama23 = 0.0;
            
            Double puan_ortalama = 0.0;
            
            if(periyod_sure_dk < tarife_heryone){               
                puan1 = puan1 + (periyod_sure_dk/tarife_heryone) * 100 ;                
            } else {
               Double puan12 = ((periyod_sure_dk - tarife_heryone) / periyod_sure_dk ) * 100;
               puan11 = 100 - puan12;     
            }     
            if(vodafone_dakika < tarife_vodafone ){
                puan2 = puan2 + (vodafone_dakika/tarife_vodafone) * 100;
            } else {
               Double puan12 = ((vodafone_dakika - tarife_vodafone) / vodafone_dakika ) * 100;
               puan22 = 100 - puan12;
            }
            if(periyod_sure_dk-vodafone_dakika < tarife_heryone){               
                puan3 = puan3 + ((periyod_sure_dk-vodafone_dakika)/tarife_heryone) * 100;                
            } 
            else {
               Double puan12 = (((periyod_sure_dk-vodafone_dakika) - tarife_heryone) / (periyod_sure_dk-vodafone_dakika)  ) * 100;
               puan33 = 100 - puan12;
            }
            
            
            puan_ortalama = (puan2 + puan3) / 2;      
            if(puan1>puan_ortalama) puan_ortalama = puan1;

            puan_ortalama23 = (puan22 + puan33) / 2; 
            if(puan11>puan_ortalama23) puan_ortalama23 = puan11;
            
            
            Double mesaj_puan1 = 0.0;
            Double mesaj_puan2 = 0.0;
            Double mesaj_puan3 = 0.0;
            
            Double mesaj_ortalama = 0.0;
            if(tarife_mesaj_heryone> mesajSayisi && tarife_mesaj_heryone > 0) {
                mesaj_puan1 = (( new Double(mesajSayisi) / tarife_mesaj_heryone) * 100 ) * 0.40;
            }
            
            if(tarife_mesaj_vodafone> vodafone.getMesaj_sayisi() &&tarife_mesaj_vodafone > 0){
                mesaj_puan2 = (( new Double(vodafone.getMesaj_sayisi()) / tarife_mesaj_vodafone) * 100 ) * 0.40;
            }
            
            if(tarife_mesaj_heryone> mesajSayisi - vodafone.getMesaj_sayisi() &&tarife_mesaj_heryone > 0){
                mesaj_puan3 = (( new Double(mesajSayisi - vodafone.getMesaj_sayisi()) / tarife_mesaj_heryone) * 100 ) * 0.40;
            }
       
            mesaj_ortalama = (mesaj_puan2+ mesaj_puan3) / 2;
            if(mesaj_puan1>mesaj_ortalama) mesaj_ortalama = mesaj_puan1;
         
            puan_ortalama += mesaj_ortalama ;
            
            System.err.println(tpojo.getTarife_ismi() +" --> puan_ortalam-->"+puan_ortalama+" puan1->"+puan1+" puan2->" +puan2+" puan3->"+puan3 +" mesaj_puan->"+mesaj_ortalama +" puan_ortalam23-->"+puan_ortalama23);
            
            tarife_oner_compare oner = new tarife_oner_compare(tpojo.getFiyat(), puan_ortalama, tpojo, tpojo.getTarife_ismi());
            ilk_eleme.add(oner);
            
            
////            if(mesajSayisi)
        }
        
        
        
        Collections.sort(ilk_eleme);
        System.err.println("-----------------------------------------------------------------------------------------------------");
        for(int j = 0 ; j<ilk_eleme.size() ; j++){
            tarife_oner_compare oner = ilk_eleme.get(j);
            System.err.println("-----------------------------------------------");
            System.err.println(oner.getTarife_ismi()+"  puan-->"+oner.getPuan() +" fiyat-->"+oner.getFiyat());
        }
        
        
        
    }    
       
    
    
    public ArrayList<TarifePojo> getTarifeler() {
        return tarifeler;
    }

    public void setTarifeler(ArrayList<TarifePojo> tarifeler) {
        this.tarifeler = tarifeler;
    }




    

    
}
