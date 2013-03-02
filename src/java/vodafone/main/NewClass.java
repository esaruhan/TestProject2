/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vodafone.islemler.DatabaseHazirla;
import vodafone.islemler.DatabaseInsert;
import vodafone.islemler.DosyadanOkuma;
import vodafone.islemler.ExportPDF;
import vodafone.islemler.GorusmeleriDuzenle;
import vodafone.islemler.PieChartPrepare;
import vodafone.pojolar.OperatoreGorePojo;

/**
 *
 * @author LifeBook
 */
public class NewClass {
    
    
    
    public NewClass (){
        String fileName = "C:\\Users\\LifeBook\\Downloads\\faturagorusmedetayi.xls";

            //Önce fatura detay dosyasını okuyoruz..
            DosyadanOkuma dokuma = new DosyadanOkuma();
            ArrayList<ArrayList<String>> gorusmeler = dokuma.dosyaOku(fileName);
            String aboneNumara = dokuma.getAboneNumara();
            
            //Daha sonra bu gorusmeleri düzenliyoruz.
            GorusmeleriDuzenle  duzenle = new GorusmeleriDuzenle();
            OperatoreGorePojo opgore  =  duzenle.duzenle(gorusmeler);
            
            
            
            //Raporda göstermek için hazır hale getiriyoruz.
            DatabaseHazirla db = new DatabaseHazirla(opgore);
            
            //Bir database memory insert işlemleri
            DatabaseInsert  dinsert = new DatabaseInsert(opgore.getDatabasepojo());
            
            dinsert.kaydetDatabase();
            Connection con = dinsert.getConnection();
           
           //PieChartları Hazırla
           PieChartPrepare prepare = new PieChartPrepare(opgore);
            try {
                prepare.hesapla();
                prepare.createPirCharsAll();
            } catch (Exception ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            int toplam_kisi_no = prepare.getToplamNumara();          

            
            String firstReportOut    = "C:\\VodafoneRaporlar\\FirstPage.pdf";
            String secondReportOut  = "C:\\VodafoneRaporlar\\SecondPage.pdf";
           
            //Raporları Çıkartabilirsiniz
            ExportPDF  export = new ExportPDF();
            export.setTarihAraligi(gorusmeler.get(3).get(0), gorusmeler.get(gorusmeler.size()-1).get(0));
	    export.setAboneNumara(aboneNumara);
            export.setConnection(con);
            export.setToplamKisiNumber(toplam_kisi_no);
            export.setSubReportParamaters(prepare.getSure_bazli_img(),prepare.getNumara_sayisina_gore(), prepare.getArama_ucret(), prepare.getMesaj_ucret() , prepare.getMesaj_sayisi());
        
            try {
                export.exportFirstReport(firstReportOut);
                export.exportSecondReport(secondReportOut);
            } catch (Exception ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    
    
    
    public static void main (String a[]){
        
        new NewClass();
         
    }
    
}
