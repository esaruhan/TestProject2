/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.pojolar;

import java.util.Date;

/**
 *
 * @author LifeBook
 */
public class TelefonPojo {
    
    private String numara   = "" ;
    private String operator = "" ;
    private String type     = "" ;
    private String tarih    = "" ;
    private Double sure     = 0.0;
    private Double tutar    = 0.0;
    private Date   date     ;

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public Double getSure() {
        return sure;
    }

    public void setSure(Double sure) {
        this.sure = sure;
    }

    public Double getTutar() {
        return tutar;
    }

    public void setTutar(Double tutar) {
        this.tutar = tutar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
    
}
