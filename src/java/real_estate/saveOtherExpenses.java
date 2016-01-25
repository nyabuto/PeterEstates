/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package real_estate;

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

/**
 *
 * @author Geofrey
 */
public class saveOtherExpenses extends HttpServlet {
HttpSession session;
String expenseType,plot,room,amount,reason,date,responseTXT;
String  year,month,yearMonth,id,status;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       
          session=request.getSession();
          dbConn conn = new dbConn();
          IdGenerator IG = new IdGenerator();
          
          id=IG.current_id();
          expenseType=plot=room=amount=reason=date=responseTXT=status="";
          
          expenseType=request.getParameter("expenseType");
          plot=request.getParameter("plot");
          room=request.getParameter("room");
          reason=request.getParameter("reason");
          date=request.getParameter("yearMonth");
          amount=request.getParameter("amount");
          
          String [] yearsm=date.split("-");
          
          year=yearsm[0];
          month=yearsm[1];
          yearMonth=date.replace("-", "");
         String checker="SELECT id FROM expenses WHERE plotid=? && roomid=? && amount=? && yearmonth=?";
         conn.pst=conn.conn.prepareStatement(checker);
         conn.pst.setString(1, plot);
         conn.pst.setString(2, room);
         conn.pst.setString(3, amount);
         conn.pst.setString(4, yearMonth);
        
         
         conn.rs=conn.pst.executeQuery();
         if(conn.rs.next()==true){
         status="<font color=\"red\"><b>Failed</b> : Expenses already exist in the system.</font>";    
         }
         else{
             String inserter="INSERT INTO expenses (id,expense_type,plotid,roomid,reason,year,month,yearmonth,amount) VALUES(?,?,?,?,?,?,?,?,?)";
             conn.pst=conn.conn.prepareStatement(inserter);
             conn.pst.setString(1, id);
             conn.pst.setString(2, expenseType);
             conn.pst.setString(3, plot);
             conn.pst.setString(4, room);
             conn.pst.setString(5, reason);
             conn.pst.setString(6, year);
             conn.pst.setString(7, month);
             conn.pst.setString(8, yearMonth);
             conn.pst.setString(9, amount);
             
             conn.pst.executeUpdate();
             status="<font color=\"green\">Expenses added successfully.</font>";
         }
          session.setAttribute("saveExpenses", status);
        
        System.out.println("the status is : "+status);
        response.sendRedirect("otherExpenses.jsp");
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
        Logger.getLogger(saveOtherExpenses.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(saveOtherExpenses.class.getName()).log(Level.SEVERE, null, ex);
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
