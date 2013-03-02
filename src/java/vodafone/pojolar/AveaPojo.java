package vodafone.pojolar;


public class AveaPojo {

	private String numara           = "";
	private String tarih            = "";
	private String saat             = "";
	private String type             = "";
	private Double sure             = 0.0;
	private Double gonderilenByte   = 0.0;
	private Double indirilenByte    = 0.0;
	private Double toplamByte       = 0.0;
	private Double tutar            = 0.0;

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

	public Double getTutar() {
		return this.tutar;
	}

	/**
	 * 
	 * @param tutar
	 */
	public void setTutar(Double tutar) {
		this.tutar = tutar;
	}

	public String getTarih() {
		return this.tarih;
	}

	/**
	 * 
	 * @param tarih
	 */
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getSaat() {
		return this.saat;
	}

	/**
	 * 
	 * @param saat
	 */
	public void setSaat(String saat) {
		this.saat = saat;
	}

	public String getType() {
		return this.type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	public Double getSure() {
		return this.sure;
	}

	/**
	 * 
	 * @param sure
	 */
	public void setSure(Double sure) {
		this.sure = sure;
	}

	public Double getGonderilenByte() {
		return this.gonderilenByte;
	}

	/**
	 * 
	 * @param gonderilenByte
	 */
	public void setGonderilenByte(Double gonderilenByte) {
		this.gonderilenByte = gonderilenByte;
	}

	public Double getIndirilenByte() {
		return this.indirilenByte;
	}

	/**
	 * 
	 * @param indirilenByte
	 */
	public void setIndirilenByte(Double indirilenByte) {
		this.indirilenByte = indirilenByte;
	}

	public Double getToplamByte() {
		return this.toplamByte;
	}

	/**
	 * 
	 * @param toplamByte
	 */
	public void setToplamByte(Double toplamByte) {
		this.toplamByte = toplamByte;
	}

}