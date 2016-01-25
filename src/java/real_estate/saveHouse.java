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
public class saveHouse extends HttpServlet {
HttpSession session;
String housename,plotid,id;
String status;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           session=request.getSession();
           dbConn conn = new dbConn();
            status=housename=plotid="";
          IdGenerator IG = new IdGenerator();
            id=IG.current_id();
            
            
            plotid=request.getParameter("plotid");
            housename=request.getParameter("housename");
            
            String checker="SELECT id FROM houses WHERE name=? and plot_id=?";
            conn.pst=conn.conn.prepareStatement(checker);
            conn.pst.setString(1, housename);
            conn.pst.setString(2, plotid);
            
            conn.rs=conn.pst.executeQuery();
            if(conn.rs.next()==true){
            status="<font color=\"red\"><b>Failed : </b> House already exist.</font>";    
            }
            else{
                
                String inserter="INSERT INTO houses (id,plot_id,name) VALUES (?,?,?)";
               conn.pst=conn.conn.prepareStatement(inserter);
                conn.pst.setString(1, id);
                conn.pst.setString(2, plotid);
                conn.pst.setString(3, housename);
                
                conn.pst.executeUpdate();
                status="<font color=\"green\"><b>Success : </b> House added successfully.</font>";
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
        Logger.getLogger(saveHouse.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(saveHouse.class.getName()).log(Level.SEVERE, null, ex);
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
