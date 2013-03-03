/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import vodafone.pojolar.DatabasePojo;

/**
 *
 * @author LifeBook
 */
public class DatabaseInsert {
    
     private   HashMap<String, DatabasePojo> 	databasePojolar;
     private   Connection                      connection = null;
        
        public DatabaseInsert(HashMap<String, DatabasePojo> 		databasePojolar) {
		// TODO Auto-generated constructor stub
		this.databasePojolar = databasePojolar;
		
	}
        
        public void kaydetDatabase(){
		
		Iterator<String> it1 = databasePojolar.keySet().iterator();
		 PreparedStatement ps ;
		boolean sonuc = createTable();
                
		if(sonuc){
                    try{
			String sql1 = "TRUNCATE TABLE gorusme";
                        ps = (PreparedStatement)connection.prepareStatement(sql1);
                        ps.executeUpdate();
                    } catch(Exception ex){
                        ex.printStackTrace();
                    }      
			while(it1.hasNext()){
				
				String numara = it1.next();
				
				DatabasePojo dpojo = databasePojolar.get(numara);
			
				String 	operator 			= 		dpojo.getOperator();
				int 	arama_sayisi 			= 		dpojo.getAramaSayisi();
				int 	mesaj_sayisi 			=		dpojo.getMesajSayisi();
				double toplam_mesaj_ucret  		= 		dpojo.getToplamMesajUcret();
				double toplam_arama_ucret  		= 		dpojo.getToplamAramaUcret();
				double toplam_arama_dakika              = 		dpojo.getToplamAramaDakika();
				double ortalama_gorusme    		= 		dpojo.getOrtalamaGorusme();
				double internet_miktar     		= 		dpojo.getToplamMiktar();
				
                              
				
					String sql = "INSERT INTO gorusme (numara,operator,mesajSayisi,aramaSayisi,toplamDakika,toplamAramaUcret,toplamMesajUcret,ortalamaGorusme) VALUES (?,?,?,?,?,?,?,?)";	
				try{	
											//	SELECT numara, toplamDakika, aramaSayisi from gorusme ORDER BY toplamDakika;
                                        ps = (PreparedStatement) connection.prepareStatement(sql);
					
					ps.setString(1, numara);
					ps.setString(2,operator);
					ps.setInt(3, mesaj_sayisi);
					ps.setInt(4, arama_sayisi);
					
                                        if(!dpojo.getOperator().equalsIgnoreCase("Internet")) {
                                             ps.setDouble(5, toplam_arama_dakika);
                                        }
                                        else {
                                            ps.setDouble(5, internet_miktar);
                                        }
					ps.setDouble(6, toplam_arama_ucret);
					ps.setDouble(7, toplam_mesaj_ucret);
					ps.setDouble(8, ortalama_gorusme);
					ps.executeUpdate();
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
        
        
        
       private boolean createTable(){
		boolean sonuc = false;
		
//		try {
//                    Class.forName("org.hsqldb.jdbc.JDBCDriver" );
//
//                    connection =   DriverManager.getConnection("jdbc:hsqldb:mem:KARNE","KARNE","");
//
//                }catch(Exception ex){
//			ex.printStackTrace();
//		}
//		
//		try {
//			if(connection!=null){
//				String sql21 = "DROP table gorusme";
//				Statement st = connection.createStatement();
//				boolean res =	st.execute(sql21);
//			}
//			
//			
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
		try{
                        Class.forName("org.hsqldb.jdbc.JDBCDriver" );

                       connection =   DriverManager.getConnection("jdbc:hsqldb:mem:KARNE","KARNE","");

			String sql = "CREATE TABLE  gorusme ( numara VARCHAR(45), operator VARCHAR(45), mesajSayisi INTEGER, aramaSayisi INTEGER,toplamDakika INTEGER, toplamAramaUcret DOUBLE, toplamMesajUcret DOUBLE, ortalamaGorusme DOUBLE)";
			Statement ps = null;
			ps = connection.createStatement();
			ps.executeUpdate(sql);
			sonuc = true;
			
			
		}catch(Exception ex){
			ex.printStackTrace();
                        if(ex.toString().contains(" object name already exists:"))
                            sonuc = true;
                        else sonuc = false;
		}
		return sonuc;
	}

    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }       
}
