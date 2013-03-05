/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.pojolar;

/**
 *
 * @author LifeBook
 */
public class TarifePojo {
    
    private String   tarife_ismi     =  "";
    private String   tarife_grubu    = "";
    private Integer  her_yone_dakika = 0;
    private Integer  vodafone_dakika = 0 ;
    private Integer  sabithat_dakika = 0 ;
    private Integer  her_yone_sms    = 0 ;
    private Integer  vodafone_sms    = 0;
    
    private boolean  isInternetPaket = false;
    private String   internet_kota   = "0KB";
    private boolean  isKontratVar    = false;
    private boolean  kalmaSozu       = false;
    private Integer  kontrat_suresi  = 0;
    private Integer  fiyat           = 0 ;

    public String getTarife_ismi() {
        return tarife_ismi;
    }

    public void setTarife_ismi(String tarife_ismi) {
        this.tarife_ismi = tarife_ismi;
    }

    public String getTarife_grubu() {
        return tarife_grubu;
    }

    public void setTarife_grubu(String tarife_grubu) {
        this.tarife_grubu = tarife_grubu;
    }

    public Integer getHer_yone_dakika() {
        return her_yone_dakika;
    }

    public void setHer_yone_dakika(Integer her_yone_dakika) {
        this.her_yone_dakika = her_yone_dakika;
    }

    public Integer getVodafone_dakika() {
        return vodafone_dakika;
    }

    public void setVodafone_dakika(Integer vodafone_dakika) {
        this.vodafone_dakika = vodafone_dakika;
    }

    public Integer getSabithat_dakika() {
        return sabithat_dakika;
    }

    public void setSabithat_dakika(Integer sabithat_dakika) {
        this.sabithat_dakika = sabithat_dakika;
    }

    public Integer getHer_yone_sms() {
        return her_yone_sms;
    }

    public void setHer_yone_sms(Integer her_yone_sms) {
        this.her_yone_sms = her_yone_sms;
    }

    public Integer getVodafone_sms() {
        return vodafone_sms;
    }

    public void setVodafone_sms(Integer vodafone_sms) {
        this.vodafone_sms = vodafone_sms;
    }

    public boolean isIsInternetPaket() {
        return isInternetPaket;
    }

    public void setIsInternetPaket(boolean isInternetPaket) {
        this.isInternetPaket = isInternetPaket;
    }

    public String getInternet_kota() {
        return internet_kota;
    }

    public void setInternet_kota(String internet_kota) {
        this.internet_kota = internet_kota;
    }

    public boolean isIsKontratVar() {
        return isKontratVar;
    }

    public void setIsKontratVar(boolean isKontratVar) {
        this.isKontratVar = isKontratVar;
    }

    public boolean isKalmaSozu() {
        return kalmaSozu;
    }

    public void setKalmaSozu(boolean kalmaSozu) {
        this.kalmaSozu = kalmaSozu;
    }

    public Integer getKontrat_suresi() {
        return kontrat_suresi;
    }

    public void setKontrat_suresi(Integer kontrat_suresi) {
        this.kontrat_suresi = kontrat_suresi;
    }

    public Integer getFiyat() {
        return fiyat;
    }

    public void setFiyat(Integer fiyat) {
        this.fiyat = fiyat;
    }
    
    
    
    
    
}
