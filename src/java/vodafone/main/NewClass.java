/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vodafone.islemler.DatabaseHazirla;
import vodafone.islemler.DatabaseInsert;
import vodafone.islemler.DosyadanOkuma;
import vodafone.islemler.ExportPDF;
import vodafone.islemler.GorusmeleriDuzenle;
import vodafone.islemler.MergePDF;
import vodafone.islemler.PieChartPrepare;
import vodafone.pojolar.OperatoreGorePojo;

/**
 *
 * @author LifeBook
 */
public class NewClass {

    private ArrayList<ArrayList<String>> gorusmeler;
    private String outputPath = "";
    private Connection con = null;
    
    public NewClass(String fileName, String contextPath) {
        try {
            //          String fileName = "C:\\Users\\Ertugrul\\Downloads\\faturagorusmedetayi.xls";
            //Önce fatura detay dosyasını okuyoruz..
            DosyadanOkuma dokuma = new DosyadanOkuma();

            gorusmeler = dokuma.dosyaOkuFromFile(fileName);

            if (gorusmeler != null && !gorusmeler.isEmpty() & gorusmeler.size() > 5) {
                
                String aboneNumara = dokuma.getAboneNumara();
                //Daha sonra bu gorusmeleri düzenliyoruz.
                GorusmeleriDuzenle duzenle = new GorusmeleriDuzenle();
                OperatoreGorePojo opgore = duzenle.duzenle(gorusmeler);

                //Raporda göstermek için hazır hale getiriyoruz.
                DatabaseHazirla db = new DatabaseHazirla(opgore);

                //Bir database memory insert işlemleri
                DatabaseInsert dinsert = new DatabaseInsert(opgore.getDatabasepojo());

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

                //Raporları Çıkartabilirsinizin
                ExportPDF export = new ExportPDF();
                export.setTarihAraligi(gorusmeler.get(3).get(0), gorusmeler.get(gorusmeler.size() - 1).get(0));
                String tarih_araligi = export.getTarihAraligi();


                String firstReportOut = contextPath + "VodafoneRaporlar\\" + aboneNumara + "##" + tarih_araligi.replaceAll("/", "-").replaceAll(" ", "##") + "##FirstPage.pdf";
                String secondReportOut = contextPath + "VodafoneRaporlar\\" + aboneNumara + "##" + tarih_araligi.replaceAll("/", "-").replaceAll(" ", "##") + "##SecondPage.pdf";
                outputPath = contextPath + "VodafoneRaporlar\\" + aboneNumara + "##" + tarih_araligi.replaceAll("/", "-").replaceAll(" ", "##") + ".pdf";

                export.setAboneNumara(aboneNumara);
                export.setConnection(con);
                export.setToplamKisiNumber(toplam_kisi_no);
                export.setSubReportParamaters(prepare.getSure_bazli_img(), prepare.getNumara_sayisina_gore(), prepare.getArama_ucret(), prepare.getMesaj_ucret(), prepare.getMesaj_sayisi());

                try {
                    export.exportFirstReport(firstReportOut);
                    export.exportSecondReport(secondReportOut);
                    List<InputStream> pdfs = new ArrayList<InputStream>();
                    pdfs.add(new FileInputStream(firstReportOut));
                    pdfs.add(new FileInputStream(secondReportOut));
                    OutputStream output = new FileOutputStream(outputPath);
                    MergePDF.concatPDFs(pdfs, output, true);


                } catch (Exception ex) {
                    Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            
            
        } catch (Exception ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String outputPath() {
        return outputPath;
    }

    public static void main(String a[]) {

        new NewClass("", "");

    }
}
