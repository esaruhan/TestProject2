/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.islemler;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 *
 * @author LifeBook
 */
public class LogFile {
    
    public static void insertLog(String message, Level log_level){
        try {
             LogManager lm = LogManager.getLogManager();
             String path = System.getProperty("user.dir")+"/log_process.txt";
             FileHandler fh = new FileHandler(path);
             Logger logger = Logger.getLogger("LogginVodafone");
             lm.addLogger(logger);
             logger.setLevel(log_level);
             fh.setFormatter(new XMLFormatter());
             logger.addHandler(fh);
             logger.log(log_level, message);
             fh.close();
        } catch (IOException ex) {
            Logger.getLogger(LogFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(LogFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
