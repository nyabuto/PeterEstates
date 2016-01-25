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
public class editHouseData extends HttpServlet {
HttpSession session;
String id,plotid,plots,name,data,editable;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           session=request.getSession();
           dbConn conn = new dbConn();
           id=request.getParameter("id");
           plots="";
           String getData="SELECT plot_id,name FROM houses WHERE id='"+id+"'";
           conn.rs=conn.st.executeQuery(getData);
           if(conn.rs.next()==true){
            plotid=conn.rs.getString(1);
            name=conn.rs.getString(2);
           }
           
           String getPlots="SELECT id,name FROM plots";
           conn.rs=conn.st.executeQuery(getPlots);
           while(conn.rs.next()){
              if(conn.rs.getString(1).equalsIgnoreCase(plotid)){
                  plots+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(2)+"</option>";
              }
              else{
                 plots+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";   
              }
           }
           
            editable="<input type=\"hidden\" name=\"editplotid2\" id=\"editplotid2\" value=\""+id+"\">"
                    + "<select name=\"editablePlot\" id=\"editablePlot\" class=\"form-control\"> "+plots+" </select><br>"
                    + "<input type=\"text\" name=\"edithousename\" id=\"edithousename\" value=\""+name+"\" class=\"form-control\" required maxlength=\"20\" list=\"allPlots\">";
          
            
           data="<div class=\"modal-dialog\">"+
                                    "<div class=\"modal-content\">"
                                       +" <div class=\"modal-header\">"
                                           +" <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>"
                                          +"  <h4 class=\"modal-title\" id=\"myModalLabel\" style=\"text-align:center;\">Edit House details.</h4>"
                                        +"</div>"
                                        +"<div class=\"modal-body\">"+
                                     editable
                                             +"   </div>"
                                        +"<div class=\"modal-footer\">"
                                           +" <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>"
                                           +" <button type=\"button\" class=\"btn btn-primary\" onclick=\"return saveHouseUpdates();\">Save changes</button>"
                                        +"</div>"
                                    +"</div>"
                                +"</div>";
                                            
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
        Logger.getLogger(editHouseData.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(editHouseData.class.getName()).log(Level.SEVERE, null, ex);
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
