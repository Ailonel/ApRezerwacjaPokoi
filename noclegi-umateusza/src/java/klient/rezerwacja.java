package klient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mateusz
 */
@WebServlet(urlPatterns = {"/rezerwacja"})
public class rezerwacja extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String data = "2015-10-27";
            String dataP = request.getParameter("dataP");
            String dataW = request.getParameter("dataW");
            String pokoj = request.getParameter("nrPok");

            //Sterowniki dla bazy danych
            Class.forName("com.mysql.jdbc.Driver");
            //Połączenie z bazą danych
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rezerwacja_pokoi", "root", "root");

            //Tworzenie rekordu
            PreparedStatement st = con.prepareStatement("Insert into Rezerwacja values(?,?,?,?,?,?,?)");

            //st.setString(1, "NULL");
            st.setNull(1, java.sql.Types.INTEGER);
            st.setString(2, data);
            st.setString(3, dataP);
            st.setString(4, dataW);
            st.setString(5, "1");
            st.setString(6, "1");
            st.setString(7, pokoj);

            int i = st.executeUpdate();
            if (i > 0) {
                out.println("Rezerwacja zakończona pomyślnie!");
            }
        } catch (Exception se) {
            se.printStackTrace();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
