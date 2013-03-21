/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.tarife_oner_islemler;

import java.text.DecimalFormat;
import vodafone.pojolar.TarifePojo;

   public class tarife_oner_compare implements Comparable<tarife_oner_compare> {
        
       public int fiyat = 0;
       public  double puan = 0.0;
       public TarifePojo pojo ;
       public String tarife_ismi = "";

        public tarife_oner_compare(int tfiyat, Double tpuan , TarifePojo tpojo,String ttarife_ismi) {
            fiyat = tfiyat;
            puan = tpuan ;
            pojo = tpojo ;
            tarife_ismi = ttarife_ismi;
                     
        }

        public int getFiyat() {
            return fiyat;
        }

        public void setFiyat(int fiyat) {
            this.fiyat = fiyat;
        }

        public Double getPuan() {
            return puan;
        }

        public void setPuan(Double puan) {
            this.puan = puan;
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
        
        
        @Override
        public int compareTo(tarife_oner_compare tp) {
                      
            if((this.puan>tp.getPuan() & this.fiyat<tp.getFiyat())|| (this.puan == tp.getPuan() & this.fiyat<tp.getFiyat())){
                return 1;
            } else if((this.puan < tp.getPuan() & this.fiyat>tp.getFiyat())||(this.puan == tp.getPuan() & this.fiyat>tp.getFiyat())||(this.puan>tp.getPuan() & this.fiyat<tp.getFiyat())){
                return -1;
            } else {
                return 0;
            }
            
        }
    }