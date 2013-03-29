/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.pojolar;

/**
 *
 * @author LifeBook
 */
public class SMS {
    
    private String paket_adi = "";
    private Integer fiyat   = 0 ;
    private Integer heryone = 0 ;
    private Integer vodafone = 0 ;

    public SMS(String paket_adi, Integer fiyat, Integer heryone , Integer vodafone) {
            
           this.paket_adi = paket_adi;
           this.fiyat = fiyat ;
           this.heryone = heryone;
           this.vodafone = vodafone;
                   
    }

    
    
    public String getPaket_adi() {
        return paket_adi;
    }

    public void setPaket_adi(String paket_adi) {
        this.paket_adi = paket_adi;
    }

    public Integer getFiyat() {
        return fiyat;
    }

    public void setFiyat(Integer fiyat) {
        this.fiyat = fiyat;
    }

    public Integer getHeryone() {
        return heryone;
    }

    public void setHeryone(Integer heryone) {
        this.heryone = heryone;
    }

    public Integer getVodafone() {
        return vodafone;
    }

    public void setVodafone(Integer vodafone) {
        this.vodafone = vodafone;
    }
    
    
    
}
