/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.awt.Image;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import vodafone.pojolar.VodafoneDataSource;
/**
 *
 * @author LifeBook
 */
public class ExportPDF {
         
         
         Double toplam_periyod_sure = 0.0;
         Double vodafone_periyod_sure = 0.0;
         Double heryone_periyod_sure = 0.0;
         Double digeryone_periyod_sure = 0.0;
         
         String tarih_araligi = "";
         String abone_numara  ="";
         VodafoneDataSource  data_source = new VodafoneDataSource();
         Image sure_bazli_img;
	 Image numara_sayisina_gore;
	 Image arama_ucret;
	 Image mesaj_ucret;
	 Image mesaj_sayisi;
	
	 int toplam_numara = 0 ;
         
        public void setTarihAraligi(String tarih1, String tarih2){
		tarih_araligi = tarih2.split(" ")[0]+" - "+tarih1.split(" ")[0];
	}
        public String getTarihAraligi(){
            return tarih_araligi;
        }
        public void setAboneNumara(String no){
            
            this.abone_numara = no ;
        }
        public void setToplamKisiNumber(int no){
            toplam_numara = no ;
        }

    public Double getToplam_periyod_sure() {
        return toplam_periyod_sure;
    }

    public void setToplam_periyod_sure(Double toplam_periyod_sure) {
        this.toplam_periyod_sure = toplam_periyod_sure;
    }
      
        
        public void setSubReportParamaters( Image sure_bazli_img, Image numara_sayisina_gore, Image arama_ucret,	 Image mesaj_ucret,	 Image mesaj_sayisi){
		
		  this.sure_bazli_img       = sure_bazli_img;
		  this.numara_sayisina_gore = numara_sayisina_gore;
		  this.arama_ucret          = arama_ucret;
		  this.mesaj_ucret          = mesaj_ucret;
		  this.mesaj_sayisi         = mesaj_sayisi;
	} 
        
        public void exportSecondReport(String outFileName) throws SQLException{
                 JasperReport jasperReport = null ;
		 String source_filename  = "C:\\VodafoneRaporlar\\MySqlReport3.jasper";
	
		
		java.net.URL leaf = this.getClass().getResource("resource/leaf_banner_green.png");
                ImageIcon  icon = new ImageIcon(leaf);
		Image img = icon.getImage();
		
		ImageIcon arama_icon = new ImageIcon(   this.getClass().getResource("resource/telefon.png"));
		ImageIcon mesaj_icon = new ImageIcon(   this.getClass().getResource("resource/mesaj.png"));
		
		
		Image arama_img = arama_icon.getImage();
	
		Image mesaj_img = mesaj_icon.getImage();
		
	        
	        HashMap hm = new HashMap();
	        hm.put("faturaTarih", tarih_araligi);
	        hm.put("leaf", img);
	        hm.put("total_number", toplam_numara);
	        hm.put("arama_icon", arama_img);
	        hm.put("mesaj_icon", mesaj_img);
	        hm.put("DataFile", "VodafoneDataSource.java - Bean Array");
                hm.put("heryone_dakika", heryone_periyod_sure/60);
                hm.put("vodafone_dakika",vodafone_periyod_sure/60);
                hm.put("digeryone_dakika", digeryone_periyod_sure/60);
                
                try {
                    JasperPrint print =    JasperFillManager.fillReport(source_filename, hm, new JRBeanArrayDataSource(data_source.getBeanArray()));

                    if(data_source!=null) 
                    { JasperExportManager.exportReportToPdfFile(print, outFileName);  
                      System.err.println("");
                    }

                 } catch (Exception e) {
                     e.printStackTrace();
                      System.err.println("Vodafone Hata"+e);
                 } finally {

                }
		
		
        }
        
        public void exportFirstReport(String outFileName) throws Exception {
                String filename      = "C:\\VodafoneRaporlar\\FirstPage.jasper";
                java.net.URL analiz_rapor = this.getClass().getResource("resource/analiz-rapor-4.jpg");
                ImageIcon  icon = new ImageIcon(analiz_rapor);
		Image analiz_raporImg = icon.getImage();
                
                 java.net.URL analiz_resim = this.getClass().getResource("resource/analiz (1).jpg");
                ImageIcon  analiz_resimicon = new ImageIcon(analiz_resim);
		Image analiz_resimImg = analiz_resimicon.getImage();

		
		       
		
		HashMap hm = new HashMap();
		hm.put("Ad", "");
		hm.put("TelefonNo", abone_numara);
		hm.put("faturaTarih", tarih_araligi);
//		hm.put("chart", img);
		hm.put("arama_sure", sure_bazli_img);
		hm.put("numara_sayisi", numara_sayisina_gore);
		hm.put("arama_ucret", arama_ucret);
		hm.put("mesaj_ucret", mesaj_ucret);
		hm.put("mesaj_sayisi", mesaj_sayisi);
		hm.put("analiz_rapor",analiz_raporImg);
                hm.put("analiz_resim", analiz_resimImg);

                
                JasperPrint jasperPrint =    JasperFillManager.fillReport(filename, hm, new  JREmptyDataSource());
//		JasperPrint jasperPrint =  JasperFillManager.fillReport(jasperReport, hm,new  JREmptyDataSource() );
		JasperExportManager.exportReportToPdfFile(jasperPrint, outFileName);
        }

    public VodafoneDataSource getData_source() {
        return data_source;
    }

    public void setData_source(VodafoneDataSource data_source) {
        this.data_source = data_source;
    }

    public Double getVodafone_periyod_sure() {
        return vodafone_periyod_sure;
    }

    public void setVodafone_periyod_sure(Double vodafone_periyod_sure) {
        this.vodafone_periyod_sure = vodafone_periyod_sure;
    }

    public Double getHeryone_periyod_sure() {
        return heryone_periyod_sure;
    }

    public void setHeryone_periyod_sure(Double heryone_periyod_sure) {
        this.heryone_periyod_sure = heryone_periyod_sure;
    }

    public Double getDigeryone_periyod_sure() {
        return digeryone_periyod_sure;
    }

    public void setDigeryone_periyod_sure(Double digeryone_periyod_sure) {
        this.digeryone_periyod_sure = digeryone_periyod_sure;
    }
        
        
        
        
}
