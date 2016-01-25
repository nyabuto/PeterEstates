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
public class savePlot extends HttpServlet {
HttpSession session;
String plotname,id;
String status;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
       session=request.getSession();
       dbConn conn = new dbConn();
            IdGenerator IG = new IdGenerator();
            id=IG.current_id();
       
       plotname=request.getParameter("plotname");
        
       status="";
       String checker="SELECT id FROM plots WHERE name=? OR id=?";
       conn.pst=conn.conn.prepareStatement(checker);
       conn.pst.setString(1, plotname);
       conn.pst.setString(2, id);
       
       conn.rs=conn.pst.executeQuery();
       if(conn.rs.next()==true){
         status="<font color=\"red\"><b>Failed </b> : Plot already exist.</font>";
           
       }
       else{
           String inserter="INSERT INTO plots (id,name) VALUES(?,?)";
           conn.pst=conn.conn.prepareStatement(inserter);
           conn.pst.setString(1, id);
           conn.pst.setString(2, plotname);
           conn.pst.executeUpdate();
           status="<font color=\"green\"><b>Success : </b> Plot added successfully.</font>";  
       }
            out.println(status);
        }
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
        Logger.getLogger(savePlot.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(savePlot.class.getName()).log(Level.SEVERE, null, ex);
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
