/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.awt.Image;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
/**
 *
 * @author LifeBook
 */
public class ExportPDF {
         
         Connection connection = null;
         
         String tarih_araligi = "";
         String abone_numara  ="";
         
         Image sure_bazli_img;
	 Image numara_sayisina_gore;
	 Image arama_ucret;
	 Image mesaj_ucret;
	 Image mesaj_sayisi;
	
	 int toplam_numara = 0 ;
         
        public void setTarihAraligi(String tarih1, String tarih2){
		tarih_araligi = tarih1.split(" ")[0]+" - "+tarih2.split(" ")[0];
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
        public void setConnection(Connection con ){
            connection = con ;
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
	        
	        try {
                           JasperPrint print =    JasperFillManager.fillReport(source_filename, hm, connection);
                                           
//	        JasperPrint print  =		JasperFillManager.fillReport(jasperReport, hm,connection );
			

                if(connection!=null) 
	        	 	JasperExportManager.exportReportToPdfFile(print, outFileName);
	        	
                 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
                    connection.close();
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
}
