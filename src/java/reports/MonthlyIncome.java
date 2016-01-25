/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import databases.dbConn;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey
 */
public class MonthlyIncome extends HttpServlet {
HttpSession session;
String yearMonth,year,month,monthName;
String output="";
int position,amount;
String plotname,housename,roomname;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DocumentException, CssResolverException {
      session = request.getSession();
      dbConn conn = new dbConn();
     
      position=amount=0;
      plotname=housename=roomname="";

      yearMonth = request.getParameter("yearMonth");
      yearMonth="2015-01";
      String ym[]=yearMonth.split("-");
     year =ym[0];
     month=ym[1];
     yearMonth=yearMonth.replace("-", "");
     
     output="<html><head>"
                   + "<style>  </style>"
                   + ""
                   + "</head><body>";
     
    String getMonth ="SELECT name FROM months WHERE id='"+month+"'";
    conn.rs=conn.st.executeQuery(getMonth);
    if(conn.rs.next()==true){
        monthName=conn.rs.getString(1);
    }
     
     output+="<div style=\"left:200px;\">Monthly Rent Payment Report for <b>"+monthName+" "+year+"</b>.</div><br>";
     output+="<table border=\"1\"><tr>"
             + "<th><b>No.</b></th>"
             + "<th><b>Plot.</b></th>"
             + "<th><b>House Name.</b></th>"
             + "<th><b>Room Name.</b></th>"
             + "<th><b>Amount Paid.</b></th>"
             + "</tr>";
     
     
     
     String query = "SELECT SUM(payments.amount),plots.name,houses.name,rooms.name "
             + "FROM payments JOIN rooms ON payments.room_id=rooms.id JOIN houses ON rooms.house_id=houses.id "
             + "JOIN plots ON houses.plot_id=plots.id WHERE payments.yearmonth='"+yearMonth+"' GROUP BY rooms.id";
     conn.rs=conn.st.executeQuery(query);
     while(conn.rs.next()){
//         output data here
         position++;
         amount=conn.rs.getInt(1);
         plotname = conn.rs.getString(2);
         housename = conn.rs.getString(3);
         roomname = conn.rs.getString(4);
         output+="<tr>"
             + "<td>"+position+"</td>"
             + "<td>"+plotname+"</td>"
             + "<td>"+housename+"</td>"
             + "<td>"+roomname+"</td>"
             + "<td> Ksh. "+amount+"</td>"
             + "</tr>";
     }
     
     output+="</table>"
             + "</body>"
             + "</html>";

       Document document = new Document();
  ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
PdfWriter.getInstance(document, outByteStream);
      document.open();
      
      HTMLWorker htmlWorker = new HTMLWorker(document);
       htmlWorker.parse(new StringReader(output));
       
       CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(false);  
cssResolver.addCss("", true);

   document.close();    
response.setContentType("application/pdf");
response.setContentLength(outByteStream.size());
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=monthly_report.pdf");

ServletOutputStream sos;
sos = response.getOutputStream();
outByteStream.writeTo(sos);
sos.flush();
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException | DocumentException | CssResolverException ex) {
        Logger.getLogger(MonthlyIncome.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException | DocumentException | CssResolverException ex) {
        Logger.getLogger(MonthlyIncome.class.getName()).log(Level.SEVERE, null, ex);
    }
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
