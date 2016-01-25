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
public class saveMonthlyRent extends HttpServlet {
HttpSession session;
String roomid,year,month,yearmonth,ym,id,status;
int unpaid,rent;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       
        session=request.getSession();
        dbConn conn = new dbConn();
        IdGenerator IG = new IdGenerator();
              
        id=IG.current_id();
        
        ym=request.getParameter("rentMonth").trim();
        yearmonth=ym.replace("-", "");
        String [] yms=ym.split("-");
        year=yms[0];
        month=yms[1];
        rent=Integer.parseInt(request.getParameter("rent").trim());
        unpaid=Integer.parseInt(request.getParameter("balance").trim());
        
        roomid=request.getParameter("room");
        status="";
        if(unpaid>=rent){
        String inserter="INSERT INTO payments (id,room_id,amount,year,month,yearmonth) VALUES (?,?,?,?,?,?)";
        conn.pst=conn.conn.prepareStatement(inserter);
        conn.pst.setString(1, id);
        conn.pst.setString(2, roomid);
        conn.pst.setInt(3, rent);
        conn.pst.setString(4, year);
        conn.pst.setString(5, month);
        conn.pst.setString(6, yearmonth);
    
        conn.pst.executeUpdate();
        
        status="<font color=\"green\">Rent saved successfully.</font>";
//            out.println("saved");
        }
        
        
        session.setAttribute("savedRent", status);
        response.sendRedirect("payRent.jsp");
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
        Logger.getLogger(saveMonthlyRent.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(saveMonthlyRent.class.getName()).log(Level.SEVERE, null, ex);
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
