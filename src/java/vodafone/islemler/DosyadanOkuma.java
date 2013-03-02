/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
            ex.printStackTrace();
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

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return gorusmeler;
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
