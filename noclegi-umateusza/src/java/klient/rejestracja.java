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
import polaczenie.PolaczenieDB;

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
        boolean status;
        try (PrintWriter out = response.getWriter()) {
            String imie = request.getParameter("imie");
            String nazwisko = request.getParameter("nazwisko");
            String email = request.getParameter("email");
            String tel = request.getParameter("nrtel");
            String haslo = request.getParameter("haslo");

            Connection con = PolaczenieDB.getConnection();

            //sprawdzenie wypełnienia wymaganych pól
            if (email.equals("") || haslo.equals("")) {
                out.println("<div class =\"alert alert-warning\">");
                out.println("<strong>Błąd:<strong> Brak wymaganych danych </font>");
                out.println("</div>");
            } else {
                Statement stc = con.createStatement();
                ResultSet rs = stc.executeQuery("select email from uzytkownik where email = \"" + email + "\"");
                status = rs.next();

                //sprawdzenie czy istnieje konto o podanym email
                if (status) {
                    out.println("<div class =\"alert alert-warning\">");
                    out.println("<strong>Błąd:<strong> Istnieje konto o podanym adresie </font>");
                    out.println("</div>");
                } 
                //dodanie użytkownika
                else {
                    //Dodawanie nowego użytkownika
                    PreparedStatement st = con.prepareStatement("Insert into uzytkownik values(?,?,?,?,?,?)");

                    st.setNull(1, java.sql.Types.INTEGER);
                    st.setString(3, imie);
                    st.setString(4, nazwisko);
                    st.setString(5, email);
                    st.setString(6, tel);
                    st.setString(2, haslo);

                    int i = st.executeUpdate();
                    if (i > 0) {
                        out.print("0");
                    }
                }
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
