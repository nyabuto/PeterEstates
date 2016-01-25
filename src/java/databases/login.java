/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package databases;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.security.MessageDigest;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Geofrey
 */
public class login extends HttpServlet {
HttpSession session;
String username,pass,password;
String status;
 String fname,mname,lname,level,userid,nextpage;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
      dbConn conn = new dbConn();
      session=request.getSession();
      username=request.getParameter("username");
      pass=request.getParameter("password");
    password=pass;
        System.out.println("username : "+username+" password : "+password);
      fname=mname=lname=level=userid="";
     String checker="SELECT fname,lname,level,id FROM users WHERE username=? && password=?";
     conn.pst=conn.conn.prepareStatement(checker);
     conn.pst.setString(1, username);
     conn.pst.setString(2, password);
     
     conn.rs=conn.pst.executeQuery();
     if(conn.rs.next()==true){
         fname=conn.rs.getString(1);
       lname=conn.rs.getString(2);
         level=conn.rs.getString(3);
         userid=conn.rs.getString(4);
         session.setAttribute("userid", userid);
         session.setAttribute("fullname", fname+" "+lname);
         session.setAttribute("level", level);
     }
      
    if(userid.equals("")){
        nextpage="index.jsp";
        session.setAttribute("login", "<font color=\"red\"><b>ERROR : </b> Wrong username and password combination.</font>");
    }
    else{
        nextpage="payRent.jsp";
    }
    
    response.sendRedirect(nextpage);
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
    } catch (Exception ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (Exception ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
