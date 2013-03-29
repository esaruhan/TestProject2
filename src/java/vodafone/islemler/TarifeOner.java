/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import vodafone.pojolar.DatabasePojo;
import vodafone.pojolar.Operator;
import vodafone.pojolar.SMS;
import vodafone.pojolar.TarifeOnerPojo;
import vodafone.pojolar.TarifePojo;
import vodafone.tarife_oner_islemler.Singleton;
import vodafone.tarife_oner_islemler.TarifeData;
import vodafone.tarife_oner_islemler.TarifeDataSource;
import vodafone.tarife_oner_islemler.tarife_oner_compare;

/**
 *
 * @author LifeBook
 */
public class TarifeOner {
  
    private  ArrayList<TarifePojo>      tarifeler   = new ArrayList<TarifePojo>();
    private  TarifeOnerPojo             toner       = null;
    private  HashMap<String, Operator>  operatorler = new HashMap<String,Operator>();
     
    private ArrayList<tarife_oner_compare>        filtreleme = new ArrayList<tarife_oner_compare>();
    private  TarifeDataSource  tarife_data   = new TarifeDataSource();
    private  Operator  vodafone  ;
    private  Operator  turkcell  ;
    private  Operator  avea      ;
    private  Operator  sabit     ;
    private  Operator  diger     ;
    
    DatabasePojo internet ;
            
        int     toplam_gorusme = 0 ;       
	int     aramaSayisi = 0;
	int     mesajSayisi = 0 ;
	int     toplamNumara = 0;
	Double  toplamSure = 0.0 ;
	Double  toplamMesajUcret = 0.0 ;
	Double  toplamAramaUcret = 0.0 ;
        Double  toplamPeriyodSure = 0.0;
    
        
        
    public  TarifeOner(TarifeOnerPojo ptoner,DatabasePojo internet) {        
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
         this.internet = internet;

         vodafone = operatorler.get("Vodafone");
         turkcell = operatorler.get("Turkcell");
         avea     = operatorler.get("Avea");
         sabit    = operatorler.get("SabitHat");
         diger    = operatorler.get("Diğer Operatorler");
         
        tarifeler = Singleton.getInstance().getTarifeler();
        
//        System.err.println("Ücret:"+internet.getToplamAramaUcret());
//        System.err.println("Miktar:"+internet.getToplamMiktar());
         
         tarifeKonusmaFiltrele();
         if(mesajSayisi>0)
             tarifeMesajFiltrele();
         
         Collections.sort(filtreleme);  
         raporIcinHazirla();
         
//         System.err.println("-----------------------------------------------------------------------------------------------------");
//        for(int j = 0 ; j<filtreleme.size() ; j++){
//            tarife_oner_compare oner = filtreleme.get(j);
//            System.err.println("-----------------------------------------------");
//            System.err.println(oner.getTarife_ismi()+"            fiyat-->"+oner.getToplam_fiyat());
//        }
//
//         System.out.println("tarifeler size"+tarifeler.size());
    }
    
    public void raporIcinHazirla(){
        for(int i = 0 ; i< filtreleme.size() ; i++){
             tarife_oner_compare toner = filtreleme.get(i);
             TarifePojo tpojo = toner.getPojo();
             
             TarifeData tarife = new TarifeData();
             if(tpojo.isIsKontratVar()||tpojo.isKalmaSozu()){
                   tarife.setKontrat(""+tpojo.getKontrat_suresi()+"AY");                 
             } else {
                   tarife.setKontrat("-----");
             }
             tarife.setTarifeismi(toner.getTarife_ismi());
             tarife.setMesajpaket(toner.getMesaj_paket_ismi());
             tarife.setMesajpaketfiyat(toner.getMesaj_fiyat()+"");
             tarife.setTarifefiyat(toner.getKonusma_fiyat()+"");
             tarife.setToplamfiyat(toner.getToplam_fiyat()+"");
             tarife.setTarifegrub(tpojo.getTarife_grubu());
             
             tarife_data.add(tarife);
           
        }
        
    }
        
    public void tarifeMesajFiltrele(){
          ArrayList<SMS>   mesaj_paketleri  = Singleton.getInstance().getMesaj_paketleri();
          int mesaj_paket = uygunMesajPaketiBul();
          SMS sms = mesaj_paketleri.get(mesaj_paket);
          int mesaj_fiyat = sms.getFiyat();
          String paket_ismi = sms.getPaket_adi();
          System.err.println("Mesaj Paket:"+mesaj_paket + " Ad : "+paket_ismi +"  fiyat :"+mesaj_fiyat);
          for(int i = 0 ; i< filtreleme.size() ; i++){
                tarife_oner_compare toner = filtreleme.get(i);
                
                boolean konusma         = toner.isKonusma();
                boolean konusma_mesaj  = toner.isKonusma_mesaj();
                boolean mesaj          = toner.isMesaj();
                
                if(!konusma_mesaj){
                    toner.setMesaj_fiyat(mesaj_fiyat);
                    toner.setMesaj_paket_ismi(paket_ismi);
                    toner.setToplam_fiyat();
                } else {
                    toner.setMesaj_fiyat(0);
                    toner.setToplam_fiyat();
                }
              
          }
    }
    
    public int uygunMesajPaketiBul(){
//         ArrayList<SMS>   mesaj_paketleri  = Singleton.getInstance().getMesaj_paketleri();
        
         int kullanilan_mesaj = mesajSayisi;  //100 - 0  
         int vodafone_kullanilan_mesaj = vodafone.getMesaj_sayisi(); // 10 - 45
         int heryone_kullanilan_mesaj = 0;
         
         if(kullanilan_mesaj>0) {
            heryone_kullanilan_mesaj  = kullanilan_mesaj - vodafone_kullanilan_mesaj;
        }
         else {
            heryone_kullanilan_mesaj = vodafone_kullanilan_mesaj;
        }
         
        if(kullanilan_mesaj>500){
            if(heryone_kullanilan_mesaj==0&vodafone_kullanilan_mesaj<5000){
                return 3;
            } else {
                return 1;
            }         
        } else {
            
            if(kullanilan_mesaj>25&kullanilan_mesaj<100){
                return 0 ;
            } else if(kullanilan_mesaj<26) {
                return 4;
            } else {
                return 2;
            }
            
        }
    }
    
    public void tarifeKonusmaFiltrele(){
             
        for(int i = 0 ; i< tarifeler.size() ; i++){
           
            TarifePojo  tpojo = tarifeler.get(i);
            
            Double  periyod_sure_dk   = toplamPeriyodSure/60;
            Double  vodafone_dakika   = vodafone.getToplam_periyod_sure() / 60;
            
            Integer tarife_heryone          = tpojo.getHer_yone_dakika();
            Integer tarife_vodafone         = tpojo.getVodafone_dakika();
            Integer tarife_mesaj_heryone    = tpojo.getHer_yone_sms();
            Integer tarife_mesaj_vodafone   = tpojo.getVodafone_sms();
             
          
            boolean control_heryone = false;
            boolean control_vodafone = false;
            boolean control_heryone1 = false;
            
            boolean message_heryone = false;
            boolean message_vodafone = false;
            boolean message_heryone1 = false;
            
            boolean konusma = false;
            boolean mesaj   = false;
            boolean konusma_mesaj = false;
           
            if(periyod_sure_dk < tarife_heryone){               
                control_heryone = true;         
            }      
            if(vodafone_dakika < tarife_vodafone ){
               control_vodafone = true;
            } 
            if(periyod_sure_dk-vodafone_dakika < tarife_heryone){               
               control_heryone1  = true;     
            } 
           
            if(tarife_mesaj_heryone >= mesajSayisi && tarife_mesaj_heryone > 0) {
                message_heryone = true;
            }
            
            if(tarife_mesaj_vodafone> vodafone.getMesaj_sayisi() &&tarife_mesaj_vodafone > 0){
                message_vodafone = true;
            }
            
            if(tarife_mesaj_heryone> mesajSayisi - vodafone.getMesaj_sayisi() &&tarife_mesaj_heryone > 0){
                message_heryone1  = true;
            }
            
            mesaj = message_heryone ||(message_vodafone&&message_heryone1);
            konusma = control_heryone || (control_vodafone&&control_heryone1);
            konusma_mesaj = konusma && mesaj ;
           
            if(konusma || konusma_mesaj){
                tarife_oner_compare oner = new tarife_oner_compare(tpojo.getFiyat(), konusma,mesaj,konusma_mesaj, tpojo, tpojo.getTarife_ismi());
                oner.setToplam_fiyat();
                filtreleme.add(oner);
            }   
////            if(mesajSayisi)
        }   
        
    }    
       
    
    
    public ArrayList<TarifePojo> getTarifeler() {
        return tarifeler;
    }

    public void setTarifeler(ArrayList<TarifePojo> tarifeler) {
        this.tarifeler = tarifeler;
    }

    public TarifeDataSource getTarife_data() {
        return tarife_data;
    }

    public void setTarife_data(TarifeDataSource tarife_data) {
        this.tarife_data = tarife_data;
    }




    

    
}
