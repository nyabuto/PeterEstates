/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import databases.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import databases.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import javax.servlet.ServletOutputStream;

/**
 *
 * @author Geofrey
 */
public class MonthlyExpenses extends HttpServlet {
HttpSession session;
String yearMonth,year,month,output;
String plot,reason,monthName,house,room,totalTable;
int position,amount,totalPaid,totalExpenses;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DocumentException, CssResolverException {
     session = request.getSession();
     dbConn conn = new dbConn();
     
//     yearMonth=request.getParameter("yearMonth");
     yearMonth = "2017-02";
     String ym[]=yearMonth.split("-");
     year=ym[0];
     month=ym[1];
     position=amount=totalPaid=totalExpenses=0;
     yearMonth=yearMonth.replace("-", "");
    monthName="";
     output="<html><head>"
                   + "<style>  </style>"
                   + ""
                   + "</head><body>";
     
    String getMonth ="SELECT name FROM months WHERE id='"+month+"'";
    conn.rs=conn.st.executeQuery(getMonth);
    if(conn.rs.next()==true){
        monthName=conn.rs.getString(1);
    }
     
     output+="<div style=\"left:200px;\">Monthly Expenses Report for <b>"+monthName+" "+year+"</b>.</div><br>";
     output+="<table border=\"1\"><tr>"
             + "<th><b>No.</b></th>"
             + "<th><b>Plot.</b></th>"
             + "<th><b>Reason/Justification</b></th>"
             + "<th><b>Amount Paid.</b></th>"
             + "</tr>";
     
     
     String getData="SELECT plots.name,expenses.reason,expenses.amount FROM expenses JOIN plots ON expenses.plotid=plots.id "
             + "WHERE expenses.expense_type='1' AND expenses.yearMonth='"+yearMonth+"'";
     conn.rs = conn.st.executeQuery(getData);
     while(conn.rs.next()==true){
    plot = conn.rs.getString(1);
    reason=conn.rs.getString(2);
    amount = conn.rs.getInt(3);
    totalPaid+=amount;
    position++;
    output+="<tr>"
             + "<td>"+position+"</td>"
             + "<td>"+plot+"</td>"
             + "<td>"+reason+"</td>"
            + "<td> Ksh. "+amount+"</td>"
             + "</tr>";
    totalExpenses+=amount;
     }
     
      output+="<tr>"
             + "<td colspan=\"3\"><b>Total amount paid.</b></td>"
               + "<td><b> Ksh. "+totalPaid+"</b></td>"
             + "</tr>";
     
     output+="</table>";
     output+="<br>";
     output+="<table border=\"1\"><tr>"
             + "<th><b>No.</b></th>"
             + "<th><b>Plot.</b></th>"
             + "<th><b>House.</b></th>"
             + "<th><b>Room.</b></th>"
             + "<th><b>Reason/Justification</b></th>"
             + "<th><b>Amount Paid.</b></th>"
             + "</tr>"; 
     
     
   
     position=totalPaid=0;
     String getRoomExpenses = "SELECT plots.name,houses.name,rooms.name,expenses.reason,expenses.amount FROM expenses "
             + "JOIN rooms ON expenses.roomid=rooms.id "
             + "JOIN houses ON rooms.house_id=houses.id JOIN plots on houses.plot_id=plots.id "
             + "WHERE expenses.expense_type='2' AND expenses.yearMonth='"+yearMonth+"'";
     conn.rs=conn.st.executeQuery(getRoomExpenses);
     while(conn.rs.next()){
    plot = conn.rs.getString(1);
    house = conn.rs.getString(2);
    room = conn.rs.getString(3);
    reason=conn.rs.getString(4);
    amount = conn.rs.getInt(5);
    totalPaid+=amount;
    position++;
     output+="<tr>"
             + "<td>"+position+"</td>"
             + "<td>"+plot+"</td>"
             + "<td>"+house+"</td>"
             + "<td>"+room+"</td>"
             + "<td>"+reason+"</td>"
             + "<td> Ksh. "+amount+"</td>"
             + "</tr>"; 
     totalExpenses+=amount;
     }
     
      output+="<tr>"
             + "<td colspan=\"5\"><b>Total amount paid.</b></td>"
               + "<td><b> Ksh. "+totalPaid+"</b></td>"
             + "</tr>";
      
        output+="</table>";
        
        output+="<br>";
     output+="<table border=\"1\">";
     output+="<tr>"
             + "<td colspan=\"5\"><b>Total amount paid (both Room and Plot  expenses).</b></td>"
               + "<td><b> Ksh. "+totalExpenses+"</b></td>"
             + "</tr>";
      output+="</table>";
     
             output+="</body>"
             + "</html>";
             
             if(totalExpenses==0){
               output="<html><head>"
                   + "<style>  </style>"
                   + ""
                   + "</head><body>";
                 output+="<div style=\"left:200px;\">No Expenses were incurred on : <b>"+monthName+" "+year+"</b>.</div><br>";
                  output+="</body>"
             + "</html>";
             }

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
response.setHeader("Content-Disposition", "attachment; filename=monthly_expenses_report.pdf");

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
    } catch (SQLException ex) {
        Logger.getLogger(MonthlyExpenses.class.getName()).log(Level.SEVERE, null, ex);
    } catch (DocumentException ex) {
        Logger.getLogger(MonthlyExpenses.class.getName()).log(Level.SEVERE, null, ex);
    } catch (CssResolverException ex) {
        Logger.getLogger(MonthlyExpenses.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (SQLException ex) {
        Logger.getLogger(MonthlyExpenses.class.getName()).log(Level.SEVERE, null, ex);
    } catch (DocumentException ex) {
        Logger.getLogger(MonthlyExpenses.class.getName()).log(Level.SEVERE, null, ex);
    } catch (CssResolverException ex) {
        Logger.getLogger(MonthlyExpenses.class.getName()).log(Level.SEVERE, null, ex);
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
