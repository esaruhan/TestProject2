/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.pojolar;

import java.util.HashMap;

/**
 *
 * @author LifeBook
 */
public class TarifeOnerPojo {
    
        int    toplam_gorusme = 0 ;    
	int    aramaSayisi = 0;
	int    mesajSayisi = 0 ;
	int    toplamNumara = 0;
	Double toplamSure = 0.0 ;
	Double toplamMesajUcret = 0.0 ;
	Double toplamAramaUcret = 0.0 ;
        Double toplamPeriyodSure = 0.0;
        
            
        private HashMap<String, Operator> operatorler = new HashMap<String,Operator>();

    public int getToplam_gorusme() {
        return toplam_gorusme;
    }

    public void setToplam_gorusme(int toplam_gorusme) {
        this.toplam_gorusme = toplam_gorusme;
    }

    public int getAramaSayisi() {
        return aramaSayisi;
    }

    public void setAramaSayisi(int aramaSayisi) {
        this.aramaSayisi = aramaSayisi;
    }

    public int getMesajSayisi() {
        return mesajSayisi;
    }

    public void setMesajSayisi(int mesajSayisi) {
        this.mesajSayisi = mesajSayisi;
    }

    public int getToplamNumara() {
        return toplamNumara;
    }

    public void setToplamNumara(int toplamNumara) {
        this.toplamNumara = toplamNumara;
    }

    public Double getToplamSure() {
        return toplamSure;
    }

    public void setToplamSure(Double toplamSure) {
        this.toplamSure = toplamSure;
    }

    public Double getToplamMesajUcret() {
        return toplamMesajUcret;
    }

    public void setToplamMesajUcret(Double toplamMesajUcret) {
        this.toplamMesajUcret = toplamMesajUcret;
    }

    public Double getToplamAramaUcret() {
        return toplamAramaUcret;
    }

    public void setToplamAramaUcret(Double toplamAramaUcret) {
        this.toplamAramaUcret = toplamAramaUcret;
    }

    public Double getToplamPeriyodSure() {
        return toplamPeriyodSure;
    }

    public void setToplamPeriyodSure(Double toplamPeriyodSure) {
        this.toplamPeriyodSure = toplamPeriyodSure;
    }

    public HashMap<String, Operator> getOperatorler() {
        return operatorler;
    }

    public void setOperatorler(HashMap<String, Operator> operatorler) {
        this.operatorler = operatorler;
    }
        
        
        
        
}
