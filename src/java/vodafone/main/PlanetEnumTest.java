/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.main;

import java.util.HashMap;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import vodafone.pojolar.Operator1;
import vodafone.pojolar.OperatorDataSource;

/**
 *
 * @author LifeBook
 */
public class PlanetEnumTest {

    public static void main(String[] args) {
        OperatorDataSource source = new OperatorDataSource();
        
        for ( int i = 1 ; i<6 ; i++){
            
            Operator1 op = new Operator1();
            op.setOperator("op"+i);
            op.setMesaj_sayisi(i*10);
            op.setNumara_sayisi(i*5);
            op.setToplam_periyod_sure(new Double(i*4));
            op.setToplam_sure(new Double(i*3));
            
            source.add(op);
        }
        export("D:\\test.pdf", source);
        
 }
        
         private static void export(String outFileName , OperatorDataSource datasource) {
                 JasperReport jasperReport = null ;
		 String source_filename  = "C:\\VodafoneRaporlar\\SonucRapor.jasper";
	
                
                HashMap hm = new HashMap();
                hm.put("faturaTarih", "12-02-2012 - 13-03-2012");
                hm.put("DataFile", "OperatorDataSource.java - Bean Array");
                
                try {
                        JasperPrint print =    JasperFillManager.fillReport(source_filename, hm, new JRBeanArrayDataSource(datasource.getBeanArray()));

                        if(datasource!=null) 
                        { JasperExportManager.exportReportToPdfFile(print, outFileName);  
                          System.err.println("");
                        }

                 } catch (Exception e) {
                            e.printStackTrace();
                            System.err.println("Vodafone Hata"+e);
                 } finally {

                }
            
        }
        

    
}