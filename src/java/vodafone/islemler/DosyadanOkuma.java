/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author LifeBook
 */
public class DosyadanOkuma {

    private  String aboneNumara = "";
    private  ArrayList<ArrayList<String>> gorusmeler = new ArrayList<ArrayList<String>>();    
    /* 
     * Excel dosyası aslında içerisinde bir HTML syntaxı barındırdığı için ,
     * jsoup-1.5.2.jar apisi ile bu HTML taglarını parse edebiliriz.
     * 
     */   
    
     public  ArrayList<ArrayList<String>> dosyaOkuFromString(String content) { 
                 
        try {                                
            Document 	doc     = Jsoup.parse(content,"iso-8859-9");      
            Elements    trler   = doc.select("table").select("tr");

            for (int i = 0; i < trler.size(); i++) {
                Element tr = trler.get(i);
                Elements tdler = tr.select("td");

                ArrayList<String> data = new ArrayList<String>();
//                System.out.println("---------------------------");
                for (int y = 0; y < tdler.size(); y++) {

                    Element td = tdler.get(y);

                    data.add(td.text());
//                    System.out.println("text:"+td.text());
                    if (i == 0 && y == 1) {
                        aboneNumara = td.text().toString();
                    }

                }
//                System.out.println("----------FİNİSH-----------");
                gorusmeler.add(data);
            }

        } catch (Exception ex) {    
            System.err.println("DosyadanOkuma:"+ex.toString());
        }

        return gorusmeler;
         
     }
    public  ArrayList<ArrayList<String>> dosyaOkuFromFile(String fileName) {
        try {        
        
            
            File        file    = new File(fileName);                             
            Document 	doc     = Jsoup.parse(file,"iso-8859-9");      
            Elements    trler   = doc.select("table").select("tr");

            for (int i = 0; i < trler.size(); i++) {
                Element tr = trler.get(i);
                Elements tdler = tr.select("td");

                ArrayList<String> data = new ArrayList<String>();
//                System.out.println("---------------------------");
                for (int y = 0; y < tdler.size(); y++) {

                    Element td = tdler.get(y);

                    data.add(td.text());
//                    System.out.println("text:"+td.text());
                    if (i == 0 && y == 1) {
                        aboneNumara = td.text().toString();
                    }

                }
//                System.out.println("----------FİNİSH-----------");
                gorusmeler.add(data);
            }   
        return gorusmeler;
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return null;
        }
       
    }

    public  String getAboneNumara() {
        return aboneNumara;
    }

    public  void setAboneNumara(String aboneNumara) {
       aboneNumara = aboneNumara;
    }

    public  ArrayList<ArrayList<String>> getGorusmeler() {
        return gorusmeler;
    }

    public  void setGorusmeler(ArrayList<ArrayList<String>> gorusmeler) {
        gorusmeler = gorusmeler;
    }
    
}
