/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.pojolar;

/**
 *
 * @author LifeBook
 */
public class DatabasePojo {
        
        private String aranan_ismi          = "";
    	private String numara               = "";
	private String operator             = "";
	private int mesajSayisi             = 0;
	private int aramaSayisi             = 0;
	private Double toplamMesajUcret     = 0.0;
	private Double toplamAramaUcret     = 0.0;
	private Double toplamAramaDakika    = 0.0;
	private Double ortalamaGorusme      = 0.0;
	private Double toplamMiktar         = 0.0;
	private Double toplamPeriyodSure    = 0.0;

	public String getNumara() {
		return this.numara;
	}

	/**
	 * 
	 * @param numara
	 */
	public void setNumara(String numara) {
		this.numara = numara;
	}

	public String getOperator() {
		return this.operator;
	}

	/**
	 * 
	 * @param operator
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

        public Double getToplamPeriyodSure() {
            return toplamPeriyodSure;
        }

        public void setToplamPeriyodSure(Double toplamPeriyodSure) {
            this.toplamPeriyodSure = toplamPeriyodSure;
        }
            public void incrementToplamPeriyodSure(double saniye){
		
		toplamPeriyodSure = toplamPeriyodSure + saniye;
		
	}
        
	public int getMesajSayisi() {
		return this.mesajSayisi;
	}

	/**
	 * 
	 * @param mesajSayisi
	 */
	public void setMesajSayisi(int mesajSayisi) {
		this.mesajSayisi = mesajSayisi;
	}

	public int getAramaSayisi() {
		return this.aramaSayisi;
	}

	/**
	 * 
	 * @param aramaSayisi
	 */
	public void setAramaSayisi(int aramaSayisi) {
		this.aramaSayisi = aramaSayisi;
	}

	public Double getToplamMesajUcret() {
		return this.toplamMesajUcret;
	}

	/**
	 * 
	 * @param toplamMesajUcret
	 */
	public void setToplamMesajUcret(Double toplamMesajUcret) {
		this.toplamMesajUcret = toplamMesajUcret;
	}

	public Double getToplamAramaUcret() {
		return this.toplamAramaUcret;
	}

	/**
	 * 
	 * @param toplamAramaUcret
	 */
	public void setToplamAramaUcret(Double toplamAramaUcret) {
		this.toplamAramaUcret = toplamAramaUcret;
	}

	public Double getToplamAramaDakika() {
		return this.toplamAramaDakika;
	}

	/**
	 * 
	 * @param toplamAramaDakika
	 */
	public void setToplamAramaDakika(Double toplamAramaDakika) {
		this.toplamAramaDakika = toplamAramaDakika;
	}

	public Double getOrtalamaGorusme() {
		return this.ortalamaGorusme;
	}

	/**
	 * 
	 * @param ortalamaGorusme
	 */
	public void setOrtalamaGorusme(Double ortalamaGorusme) {
		this.ortalamaGorusme = ortalamaGorusme;
	}

	public Double getToplamMiktar() {
		return this.toplamMiktar;
	}

	/**
	 * 
	 * @param toplamMiktar
	 */
	public void setToplamMiktar(Double toplamMiktar) {
		this.toplamMiktar = toplamMiktar;
	}

        
        public void incrementAramaSayisi(){
		aramaSayisi ++;
	}
	public void incrementMesajSayisi(){
		mesajSayisi ++;
	}
	public void incrementToplamMesajUcret(double ucret){
		
		toplamMesajUcret = toplamMesajUcret + ucret;
	}
	public void incrementToplamAramaUcret(double ucret){
		
		toplamAramaUcret = toplamAramaUcret + ucret;
	}
	public void incrementToplamDakika(double dakika){
		
		toplamAramaDakika = toplamAramaDakika + dakika;
		
	}
	public void incrementToplamMiktar(double miktar){
		
		toplamMiktar = toplamMiktar + miktar;
		
	}
        
        public void setOrtalamaGorusme() {
		
		if(aramaSayisi !=0 && toplamAramaDakika !=0 ) {
                    this.ortalamaGorusme = toplamAramaDakika / aramaSayisi;
                }	
		else {
                    this.ortalamaGorusme = 0.0;
                }
		
	}

    public String getAranan_ismi() {
        return aranan_ismi;
    }

    public void setAranan_ismi(String aranan_ismi) {
        this.aranan_ismi = aranan_ismi;
    }
	
    
        
}
