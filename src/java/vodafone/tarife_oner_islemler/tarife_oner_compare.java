/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.tarife_oner_islemler;

import vodafone.pojolar.TarifePojo;

   public class tarife_oner_compare implements Comparable<tarife_oner_compare> {
        
       private  int toplam_fiyat = 0;
       private  int konusma_fiyat = 0;
       private  int mesaj_fiyat    = 0 ;
       private  int internet_fiyat = 0 ;
       private  boolean konusma = false;
       private  boolean mesaj = false;
       private  boolean konusma_mesaj = false;
       private  TarifePojo pojo ;
       private  String    tarife_ismi = "";
       private  String   mesaj_paket_ismi = "";
      
        public tarife_oner_compare(int tfiyat, boolean konusma ,boolean mesaj,boolean konusma_mesaj, TarifePojo tpojo,String ttarife_ismi) {
            konusma_fiyat = tfiyat;
            this.konusma = konusma ;
            this.konusma_mesaj = konusma_mesaj;
            this.mesaj = mesaj;
            pojo = tpojo ;
            tarife_ismi = ttarife_ismi;
                     
        }

    public int getToplam_fiyat() {
        return toplam_fiyat;
    }

//    public void setToplam_fiyat(int toplam_fiyat) {
//        this.toplam_fiyat = toplam_fiyat;
//    }
    
    
    
    public void setToplam_fiyat() {
     this.toplam_fiyat = this.mesaj_fiyat + this.konusma_fiyat;
    }

    public int getKonusma_fiyat() {
        return konusma_fiyat;
    }

    public void setKonusma_fiyat(int konusma_fiyat) {
        this.konusma_fiyat = konusma_fiyat;
    }

    
    
    public int getMesaj_fiyat() {
        return mesaj_fiyat;
    }

    public void setMesaj_fiyat(int mesaj_fiyat) {
        this.mesaj_fiyat = mesaj_fiyat;
    }

    public int getInternet_fiyat() {
        return internet_fiyat;
    }

    public void setInternet_fiyat(int internet_fiyat) {
        this.internet_fiyat = internet_fiyat;
    }

      public TarifePojo getPojo() {
            return pojo;
        }

        public void setPojo(TarifePojo pojo) {
            this.pojo = pojo;
        }

        public String getTarife_ismi() {
            return tarife_ismi;
        }

        public void setTarife_ismi(String tarife_ismi) {
            this.tarife_ismi = tarife_ismi;
        }

    public boolean isKonusma() {
        return konusma;
    }

    public void setKonusma(boolean konusma) {
        this.konusma = konusma;
    }

    public boolean isMesaj() {
        return mesaj;
    }

    public void setMesaj(boolean mesaj) {
        this.mesaj = mesaj;
    }

    public boolean isKonusma_mesaj() {
        return konusma_mesaj;
    }

    public String getMesaj_paket_ismi() {
        return mesaj_paket_ismi;
    }

    public void setMesaj_paket_ismi(String mesaj_paket_ismi) {
        this.mesaj_paket_ismi = mesaj_paket_ismi;
    }
    
    

    public void setKonusma_mesaj(boolean konusma_mesaj) {
        this.konusma_mesaj = konusma_mesaj;
    }
        
        
        @Override
        public int compareTo(tarife_oner_compare tp) {
                      
            if(this.getToplam_fiyat()>tp.getToplam_fiyat()){
                return 1;
            } else if(this.getToplam_fiyat()<tp.getToplam_fiyat()){
                return -1;
            } else {
                return 0;
            }
            
        }
    }