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
public class saveEditedRoom extends HttpServlet {
HttpSession session;
String id,name,plotid,houseid,status;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            session=request.getSession();
            dbConn conn = new dbConn();
            
            id=request.getParameter("id");
            name=request.getParameter("name");
            plotid=request.getParameter("plot_id");
            houseid=request.getParameter("house_id");
            
            String updator="UPDATE rooms SET name=?,house_id=? WHERE id=?";
            conn.pst=conn.conn.prepareStatement(updator);
            conn.pst.setString(1, name);
            conn.pst.setString(2, houseid);
            conn.pst.setString(3, id);
            
           int res= conn.pst.executeUpdate();
           
           if(res==1){
             status="<font color=\"\"><b>SUCCESS :</b> Record edited successfully. </font>";
         }
         else{
             status="</font color=\"\"><b>ERROR : </b>Failed to edit record, refresh and try again.</font>";
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
        Logger.getLogger(saveEditedRoom.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(saveEditedRoom.class.getName()).log(Level.SEVERE, null, ex);
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
