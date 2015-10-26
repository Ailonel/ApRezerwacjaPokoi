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
@WebServlet(urlPatterns = {"/rejestracja"})
public class rejestracja extends HttpServlet {

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
            String imie = request.getParameter("imie");
            String nazwisko = request.getParameter("nazwisko");
            String email = request.getParameter("email");
            String tel = request.getParameter("nrtel");
            String haslo = request.getParameter("haslo");

            //Sterowniki dla bazy danych
            Class.forName("com.mysql.jdbc.Driver");
            //Połączenie z bazą danych
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rezerwacja_pokoi", "root", "root");

            //Tworzenie rekordu
            PreparedStatement st = con.prepareStatement("Insert into uzytkownik values(?,?,?,?,?,?)");

            //st.setString(1, "NULL");
            st.setNull(1, java.sql.Types.INTEGER);
            st.setString(3, imie);
            st.setString(4, nazwisko);
            st.setString(5, email);
            st.setString(6, tel);
            st.setString(2, haslo);

            int i = st.executeUpdate();
            if (i > 0) {
                out.println("Rejestracja zakończona pomyślnie!");
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
