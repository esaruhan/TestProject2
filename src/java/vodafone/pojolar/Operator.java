/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.pojolar;


public	class  Operator {
	
	
	public String operator = "";
	
	
	public Double mesaj_ucret = 0.0 ;
	public Double arama_ucret = 0.0 ;
	public Double toplam_sure = 0.0 ;
	public Double toplam_periyod_sure = 0.0;
        
	public int arama_sayisi = 0;
	public int mesaj_sayisi = 0 ;
	public int numara_sayisi = 0;

    public Double getToplam_periyod_sure() {
        return toplam_periyod_sure;
    }

    public void setToplam_periyod_sure(Double toplam_periyod_sure) {
        this.toplam_periyod_sure = toplam_periyod_sure;
    }

	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Double getMesaj_ucret() {
		return mesaj_ucret;
	}

	public void setMesaj_ucret(Double mesajUcret) {
		mesaj_ucret = mesajUcret;
	}

	public Double getArama_ucret() {
		return arama_ucret;
	}

	public void setArama_ucret(Double aramaUcret) {
		arama_ucret = aramaUcret;
	}

	public Double getToplam_sure() {
		return toplam_sure;
	}

	public void setToplam_sure(Double toplamSure) {
		toplam_sure = toplamSure;
	}

	public int getArama_sayisi() {
		return arama_sayisi;
	}

	public void setArama_sayisi(int aramaSayisi) {
		arama_sayisi = aramaSayisi;
	}

	public int getMesaj_sayisi() {
		return mesaj_sayisi;
	}

	public void setMesaj_sayisi(int mesajSayisi) {
		mesaj_sayisi = mesajSayisi;
	}

	public int getNumara_sayisi() {
		return numara_sayisi;
	}

	public void setNumara_sayisi(int numaraSayisi) {
		numara_sayisi = numaraSayisi;
	}
}