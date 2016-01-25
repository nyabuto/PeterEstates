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
public class datatableRent extends HttpServlet {
HttpSession session;
String id,plotname,housename,roomname,rent,yearMonth,concantYearMonth,data;
int position;
String hidden,edit,delete;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          session=request.getSession();
          dbConn conn= new dbConn();
          id=plotname=housename=roomname=rent=yearMonth=concantYearMonth=data="";
          position=0;
          
          yearMonth=request.getParameter("yearMonth");
          concantYearMonth=yearMonth.replace("-", "");
//            System.out.println("year month : "+concantYearMonth);
          String getData="SELECT plots.name,houses.name,rooms.name,payments.amount FROM "
                  + "payments JOIN rooms ON payments.room_id=rooms.id JOIN houses ON rooms.house_id=houses.id "
                  + "JOIN plots ON houses.plot_id=plots.id "
                  + "WHERE payments.yearmonth='"+concantYearMonth+"' ORDER BY plots.name,houses.name,rooms.name";
          conn.rs=conn.st.executeQuery(getData);
          while(conn.rs.next()){
            plotname=conn.rs.getString(1);
            housename=conn.rs.getString(2);
            roomname=conn.rs.getString(3);
            rent=conn.rs.getString(4);
            position++;
                hidden="<input type=\"hidden\" name=\"val_"+position+"\" id=\"val_"+position+"\" value=\""+id+"\">";
             edit="<button class=\"form-control btn btn-info\" id=\"edit_"+position+"\" onclick=\"return editor("+position+");\"  name=\"edit_"+position+"\">Edit</button>";
            delete="<button class=\"form-control btn btn-danger\" id=\"delete_"+position+"\" onclick=\"return deletor("+position+");\"  name=\"delete_"+position+"\">Delete</button>";
            
             data+="<tr><td>"+position+"</td><td>"+plotname+"</td><td>"+housename+"</td><td>"+roomname+"</td><td>"+yearMonth+"</td><td>"+rent+"</td><td>"+edit+"</td><td>"+delete+"</td></tr>"; 
          }
            out.println(data);
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
        Logger.getLogger(datatableRent.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(datatableRent.class.getName()).log(Level.SEVERE, null, ex);
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
