/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Image;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import vodafone.pojolar.Operator;
import vodafone.pojolar.Operator1;
import vodafone.pojolar.OperatoreGorePojo;
import vodafone.pojolar.TarifeOnerPojo;
import vodafone.pojolar.TelefonPojo;

public class PieChartPrepare {

	/*  1 - Arama sï¿½resine Gï¿½re Yï¿½zdelik - Operatore gore arama sï¿½resini baz alarak
	 *  2 - Aranan operatore Gï¿½re Yï¿½zdelik - Operatï¿½re gï¿½re aranan kiï¿½iyi baz alarak
	 *  3 - Aranan ï¿½crete Gï¿½re Yï¿½zdelik  - Operatore gore arama ï¿½cretini baz alarak
	 *  4 - Atï¿½lan Mesaja Gï¿½re Yï¿½zdelik  - Operatatore gï¿½re mesaj sayï¿½sï¿½nï¿½ baz alarak
	 *  5 - Atï¿½lan mesaj ï¿½cretine gï¿½re Yï¿½zdelik  - Operatore gore mesaj ï¿½cretini baz alarak
	 */
   
	private OperatoreGorePojo opgore = null ;
	private TarifeOnerPojo toner = new TarifeOnerPojo();
        private HashMap<String, Operator> operatorler = new HashMap<String,Operator>();
        private HashMap<String, Operator1> operatorler1 = new HashMap<String,Operator1>();
       
	// Toplam ları bulmak için , PİE Chartta toplamları bilirsek
        // oranlama imkanımız olacak.
        int toplam_gorusme = 0 ;
        
	int aramaSayisi = 0;
	int mesajSayisi = 0 ;
	int toplamNumara = 0;
	Double toplamSure = 0.0 ;
	Double toplamMesajUcret = 0.0 ;
	Double toplamAramaUcret = 0.0 ;
        Double toplamPeriyodSure = 0.0;
        
	Operator turkcel    = null ;
	Operator vodafone   = null ;
	Operator avea       = null ;
	Operator sabithat   = null ;
	Operator diger      = null ;
	Operator internet   = null;
        
        public  Image sure_bazli_img;
        public  Image numara_sayisina_gore;
        public  Image arama_ucret;
        public  Image mesaj_ucret;
        public  Image mesaj_sayisi;


	public PieChartPrepare(OperatoreGorePojo opgore ) {
		
		this.opgore = opgore ;
                
	}
	
	public void hesapla(){
		
		 turkcel     = degerHesapla(opgore.getTurkcell(), "TURKCELL");
		 vodafone    = degerHesapla(opgore.getVodafone(), "VODAFONE");
		 avea        = degerHesapla(opgore.getAvea(), "AVEA");
		 sabithat    = degerHesapla(opgore.getSabithat(), "SABIT");
		 diger 	     = degerHesapla(opgore.getDiger(),"Diğer/Yurtdışı");
		
                
                 calculateTotals(turkcel,"Turkcell");
                 calculateTotals(vodafone,"Vodafone");
                 calculateTotals(avea,"Avea");
                 calculateTotals(sabithat,"SabitHat");
                 calculateTotals(diger,"Diğer Operatorler");         
                
                 opgore.setOperatorler_rapor(new ArrayList<Operator1>(operatorler1.values()));
                 
                 
                 toner.setAramaSayisi(aramaSayisi);
                 toner.setMesajSayisi(mesajSayisi);
                 toner.setOperatorler(operatorler);
                 toner.setToplamAramaUcret(toplamAramaUcret);
                 toner.setToplamMesajUcret(toplamMesajUcret);
                 toner.setToplamNumara(toplamNumara);
                 toner.setToplamPeriyodSure(toplamPeriyodSure);
                 toner.setToplamSure(toplamSure);
                 toner.setToplam_gorusme(toplam_gorusme);
                 
	}
	public void createPirCharsAll() throws Exception{
		
		 sure_bazli_img 			= createPieChart_AramaSuresineGore();
		 numara_sayisina_gore                   = createPieChart_ArananSayiyaGore();
		 arama_ucret  				= createPieChart_Arama_UcreteGore();
		 mesaj_ucret  				= createPieChart_Mesaj_UcreteGore();
		 mesaj_sayisi				= createPieChart_Mesaj_SayisinaGore();
   	       //exportPDF(sure_bazli_img, numara_sayisina_gore, arama_ucret, mesaj_ucret ,mesaj_sayisi);		
	}

	public Image createPieChart_Mesaj_SayisinaGore(){
		
		Double calculate_total_aranan = (double)(turkcel.getMesaj_sayisi() + avea.getMesaj_sayisi() + vodafone.getMesaj_sayisi() + sabithat.getMesaj_sayisi() + diger.getMesaj_sayisi());
		
		Double turkcel_per = calculatePercentage((double)turkcel.getMesaj_sayisi() , calculate_total_aranan);
		Double avea_per = calculatePercentage((double)avea.getMesaj_sayisi() , calculate_total_aranan);
		Double vodafone_per = calculatePercentage((double)vodafone.getMesaj_sayisi() , calculate_total_aranan);
		Double sabit_per = calculatePercentage((double)sabithat.getMesaj_sayisi() , calculate_total_aranan);
		Double diger_per = calculatePercentage((double)diger.getMesaj_sayisi() , calculate_total_aranan);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue(turkcel.getOperator() , new Double(turkcel_per));
		dataset.setValue(avea.getOperator()    , new Double(avea_per));
		dataset.setValue(vodafone.getOperator(), new Double(vodafone_per));
		dataset.setValue(sabithat.getOperator(), new Double(sabit_per));
		dataset.setValue(diger.getOperator()   , new Double(diger_per));
		
                String message = "Hiç Mesaj Atmamışsınız. \n Bu yüzden grafik oluşturulmamıştır";
		Image mesaj_sayisi =  createPIEChart(dataset,null,message);
		
		return mesaj_sayisi;
	}
	public Image createPieChart_Mesaj_UcreteGore(){
		
		Image mesaj_ucret = null ;
		
		Double calculate_total_aranan = (double)(turkcel.getMesaj_ucret() + avea.getMesaj_ucret() + vodafone.getMesaj_ucret() + sabithat.getMesaj_ucret() + diger.getMesaj_ucret());
		
		Double turkcel_per = calculatePercentage((double)turkcel.getMesaj_ucret() , calculate_total_aranan);
		Double avea_per = calculatePercentage((double)avea.getMesaj_ucret() , calculate_total_aranan);
		Double vodafone_per = calculatePercentage((double)vodafone.getMesaj_ucret() , calculate_total_aranan);
		Double sabit_per = calculatePercentage((double)sabithat.getMesaj_ucret() , calculate_total_aranan);
		Double diger_per = calculatePercentage((double)diger.getMesaj_ucret() , calculate_total_aranan);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue(turkcel.getOperator() , new Double(turkcel_per));
		dataset.setValue(avea.getOperator()    , new Double(avea_per));
		dataset.setValue(vodafone.getOperator(), new Double(vodafone_per));
		dataset.setValue(sabithat.getOperator(), new Double(sabit_per));
		dataset.setValue(diger.getOperator()   , new Double(diger_per));
                String message = "";
		if(mesajSayisi>0)
		   message = "Hiç SMS ye Ücret ödememişsiniz. \n SMS Paketiniz var.\n Bu yüzden grafik oluşturulmamıştır";
               else if(mesajSayisi==0)
		   message = "Hiç SMS atmamışsınız\n Bu yüzden grafik oluşturulmamıştır";
		mesaj_ucret = createPIEChart(dataset,null,message);
		
		return mesaj_ucret ;
	}
	public Image createPieChart_Arama_UcreteGore(){
		
		Double calculate_total_aranan = (double)(turkcel.getArama_ucret() + avea.getArama_ucret() + vodafone.getArama_ucret() + sabithat.getArama_ucret() + diger.getArama_ucret());
		
		Double turkcel_per = calculatePercentage((double)turkcel.getArama_ucret() , calculate_total_aranan);
		Double avea_per = calculatePercentage((double)avea.getArama_ucret() , calculate_total_aranan);
		Double vodafone_per = calculatePercentage((double)vodafone.getArama_ucret() , calculate_total_aranan);
		Double sabit_per = calculatePercentage((double)sabithat.getArama_ucret() , calculate_total_aranan);
		Double diger_per = calculatePercentage((double)diger.getArama_ucret() , calculate_total_aranan);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue(turkcel.getOperator() , new Double(turkcel_per));
		dataset.setValue(avea.getOperator()    , new Double(avea_per));
		dataset.setValue(vodafone.getOperator(), new Double(vodafone_per));
		dataset.setValue(sabithat.getOperator(), new Double(sabit_per));
		dataset.setValue(diger.getOperator()   , new Double(diger_per));
		
                
		String message = "Hiç aramaya ücret ödememişsiniz. \n Paketinizdeki dakikalarınızı aşmadınız.\n Bu yüzden grafik oluşturulmamıştır.";
		Image arama_ucret = createPIEChart(dataset,null,message);
		
		return arama_ucret;
		
	}
	public Image createPieChart_ArananSayiyaGore(){
		// ABONE SAYISINA Gï¿½RE
		Double calculate_total_aranan = (double)(turkcel.getNumara_sayisi() + avea.getNumara_sayisi() + vodafone.getNumara_sayisi() + sabithat.getNumara_sayisi() + diger.getNumara_sayisi());
		
		Double turkcel_per = calculatePercentage((double)turkcel.getNumara_sayisi() , calculate_total_aranan);
		Double avea_per = calculatePercentage((double)avea.getNumara_sayisi() , calculate_total_aranan);
		Double vodafone_per = calculatePercentage((double)vodafone.getNumara_sayisi() , calculate_total_aranan);
		Double sabit_per = calculatePercentage((double)sabithat.getNumara_sayisi() , calculate_total_aranan);
		Double diger_per = calculatePercentage((double)diger.getNumara_sayisi() , calculate_total_aranan);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue(turkcel.getOperator() , new Double(turkcel_per));
		dataset.setValue(avea.getOperator()    , new Double(avea_per));
		dataset.setValue(vodafone.getOperator(), new Double(vodafone_per));
		dataset.setValue(sabithat.getOperator(), new Double(sabit_per));
		dataset.setValue(diger.getOperator()   , new Double(diger_per));
		
                String message = ""+"\nBu yüzden grafik oluşturulmamıştır";
		Image numara_sayisina_gore =  createPIEChart(dataset,null,message);
		
		return numara_sayisina_gore;
	}
	public Image createPieChart_AramaSuresineGore() throws Exception{
		
		Double calculate_total_sure = turkcel.getToplam_sure() + avea.getToplam_sure() + vodafone.getToplam_sure() + sabithat.getToplam_sure() + diger.getToplam_sure();
		
		Double turkcel_per = calculatePercentage(turkcel.getToplam_sure() , calculate_total_sure);
		Double avea_per = calculatePercentage(avea.getToplam_sure() , calculate_total_sure);
		Double vodafone_per = calculatePercentage(vodafone.getToplam_sure() , calculate_total_sure);
		Double sabit_per = calculatePercentage(sabithat.getToplam_sure() , calculate_total_sure);
		Double diger_per = calculatePercentage(diger.getToplam_sure() , calculate_total_sure);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue(turkcel.getOperator() , new Double(turkcel_per));
		dataset.setValue(avea.getOperator()    , new Double(avea_per));
		dataset.setValue(vodafone.getOperator(), new Double(vodafone_per));
		dataset.setValue(sabithat.getOperator(), new Double(sabit_per));
		dataset.setValue(diger.getOperator()   , new Double(diger_per));
		String message = "Hiç arama yapmamışınız."+"\nBu yüzden grafik oluşturulmamıştır";
		Image sure_bazli_img  =  createPIEChart(dataset,null,message);

		return sure_bazli_img;
		
	}
         public Image createPIEChart2(DefaultPieDataset dataset){
                Image image = null ;
                Color[] colors = {Color.blue, Color.white, Color.red,Color.cyan,Color.green};
		
		JFreeChart chart = ChartFactory.createPieChart3D(null,dataset,true,true,false);
		
		PiePlot plot =((PiePlot) chart.getPlot());
                plot.setForegroundAlpha(1.0f);
		plot.setCircular(true);
		plot.setStartAngle(250);
		plot.setDirection(Rotation.CLOCKWISE);
                     float h = 250;
                     float w = 180;
                    GradientPaint gradientPaint = new GradientPaint(0.0F, 10.0F, Color.white, 0, h, Color.YELLOW);
                    plot.setBackgroundPaint(gradientPaint);
		plot.setNoDataMessage("Değerler olmadığından grafik çizilmemiştir");
		plot.setLabelFont(new Font("Arial", Font.PLAIN, 7));
//		
                plot.setInteriorGap(0.1);
                PieRenderer renderer = new PieRenderer(colors);
                renderer.setColor(plot, dataset);
                  plot.setLabelGenerator(new CustomLabelGenerator());
		plot.setNoDataMessageFont(new Font("Arial", Font.PLAIN, 12));
		
		
		
		image =  chart.createBufferedImage(250,180);
		
            return image;
        }
        public Image createPIEChart(DefaultPieDataset dataset,PieSectionLabelGenerator genarator,String message){
                Image image = null ;
                Color[] colors = {Color.blue, Color.white, Color.red,Color.cyan,Color.green};
		
		JFreeChart chart = ChartFactory.createPieChart3D(null,dataset,true,false,false);
                
		GradientPaint gradientPaint = new GradientPaint(0.0F, 6.0F, Color.white, 0, 180, new Color(204,255,230));		
                PiePlot3D plot = (PiePlot3D) chart.getPlot();
                plot.setBackgroundPaint(gradientPaint);
		plot.setCircular(true);
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.7f);
		plot.setNoDataMessage(message);
                plot.setNoDataMessageFont(new Font("Arial", Font.PLAIN, 10));
                plot.setNoDataMessagePaint(Color.RED);
                if(genarator!=null)
                {    plot.setLabelFont(new Font("Arial", Font.PLAIN, 8));

                }
              //  plot.setToolTipGenerator(new StandardPieToolTipGenerator());
                plot.setToolTipGenerator(null);
                plot.setInteriorGap(0.01);
                
                PieRenderer renderer = new PieRenderer(colors);
                renderer.setColor(plot, dataset);
		plot.setLabelGenerator(genarator);
		
		
		
		image =  chart.createBufferedImage(250,180);
		
            return image;
        }

        
        public void calculateTotals(Operator op ,String operatorName){
	
		 aramaSayisi 		+= op.getArama_sayisi();
		 mesajSayisi 		+= op.getMesaj_sayisi();
		 toplamSure 		+= op.getToplam_sure();
		 toplamMesajUcret 	+= op.getMesaj_ucret();
		 toplamAramaUcret 	+= op.getArama_ucret();
		 toplamNumara           += op.getNumara_sayisi();
		 toplamPeriyodSure       += op.getToplam_periyod_sure();
                
                 operatorler.put(operatorName, op);
                 operatorler1.put(operatorName, op);
	}
                
	public Double calculatePercentage(Double sure , Double total_sure ){
		
		Double percen = ( sure* 100 )/ total_sure; 
		
		return percen;
	}
	
	public Operator degerHesapla (HashMap<String, ArrayList<TelefonPojo>> telpojo , String operator){
		
		Operator oper = new Operator();
		
		Iterator<String> it = telpojo.keySet().iterator();
		
		int numara_sayisi = 0 ;
		
		Double mesaj_ucret = 0.0 ;
		Double arama_ucret = 0.0 ;
		Double toplam_sure = 0.0 ;
		Double toplam_periyod_sure = 0.0;
                
                
		int arama_sayisi = 0;
		int mesaj_sayisi = 0 ;
		
		while(it.hasNext()){
			
			String numara = it.next();
			
			numara_sayisi ++;
			
			ArrayList<TelefonPojo> pojolar  = telpojo.get(numara);
			
                        toplam_gorusme += pojolar.size();
			for(int i = 0 ; i<pojolar.size();i++){
				
				TelefonPojo  pojo = pojolar.get(i);

				 Double  sure		=pojo.getSure();
				 Double  tutar		=pojo.getTutar();
                                 
				 if(pojo.getType().equalsIgnoreCase("Telefon")){
					 
					 arama_ucret = arama_ucret + tutar;
					 toplam_sure = toplam_sure + sure ;
                                         if(numara.length()>5)
                                         toplam_periyod_sure = toplam_periyod_sure + calculatePeriyodSure(sure,numara);                                      
					 arama_sayisi++;
					 
				 }else if(pojo.getType().equalsIgnoreCase("SMS")){
					 
					 mesaj_ucret = mesaj_ucret + tutar;
					 mesaj_sayisi ++;
				 }
				
				
				
			}
			
			
		}
		
		oper.setArama_sayisi(arama_sayisi);
		oper.setArama_ucret(arama_ucret);
		oper.setMesaj_sayisi(mesaj_sayisi);
		oper.setMesaj_ucret(mesaj_ucret);
		oper.setToplam_sure(toplam_sure);
		oper.setNumara_sayisi(numara_sayisi);
		oper.setToplam_periyod_sure(toplam_periyod_sure);
		
		oper.setOperator(operator);
		
		return oper ;
	}
public Double calculatePeriyodSure(Double sure,String numara){
     
     if(sure==null || sure==0.0)
         return 0.0;
     Double kalan = sure %6;
     kalan = kalan == 0?6:kalan;
     Double periyod_sure = sure + (6-kalan);

     return periyod_sure;   
 }

 public static class PieRenderer
 {
     private Color[] color;
    
     public PieRenderer(Color[] color)
     {
         this.color = color;
     }       
    
     public void setColor(PiePlot plot, DefaultPieDataset dataset)
     {
         List <Comparable> keys = dataset.getKeys();
         int aInt;
        
         for (int i = 0; i < keys.size(); i++)
         {
             aInt = i % this.color.length;
             plot.setSectionPaint(keys.get(i), this.color[aInt]);
            
         }
     }
 }
	public Image getSure_bazli_img() {
		return sure_bazli_img;
	}

	public void setSure_bazli_img(Image sureBazliImg) {
		sure_bazli_img = sureBazliImg;
	}

	public Image getNumara_sayisina_gore() {
		return numara_sayisina_gore;
	}

	public void setNumara_sayisina_gore(Image numaraSayisinaGore) {
		numara_sayisina_gore = numaraSayisinaGore;
	}

	public Image getArama_ucret() {
		return arama_ucret;
	}

	public void setArama_ucret(Image aramaUcret) {
		arama_ucret = aramaUcret;
	}

	public Image getMesaj_ucret() {
		return mesaj_ucret;
	}

	public void setMesaj_ucret(Image mesajUcret) {
		mesaj_ucret = mesajUcret;
	}

	public Image getMesaj_sayisi() {
		return mesaj_sayisi;
	}

	public void setMesaj_sayisi(Image mesajSayisi) {
		mesaj_sayisi = mesajSayisi;
	}
	public int getToplamNumara() {
		return toplamNumara;
	}

	public void setToplamNumara(int toplamNumara) {
		this.toplamNumara = toplamNumara;
	}
        
        
 
	public ArrayList<Operator> getOperatorAnalizler(){
	
		ArrayList<Operator> operator = new ArrayList<Operator>();
		operator.add(turkcel);
		operator.add(vodafone);
		operator.add(avea);
		operator.add(sabithat);
		operator.add(diger);
		
		return operator ;
	}

    public HashMap<String, Operator> getOperatorler() {
        return operatorler;
    }
    public TarifeOnerPojo getTarifeOnerPojo() {
        return toner;
    }
    public void setOperatorler(HashMap<String, Operator> operatorler) {
        this.operatorler = operatorler;
    }
        
        
     static class CustomLabelGenerator implements PieSectionLabelGenerator {
        
        /**
         * Generates a label for a pie section.
         * 
         * @param dataset  the dataset (<code>null</code> not permitted).
         * @param key  the section key (<code>null</code> not permitted).
         * 
         * @return the label (possibly <code>null</code>).
         */
        public String generateSectionLabel(final PieDataset dataset, final Comparable key) {
            String result = null;    
            if (dataset != null) {
                if (!key.equals("Vodafone")) {
                    result = key.toString();   
                }
            }
            return result;
        }

        @Override
        public AttributedString generateAttributedSectionLabel(PieDataset dataset, Comparable key) {
            AttributedString result = null;    
            if (dataset != null) {
                if (!key.equals("Vodafone")) {
                    result = new AttributedString(key.toString());  
                }
            }
            return result;
        }
   
    }

    public Double getToplamPeriyodSure() {
        return toplamPeriyodSure;
    }

    public void setToplamPeriyodSure(Double toplamPeriyodSure) {
        this.toplamPeriyodSure = toplamPeriyodSure;
    }
     
     
     
}
