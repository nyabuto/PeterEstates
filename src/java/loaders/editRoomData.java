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
public class editRoomData extends HttpServlet {
HttpSession session;
String id,roomname,room,plotid,plots,houseid,houses,data,editable;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            session=request.getSession();
            dbConn conn = new dbConn();
            id=roomname=room=plotid=plots=houseid=houses=data=editable="";
          id=request.getParameter("id");
            System.out.println("id : "+id);
            
            
//            houses="<option value=\"\">Choose House</option>";
           String getData="SELECT plots.id,houses.id,rooms.name FROM rooms JOIN houses ON rooms.house_id=houses.id "
                    + "JOIN plots ON houses.plot_id=plots.id WHERE rooms.id='"+id+"'";
           conn.rs=conn.st.executeQuery(getData);
           if(conn.rs.next()==true){
             plotid=conn.rs.getString(1);
             houseid=conn.rs.getString(2);
             roomname=conn.rs.getString(3);
             
             String getHouses="SELECT houses.id,houses.name,plots.name FROM houses JOIN plots ON houses.plot_id=plots.id";
             conn.rs1=conn.st1.executeQuery(getHouses);
             while(conn.rs1.next()){
                 if(conn.rs1.getString(1).equals(houseid)){
                  houses+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(3)+" -> "+conn.rs1.getString(2)+"</option>";   
                 }
                 
                 else{
                  houses+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(3)+" -> "+conn.rs1.getString(2)+"</option>";   
                 }
             }
             
             String getPlots="SELECT id,name FROM plots WHERE id='"+plotid+"'";
             conn.rs1=conn.st1.executeQuery(getPlots);
             if(conn.rs1.next()==true){
                 plots+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>";   
               
             }
           }
           
            editable="<input type=\"hidden\" name=\"editroomid\" id=\"editroomid\" value=\""+id+"\">"
                    + "<select name=\"editablePlot\" id=\"editablePlot\" class=\"form-control\" readonly> "+plots+" </select><br>"
                     + "<select name=\"editableHouse\" id=\"editableHouse\" class=\"form-control\"> "+houses+" </select><br>"
                    + "<input type=\"text\" name=\"editroomname\" id=\"editroomname\" value=\""+roomname+"\" class=\"form-control\" required maxlength=\"20\" list=\"allPlots\">";
          
            
           data="<div class=\"modal-dialog\">"+
                                    "<div class=\"modal-content\">"
                                       +" <div class=\"modal-header\">"
                                           +" <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>"
                                          +"  <h4 class=\"modal-title\" id=\"myModalLabel\" style=\"text-align:center;\">Edit Room details.</h4>"
                                        +"</div>"
                                        +"<div class=\"modal-body\">"+
                                     editable
                                             +"   </div>"
                                        +"<div class=\"modal-footer\">"
                                           +" <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>"
                                           +" <button type=\"button\" class=\"btn btn-primary\" onclick=\"return saveRoomUpdates();\">Save changes</button>"
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
        Logger.getLogger(editRoomData.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(editRoomData.class.getName()).log(Level.SEVERE, null, ex);
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
