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
public class saveRoom extends HttpServlet {
HttpSession session;
String roomname,houseid,id,status;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            session=request.getSession();
            dbConn conn = new dbConn();
            IdGenerator IG = new IdGenerator();
            id=IG.current_id();
            status=houseid=roomname="";
            
            houseid=request.getParameter("houseid");
            roomname=request.getParameter("room");
            
            String checker="SELECT id FROM rooms WHERE house_id=? && name=?";
            conn.pst=conn.conn.prepareStatement(checker);
            conn.pst.setString(1, houseid);
            conn.pst.setString(2, roomname);
            conn.rs=conn.pst.executeQuery();
            if(conn.rs.next()==true){
            status="<font color=\"red\"><b>Failed : </b>Room already exist.</font>";    
            }
            
            else{
                String inserter="INSERT INTO rooms (id,house_id,name) VALUES(?,?,?)";
                conn.pst=conn.conn.prepareStatement(inserter);
                conn.pst.setString(1, id);
                conn.pst.setString(2, houseid);
                conn.pst.setString(3, roomname);
                
                conn.pst.executeUpdate();
              status="<font color=\"green\"><b>Success : </b>Room added successfully.</font>";  
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
        Logger.getLogger(saveRoom.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(saveRoom.class.getName()).log(Level.SEVERE, null, ex);
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
