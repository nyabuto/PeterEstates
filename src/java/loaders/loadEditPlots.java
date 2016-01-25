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
public class loadEditPlots extends HttpServlet {
HttpSession session;
String data,id,name,edit,delete,hidden;
int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         session=request.getSession();
         dbConn conn = new dbConn();
         data="";position=0; hidden="";
         
         String getData="SELECT id,name FROM plots";
         conn.rs=conn.st.executeQuery(getData);
         while(conn.rs.next()){
             id=conn.rs.getString(1);
             name=conn.rs.getString(2);
             position++;
             
             hidden="<input type=\"hidden\" name=\"val_"+position+"\" id=\"val_"+position+"\" value=\""+id+"\">";
             edit="<button class=\"form-control btn btn-info\" id=\"edit_"+position+"\" onclick=\"return editor("+position+");\"  name=\"edit_"+position+"\">Edit</button>";
            delete="<button class=\"form-control btn btn-danger\" id=\"delete_"+position+"\" onclick=\"return deletor("+position+");\"  name=\"delete_"+position+"\">Delete</button>";
             data+="<tr><td>"+hidden+""+position+"</td><td>"+name+"</td><td>"+edit+"</td><td>"+delete+"</td></tr>";
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
        Logger.getLogger(loadEditPlots.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadEditPlots.class.getName()).log(Level.SEVERE, null, ex);
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
