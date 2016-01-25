/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package loaders;

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
public class loadHouseRent extends HttpServlet {
HttpSession session;
String houseid,responseTXT,yearmonth,roomid;
int paid,topay,rent;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         session=request.getSession();
         dbConn conn = new dbConn();
         
         
            roomid=request.getParameter("roomid");
            yearmonth=request.getParameter("yearmonth").replace("-", "");
            System.out.println("year month : "+yearmonth+" room id : "+roomid);
            String getPaid="SELECT SUM(amount) FROM payments WHERE room_id='"+roomid+"' && yearmonth='"+yearmonth+"'";
            conn.rs=conn.st.executeQuery(getPaid);
            if(conn.rs.next()==true){
                paid=conn.rs.getInt(1);
            }
            else{
                paid=0;
            }
            
            String getRent="SELECT house_rent.rent FROM house_rent JOIN rooms ON house_rent.house_id=rooms.house_id WHERE rooms.id='"+roomid+"'";
            conn.rs=conn.st.executeQuery(getRent);
            if(conn.rs.next()==true){
           rent=conn.rs.getInt(1);
            }
            else{
                rent=0;
            }
          topay=rent-paid;
            responseTXT=rent+"-"+paid+"-"+topay;
            out.println(responseTXT);
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
        Logger.getLogger(loadHouseRent.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadHouseRent.class.getName()).log(Level.SEVERE, null, ex);
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
