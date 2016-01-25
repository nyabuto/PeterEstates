/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package databases;

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
import real_estate.IdGenerator;

/**
 *
 * @author Geofrey
 */
public class register extends HttpServlet {
HttpSession session;
String userid,fname,lname,username,password,pass,phone,email;
String newUsername,newId,newFname,newLname,status;
int level;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        dbConn conn = new dbConn();
        IdGenerator IG = new IdGenerator();
        session=request.getSession();
        
        userid=IG.current_id();
        
//    GET PARAMETERS
        fname=request.getParameter("fname");
        lname=request.getParameter("lname");
        username=request.getParameter("username");
        pass=request.getParameter("password");
    phone=email="";
    level=1;
    status="";
    newUsername=newId=newFname=newLname="";
    
        password=pass;
        
        String checkUser="SELECT id,username,fname,lname FROM users WHERE id=? || username=? || (fname=? && lname=?)";
        conn.pst=conn.conn.prepareStatement(checkUser);
        conn.pst.setString(1, userid);
        conn.pst.setString(2, username);
        conn.pst.setString(3, fname);
        conn.pst.setString(4, lname);
    
        conn.rs=conn.pst.executeQuery();
        if(conn.rs.next()==true){
         newUsername=conn.rs.getString(2);
         newId=conn.rs.getString(1);
         newFname=conn.rs.getString(3);
         newLname=conn.rs.getString(4);
         
         if(fname.equals(newFname) && lname.equals(newLname)){
           status="<font color=\"red\"><b>Error</b> : First name and last name already Exists.</font>";    
         }
        
         
         else if(userid.equals(newId)){
          status="<font color=\"red\"><b>Error</b> : User Id Exists.</font>";     
             
         }
         else if(username.equals(newUsername)){
            status="<font color=\"red\"><b>Error</b> : Username Exists.</font>"; 
         }
         else{
           status="<font color=\"red\"><b>Unknown error.</b> </font>";    
         }
        }
        else{
//         add user now   
            
        String inserter="INSERT INTO users (id,fname,lname,phone,email,username,password,level) VALUES(?,?,?,?,?,?,?,?)";
        conn.pst=conn.conn.prepareStatement(inserter);
        conn.pst.setString(1, userid);
        conn.pst.setString(2, fname);
        conn.pst.setString(3, lname);
        conn.pst.setString(4, phone);
        conn.pst.setString(5, email);
        conn.pst.setString(6, username);
        conn.pst.setString(7, password);
        conn.pst.setInt(8, level);
        
        conn.pst.executeUpdate();
         
        status="<font color=\"green\">User added successfully.</font>";
        }
        
        session.setAttribute("userAdded", status);
        
        
        System.out.println("session is : "+session.getAttribute("userAdded"));
        response.sendRedirect("register.jsp");
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
        Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
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
