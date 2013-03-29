/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.util.HashMap;
import java.util.Iterator;
import vodafone.pojolar.DatabasePojo;
import vodafone.pojolar.VodafoneDataSource;
import vodafone.pojolar.VodafonePojo;

/**
 *
 * @author LifeBook
 */
public class DatabaseInsert {
    
     private   HashMap<String, DatabasePojo> 	databasePojolar;
     private   VodafoneDataSource   data_source = new VodafoneDataSource();
        
     public DatabaseInsert(HashMap<String, DatabasePojo> 		databasePojolar) {
		// TODO Auto-generated constructor stub
		this.databasePojolar = databasePojolar;
		
     }
        
        public VodafoneDataSource kaydetDatabase(){
                        
                        Iterator<String> it1 = databasePojolar.keySet().iterator();
                        
			while(it1.hasNext()){
				
                                String unique_key = it1.next();
                                
                                DatabasePojo dpojo = databasePojolar.get(unique_key);
                                
                                String numara                           =               dpojo.getNumara();
                                String aranan_ismi                      =               dpojo.getAranan_ismi();
                                String 	operator 			= 		dpojo.getOperator();
                                int 	arama_sayisi 			= 		dpojo.getAramaSayisi();
                                int 	mesaj_sayisi 			=		dpojo.getMesajSayisi();
                                double toplam_mesaj_ucret  		= 		dpojo.getToplamMesajUcret();
                                double toplam_arama_ucret  		= 		dpojo.getToplamAramaUcret();
                                double toplam_arama_dakika              = 		dpojo.getToplamAramaDakika();
                                double ortalama_gorusme    		= 		dpojo.getOrtalamaGorusme();
                                double internet_miktar     		= 		dpojo.getToplamMiktar();
                                
                                
                           System.err.println(numara+" ->"+internet_miktar+" ->"+toplam_arama_dakika);
                            VodafonePojo vpojo = new VodafonePojo(); 
                            if(!aranan_ismi.isEmpty()) {
                                vpojo.setNumara(aranan_ismi);
                            }
                            else {
                                vpojo.setNumara(numara);
                            }
                            vpojo.setOperator(operator);
                            vpojo.setMesajSayisi(mesaj_sayisi);
                            vpojo.setAramaSayisi(arama_sayisi);
                            vpojo.setToplamDakika(toplam_arama_dakika);           
                            vpojo.setToplamAramaUcret(toplam_arama_ucret);
                            vpojo.setToplamMesajUcret(toplam_mesaj_ucret);
                            vpojo.setOrtalamaGorusme(ortalama_gorusme);
                                       
                            data_source.add(vpojo);
		}
                        
               return data_source;
	}

    public VodafoneDataSource getData_source() {
        return data_source;
    }
   
}
