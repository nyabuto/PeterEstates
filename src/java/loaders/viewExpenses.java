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
public class viewExpenses extends HttpServlet {
HttpSession session;
String yearMonth,plot,room, reason,amount,data,expenseType,expenses;
int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          session=request.getSession();
          dbConn conn = new dbConn();
          
          yearMonth=request.getParameter("yearMonth").replace("-", "");
          
          data="";
          position=0;
          
          String getData="SELECT expenses.expense_type,plots.name,rooms.name,expenses.reason,expenses.amount " +
"FROM expenses " +
"LEFT JOIN plots ON expenses.plotid=plots.id  " +
"LEFT JOIN rooms ON rooms.id=expenses.roomid " +
"WHERE expenses.yearmonth='"+yearMonth+"'";
        conn.rs=conn.st.executeQuery(getData);
          while (conn.rs.next()){
              System.out.println("position : "+position);
                   expenseType=conn.rs.getString(1);
                     plot=conn.rs.getString(2);
                    room=conn.rs.getString(3); 
                    reason=conn.rs.getString(4);
                    amount=conn.rs.getString(5);
              switch (expenseType) {
                  case "1":
                      expenses="Plot expenses for "+plot+"(Ksh : "+amount+")";
                      break;
                  case "2":
                      expenses="Room expenses for :  "+plot+" , "+room+"(Ksh : "+amount+")";
                      break;
                      default:
                          expenses="Unknown expenses";
              }
              
            position++;
            data+="<li class=\"clearfix\">" +
"<div class=\"chat-body clearfix\">" +
"<div class=\"header\">\n" +
"<strong class=\"primary-font \"> "+position+". "+expenses+" </strong>" +
"</div>" +
"<br />" +
"<p>" +
""+reason+"" +
"</p>" +
"</div>" +
" </li>";
              
          }
          if(position==0){
              data="<li class=\"clearfix\">" +
"<div class=\"chat-body clearfix\">" +
"<div class=\"header\">\n" +
"<p>" +
"No added expenses." +
"</p>" +
"</div>" +
" </li>";
          }
            System.out.println("other expenses : "+data);
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
        Logger.getLogger(viewExpenses.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(viewExpenses.class.getName()).log(Level.SEVERE, null, ex);
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
