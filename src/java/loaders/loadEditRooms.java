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
public class loadEditRooms extends HttpServlet {
HttpSession session;
String id,plotname,housename,roomname;
String data,hidden,edit,delete;
int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            session=request.getSession();
            dbConn conn = new dbConn();
            
            data="";position=0;
            String getData="SELECT rooms.id,plots.name,houses.name,rooms.name FROM rooms JOIN houses ON rooms.house_id=houses.id "
                    + "JOIN plots ON houses.plot_id=plots.id ORDER BY plots.name,houses.name,rooms.name";
            conn.rs=conn.st.executeQuery(getData);
            while(conn.rs.next()){
              id=conn.rs.getString(1);
              plotname=conn.rs.getString(2);
              housename=conn.rs.getString(3);
              roomname=conn.rs.getString(4);
              position++;
              
                hidden="<input type=\"hidden\" name=\"valrm_"+position+"\" id=\"valrm_"+position+"\" value=\""+id+"\">";
             edit="<button class=\"form-control btn btn-info\" id=\"editrm_"+position+"\" onclick=\"return editor3("+position+");\"  name=\"editrm_"+position+"\">Edit</button>";
            delete="<button class=\"form-control btn btn-danger\" id=\"deleterm_"+position+"\" onclick=\"return deletor3("+position+");\"  name=\"deleterm_"+position+"\">Delete</button>";
             data+="<tr><td>"+hidden+""+position+"</td><td>"+plotname+"</td><td>"+housename+"</td><td>"+roomname+"</td><td>"+edit+"</td><td>"+delete+"</td></tr>";
          
              
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
        Logger.getLogger(loadEditRooms.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadEditRooms.class.getName()).log(Level.SEVERE, null, ex);
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
