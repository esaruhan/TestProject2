/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vodafone.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import vodafone.main.NewClass;

/**
 *
 * @author LifeBook
 */
public class RaporServlet extends HttpServlet {
    
     FileItem item = null;
        String   outputPath = "";
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            {
        response.setContentType("application/pdf;charset=UTF-8");
       
        response.setCharacterEncoding("UTF8");
       
      PrintWriter out = null ;
        try {
            out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            // Create a factory for disk-based file items
            if(isMultipart){
                DiskFileItemFactory factory = new DiskFileItemFactory();
                // Set factory constraints
                factory.setSizeThreshold(200058);
                factory.setRepository(new File("C:\\VodafoneRaporlar"));
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // Set overall request size constraint
                upload.setSizeMax(200058);
                List /* FileItem */ items = upload.parseRequest(request);
                
                Iterator iter = items.iterator();
                if (iter.hasNext()) {
                    item = (FileItem) iter.next();

                }
            }

          
//            if (!item.isFormField()) {
//                String fieldName = item.getFieldName();  
//                String fileName = item.getName();  
//                String contentType = item.getContentType();     
//                boolean isInMemory = item.isInMemory();         
//                long sizeInBytes = item.getSize();
//            }
        
            File uploadedFile = new File("C:\\VodafoneRaporlar\\test.xls");
            item.write(uploadedFile);
            NewClass myclass    = new NewClass(uploadedFile.getAbsolutePath());      
               outputPath = myclass.outputPath();
               
                String pdfFileName = "pdf-test.pdf";
		
		File pdfFile = new File(outputPath);

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=" + "5368799737##15-02-2013##-##16-01-2013.pdf");
		response.setContentLength((int) pdfFile.length());

		FileInputStream fileInputStream = new FileInputStream(pdfFile);
		OutputStream responseOutputStream = response.getOutputStream();
		int bytes;
		while ((bytes = fileInputStream.read()) != -1) {
			responseOutputStream.write(bytes);
		}
               
                fileInputStream.close();
                responseOutputStream.close();
        } catch(Exception ex) {
            out.println("Sorun oluştu" +ex.toString());
        }finally {            
            out.close();
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    
            processRequest(request, response);
       
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    
            processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
